package com.catalisa.sistema_e_commerce.services;

import com.catalisa.sistema_e_commerce.model.Produto;
import com.catalisa.sistema_e_commerce.model.ProdutoEntity;
import com.catalisa.sistema_e_commerce.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;


    public Produto criarProduto(@Valid Produto produto) {
        ProdutoEntity entity = new ProdutoEntity(produto.nome(), produto.preco(), produto.quantidade());
        ProdutoEntity savedEntity = produtoRepository.save(entity);
        return new Produto(savedEntity.getNome(), savedEntity.getPreco(), savedEntity.getQuantidade());
    }

    public List<Produto> listarTodosProdutos() {
        return produtoRepository.findAll().stream()
                .map(entity -> new Produto(entity.getNome(), entity.getPreco(), entity.getQuantidade()))
                .collect(Collectors.toList());
    }


}
