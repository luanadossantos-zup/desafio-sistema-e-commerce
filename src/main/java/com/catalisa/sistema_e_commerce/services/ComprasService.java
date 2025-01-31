package com.catalisa.sistema_e_commerce.services;

import com.catalisa.sistema_e_commerce.models.*;
import com.catalisa.sistema_e_commerce.repository.ClienteRepository;
import com.catalisa.sistema_e_commerce.repository.ComprasRepository;
import com.catalisa.sistema_e_commerce.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComprasService {

    @Autowired
    private ComprasRepository comprasRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public Compras fazCompra(@Valid Compras compras) {

        validarCpf(compras);

        List<String> produtosIndisponiveis = new ArrayList<>();
        List<ProdutoEntity> produtosDisponiveis = new ArrayList<>();
        List<CarrinhoEntity> carrinhoEntities = new ArrayList<>();

        separarProdutosPorDisponibilidade(compras, produtosIndisponiveis, produtosDisponiveis);

        if (!produtosIndisponiveis.isEmpty()) {
            throw new RuntimeException("erro: Produto em falta: " + produtosIndisponiveis);
        }

        atualizarProdutosEstoqueBD(produtosDisponiveis, carrinhoEntities);

        ComprasEntity comprasEntity = atualizarComprasBD(compras, carrinhoEntities);

        List<Carrinho> carrinhoResponse = criarRespostaCarrinho(carrinhoEntities);

        return new Compras(comprasEntity.getCpf(), carrinhoResponse);
    }

    private void separarProdutosPorDisponibilidade(Compras compras, List<String> produtosIndisponiveis, List<ProdutoEntity> produtosDisponiveis) {
        for (Carrinho produtoCarrinho : compras.produtos()) {
            ProdutoEntity produtoEntity = validarExistenciaProdutoNoBD(produtoCarrinho);

            boolean produtoSemEstoque = produtoEntity.getQuantidade() <= 0;

            if (produtoSemEstoque) {
                produtosIndisponiveis.add(produtoEntity.getNome());
            } else {
                produtosDisponiveis.add(produtoEntity);
            }

        }
    }

    private static List<Carrinho> criarRespostaCarrinho(List<CarrinhoEntity> carrinhoEntities) {
        return carrinhoEntities
                .stream()
                .map(produtoEntity -> new Carrinho(produtoEntity.getNome()))
                .toList();
    }

    private ComprasEntity atualizarComprasBD(Compras compras, List<CarrinhoEntity> carrinhoEntities) {
        ComprasEntity comprasEntity = new ComprasEntity(compras.cpf(), carrinhoEntities);
        comprasRepository.save(comprasEntity);
        return comprasEntity;
    }

    private void atualizarProdutosEstoqueBD(List<ProdutoEntity> produtosParaAtualizar, List<CarrinhoEntity> carrinhoEntities) {
        for (ProdutoEntity produto : produtosParaAtualizar) {
            produto.setQuantidade(produto.getQuantidade() - 1);
            produtoRepository.save(produto);
            carrinhoEntities.add(new CarrinhoEntity(produto.getNome()));
        }
    }


    private ProdutoEntity validarExistenciaProdutoNoBD(Carrinho produtoCarrinho) {
        return produtoRepository
                .findById(produtoCarrinho.nome())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + produtoCarrinho.nome()));
    }

    private void validarCpf(Compras compras) {
        clienteRepository
                .findById(compras.cpf())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }
}
