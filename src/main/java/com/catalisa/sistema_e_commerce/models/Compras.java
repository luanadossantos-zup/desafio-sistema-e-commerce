package com.catalisa.sistema_e_commerce.models;

import java.util.ArrayList;

public record Compras(String cpf, ArrayList<Produto> produto) {
}
