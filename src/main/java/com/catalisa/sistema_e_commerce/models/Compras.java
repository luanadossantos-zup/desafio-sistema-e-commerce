package com.catalisa.sistema_e_commerce.models;

import java.util.List;

public record Compras(String cpf, List<ProdutosCompras> produtos) {
}
