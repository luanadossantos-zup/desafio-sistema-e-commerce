package com.catalisa.sistema_e_commerce.controllers;

import com.catalisa.sistema_e_commerce.models.Cliente;
import com.catalisa.sistema_e_commerce.services.ClienteService;
import jakarta.transaction.Transactional;
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
        Cliente clienteSalvo = service.criarCliente(cliente);
        return ResponseEntity.ok().body(clienteSalvo);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<Cliente> atualizarCliente (@Valid @PathVariable String cpf, @RequestBody Cliente cliente) {
        return ResponseEntity.ok(service.atualizarCliente(cpf, cliente));
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<String> deletarCliente (@Valid @PathVariable String cpf) {
        service.excluirCliente(cpf);
        return ResponseEntity.ok("Cliente deletado com sucesso!");
    }

    @GetMapping
    public  ResponseEntity<List<Cliente>> listarTodosClientes () {
        return ResponseEntity.ok(service.listarTodosClientes());
    }
}
