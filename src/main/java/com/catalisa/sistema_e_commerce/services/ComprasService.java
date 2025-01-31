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


        List<String> listaDeProdutosIndisponiveis = new ArrayList<>();
        List<ProdutoEntity> produtosParaAtualizar = new ArrayList<>();
        List<CarrinhoEntity> carrinhoEntities = new ArrayList<>();

        for (Carrinho produtoCarrinho : compras.produtos()) {
            ProdutoEntity produtoEntity = validarExistenciaProduto(produtoCarrinho);

            boolean produtoSemEstoque = produtoEntity.getQuantidade() <= 0;

            if (produtoSemEstoque) {
                listaDeProdutosIndisponiveis.add(produtoEntity.getNome());
            } else {
                produtosParaAtualizar.add(produtoEntity);
            }

        }

        if (!listaDeProdutosIndisponiveis.isEmpty()) {
            throw new RuntimeException("erro: Produto em falta: " + listaDeProdutosIndisponiveis);
        }

        for (ProdutoEntity produto : produtosParaAtualizar) {
            produto.setQuantidade(produto.getQuantidade() - 1);
            produtoRepository.save(produto);
            carrinhoEntities.add(new CarrinhoEntity(produto.getNome()));
        }



        ComprasEntity comprasEntity = new ComprasEntity(compras.cpf(), carrinhoEntities);
        comprasRepository.save(comprasEntity);


        List<Carrinho> carrinhoResponse = carrinhoEntities
                .stream()
                .map(produtoEntity -> new Carrinho(produtoEntity.getNome()))
                .toList();

        
        return new Compras(comprasEntity.getCpf(), carrinhoResponse);
    }

    private ProdutoEntity validarExistenciaProduto(Carrinho produtoCarrinho) {
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
