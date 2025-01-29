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
        ProdutoEntity entidade = new ProdutoEntity(produto.nome(), produto.preco(), produto.quantidade());

        if(produtoRepository.existsById(entidade.getNome())) {
            throw new RuntimeException("Produto com o nome " + entidade.getNome() + " já existe!");
        } else {
            ProdutoEntity entidadeSalva = produtoRepository.save(entidade);
            return new Produto(entidadeSalva.getNome(), entidadeSalva.getPreco(), entidadeSalva.getQuantidade());
        }


    }

    public Produto atualizaProduto(@Valid String nomeId, Produto produtoAtualizado) {

        ProdutoEntity entidadeExistente = produtoRepository.findById(nomeId)
                .orElseThrow(() -> new RuntimeException(PRODUTO_SERVICE + "produto com o nome " + nomeId + " não foi encontrado."));


        entidadeExistente.setNome(produtoAtualizado.nome());
        entidadeExistente.setPreco(produtoAtualizado.preco());
        entidadeExistente.setQuantidade(produtoAtualizado.quantidade());


        ProdutoEntity entidadeSalva = produtoRepository.save(entidadeExistente);


        return new Produto(entidadeSalva.getNome(), entidadeSalva.getPreco(), entidadeSalva.getQuantidade());
    }

    public void excluirProduto(String nomeId) {

        boolean entidadeExiste = produtoRepository.existsById(nomeId);

        if (entidadeExiste) {
            produtoRepository.deleteById(nomeId);
        } else {
            System.out.println(PRODUTO_SERVICE + "produto com o nome " + nomeId + " não foi encontrado.");
            throw new RuntimeException("Produto com o nome " + nomeId + " não foi encontrado.");
        }

    }

    public List<Produto> listarTodosProdutos() {
        return produtoRepository.findAll().stream()
                .map(entity -> new Produto(entity.getNome(), entity.getPreco(), entity.getQuantidade()))
                .collect(Collectors.toList());
    }

}
