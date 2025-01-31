package com.catalisa.sistema_e_commerce.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ProdutosComprasEntity {

    @Id
    private String nome;

    public ProdutosComprasEntity() {
    }

    public ProdutosComprasEntity(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
