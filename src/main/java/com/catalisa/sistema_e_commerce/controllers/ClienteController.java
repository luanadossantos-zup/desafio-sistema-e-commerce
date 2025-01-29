package com.catalisa.sistema_e_commerce.controllers;

import com.catalisa.sistema_e_commerce.models.Cliente;
import com.catalisa.sistema_e_commerce.models.Produto;
import com.catalisa.sistema_e_commerce.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {


    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@Valid @RequestBody Cliente cliente) {
        return ResponseEntity.ok(service.criarCliente(cliente));
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<Cliente> atualizarCliente (@Valid @PathVariable String cpfId, @RequestBody Cliente cliente) {
        return ResponseEntity.ok(service.atualizarCliente(cpfId, cliente));
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deletarCliente (@Valid @PathVariable String cpfId) {
        service.excluirCliente(cpfId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public  ResponseEntity<List<Cliente>> listarTodosClientes () {
        return ResponseEntity.ok(service.listarTodosClientes());
    }
}
