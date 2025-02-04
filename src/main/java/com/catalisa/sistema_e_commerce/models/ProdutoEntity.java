package com.catalisa.sistema_e_commerce.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


@Entity
public class ProdutoEntity {



    @NotNull(message = "O nome não pode estar nulo!")
    @NotEmpty(message = "O nome não pode estar vazio!")
    @Column(unique = true)
    @Id
    private String nome;

    @NotNull(message = "O preço não pode estar nulo!")
    @DecimalMin(value = "0.1", message = "O preço deve ser maior que 0!")
    private Double preco;

    @NotNull(message = "A quantidade não pode estar nulo!")
    @Min(value = 0, message = "Quantidade mínima não pode ser negativa!")
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
