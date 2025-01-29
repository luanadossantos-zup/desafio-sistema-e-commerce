package com.catalisa.sistema_e_commerce.controllers;

import com.catalisa.sistema_e_commerce.models.Produto;
import com.catalisa.sistema_e_commerce.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Produto> criarProduto(@Valid @RequestBody Produto produto) {
        return ResponseEntity.ok(service.criarProduto(produto));
    }

    @PutMapping("/{name}")
    public ResponseEntity<Produto> atualizarProduto (@Valid @PathVariable String nome, @RequestBody Produto produto) {
        return ResponseEntity.ok(service.atualizaProduto(nome, produto));
    }

    @DeleteMapping("/{nome}")
    public ResponseEntity<Void> deletarProduto (@Valid @PathVariable String nome) {
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public  ResponseEntity<List<Produto>> listarTodosProdutos () {
        return ResponseEntity.ok(service.listarTodosProdutos());
    }

}
