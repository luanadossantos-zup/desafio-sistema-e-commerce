package com.catalisa.sistema_e_commerce.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class CarrinhoEntity {

    @Id
    @NotNull (message = "O nome não pode ser nulo!")
    @NotEmpty(message = "O nome não pode estar vazio!")
    private String nome;

    public CarrinhoEntity() {
    }

    public CarrinhoEntity(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
