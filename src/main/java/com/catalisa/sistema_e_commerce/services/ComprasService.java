package com.catalisa.sistema_e_commerce.services;

import com.catalisa.sistema_e_commerce.models.*;
import com.catalisa.sistema_e_commerce.repository.ClienteRepository;
import com.catalisa.sistema_e_commerce.repository.ComprasRepository;
import com.catalisa.sistema_e_commerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ComprasService {

    @Autowired
    private ComprasRepository comprasRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;


    private final String COMPRAS_SERVICE = "ClienteService:: ";


    public Compras fazCompra (Compras compras) {

        if (compras == null) {
            throw new RuntimeException(COMPRAS_SERVICE + "a compra não pode ser nulo");
        }

        ComprasEntity entidade = new ComprasEntity(compras.cpf(), compras.produto());

        ComprasEntity entidadeSalva = comprasRepository.save(entidade);

        ClienteEntity clienteEntity = clienteRepository
                .findById(compras.cpf())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        // Lista para armazenar os produtos encontrados
        ArrayList<Produto> produtosEncontrados = new ArrayList<>();

        // Itera sobre os produtos da compra
        for (Produto produto : compras.produto()) {
            ProdutoEntity produtoEntity = produtoRepository
                    .findById(produto.nome())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + produto.nome()));

            if (produtoEntity.getQuantidade() == 0)  {
                throw new RuntimeException("erro: Produto em falta: " + produto.nome());
            }

            // Adiciona o produto encontrado à lista
            produtosEncontrados.add(new Produto(produtoEntity.getNome(), produtoEntity.getPreco(), produtoEntity.getQuantidade()- 1));
        }




        return new Compras(clienteEntity.getCpf(), produtosEncontrados);
    }
}
