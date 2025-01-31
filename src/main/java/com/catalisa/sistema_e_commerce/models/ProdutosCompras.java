package com.catalisa.sistema_e_commerce.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProdutosCompras(@JsonProperty("nome") String nome) {
}
