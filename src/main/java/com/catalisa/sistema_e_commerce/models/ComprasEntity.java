package com.catalisa.sistema_e_commerce.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class ComprasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpf;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CarrinhoEntity> produtos;

    public ComprasEntity() {
    }

    public ComprasEntity(String cpf, List<CarrinhoEntity> produtos) {
        this.cpf = cpf;
        this.produtos = produtos;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<CarrinhoEntity> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<CarrinhoEntity> produtos) {
        this.produtos = produtos;
    }
}