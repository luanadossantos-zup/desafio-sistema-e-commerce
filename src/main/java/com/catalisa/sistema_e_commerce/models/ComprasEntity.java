package com.catalisa.sistema_e_commerce.models;

import java.util.ArrayList;

public class ComprasEntity {

    private String cpf;

    private ArrayList<Produto> produtos;

    public ComprasEntity() {
    }

    public ComprasEntity(String cpf, ArrayList<Produto> produtos) {
        this.cpf = cpf;
        this.produtos = produtos;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }
}
