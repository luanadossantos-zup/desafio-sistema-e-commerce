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
    private List<ProdutosComprasEntity> produtos;

    public ComprasEntity() {
    }

    public ComprasEntity(String cpf, List<ProdutosComprasEntity> produtos) {
        this.cpf = cpf;
        this.produtos = produtos;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<ProdutosComprasEntity> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutosComprasEntity> produtos) {
        this.produtos = produtos;
    }
}