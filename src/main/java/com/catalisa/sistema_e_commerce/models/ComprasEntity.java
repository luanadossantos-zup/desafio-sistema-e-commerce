package com.catalisa.sistema_e_commerce.models;

import jakarta.persistence.Entity;

import java.util.ArrayList;

@Entity
public class ComprasEntity {
    private String cpfComprador;
    private ArrayList<String> produtosComprados;
}
