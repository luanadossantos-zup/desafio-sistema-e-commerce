package com.catalisa.sistema_e_commerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

@Entity
public class ProdutoEntity {


    @Id
    @NotNull(message = "O nome não pode estar em branco!")
    @Column(unique = true)
    private String nome;

    @NotNull(message = "O preço não pode estar em branco!")
    @DecimalMin(value = "0.1", message = "O preço deve ser maior que 0!")
    private Double preco;

    @NotNull(message = "A quantidade não pode estar em branco!")
    private int quantidade;

    public ProdutoEntity() {

    }

    public ProdutoEntity(String nome, Double preco, int quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
