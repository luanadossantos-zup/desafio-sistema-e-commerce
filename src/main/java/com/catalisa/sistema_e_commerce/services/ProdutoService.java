package com.catalisa.sistema_e_commerce.services;

import com.catalisa.sistema_e_commerce.model.Produto;
import com.catalisa.sistema_e_commerce.model.ProdutoEntity;
import com.catalisa.sistema_e_commerce.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;


    public Produto criarProduto(@Valid Produto produto) {
        ProdutoEntity entity = new ProdutoEntity(produto.nome(), produto.preco(), produto.quantidade());
        ProdutoEntity savedEntity = produtoRepository.save(entity);
        return new Produto(savedEntity.getNome(), savedEntity.getPreco(), savedEntity.getQuantidade());
    }

}
