package com.catalisa.sistema_e_commerce.controllers;

import com.catalisa.sistema_e_commerce.models.Compras;
import com.catalisa.sistema_e_commerce.services.ComprasService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/compras")
public class ComprasController {

    private final ComprasService service;

    public ComprasController(ComprasService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Compras> salvarCompras(@Valid @RequestBody Compras compras) {

        return ResponseEntity.ok(service.fazCompra(compras));
    }
}
