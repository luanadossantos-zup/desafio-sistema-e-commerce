package com.catalisa.sistema_e_commerce.services;

import com.catalisa.sistema_e_commerce.models.Produto;
import com.catalisa.sistema_e_commerce.models.ProdutoEntity;
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
    private final String PRODUTO_SERVICE = "ProductService:: ";


    public Produto criarProduto(@Valid Produto produto) {
        ProdutoEntity entity = new ProdutoEntity(produto.nome(), produto.preco(), produto.quantidade());

        boolean nomeProdutoJaExiste = produtoRepository.existsById(entity.getNome());

        if(nomeProdutoJaExiste) {
            throw new RuntimeException("Produto com o nome " + entity.getNome() + " já existe!");
        } else {
            ProdutoEntity entidadeSalva = produtoRepository.save(entity);
            return new Produto(entidadeSalva.getNome(), entidadeSalva.getPreco(), entidadeSalva.getQuantidade());
        }

    }

    public Produto atualizaProduto(@Valid String nomeId, Produto produtoAtualizado) {

        ProdutoEntity entidadeExistente = produtoRepository
                .findById(nomeId)
                .orElseThrow(() -> new RuntimeException(PRODUTO_SERVICE + "produto com o nome " + nomeId + " não foi encontrado."));

        entidadeExistente.setNome(produtoAtualizado.nome());
        entidadeExistente.setPreco(produtoAtualizado.preco());
        entidadeExistente.setQuantidade(produtoAtualizado.quantidade());

        ProdutoEntity entidadeSalva = produtoRepository.save(entidadeExistente);

        return new Produto(entidadeSalva.getNome(), entidadeSalva.getPreco(), entidadeSalva.getQuantidade());
    }

    public void excluirProduto(String nomeId) {

        boolean produtoExiste = produtoRepository.existsById(nomeId);

        if (produtoExiste) {
            produtoRepository.deleteById(nomeId);
        } else {
            System.out.println(PRODUTO_SERVICE + "produto com o nome " + nomeId + " não foi encontrado.");
            throw new RuntimeException("Produto com o nome " + nomeId + " não foi encontrado.");
        }

    }

    public List<Produto> listarTodosProdutos() {
        return produtoRepository
                .findAll()
                .stream()
                .map(entity -> new Produto(entity.getNome(), entity.getPreco(), entity.getQuantidade()))
                .collect(Collectors.toList());
    }

}
