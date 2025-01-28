package com.catalisa.sistema_e_commerce.model;

import java.util.ArrayList;

public record Compras(String cpfComprador, ArrayList<String> produtosComprados) {
}
