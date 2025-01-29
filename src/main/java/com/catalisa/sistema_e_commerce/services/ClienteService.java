package com.catalisa.sistema_e_commerce.services;

import com.catalisa.sistema_e_commerce.models.Cliente;
import com.catalisa.sistema_e_commerce.models.ClienteEntity;
import com.catalisa.sistema_e_commerce.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente criarCliente(@Valid Cliente cliente) {
        ClienteEntity entidade = new ClienteEntity(cliente.nome(), cliente.cpf(), cliente.email());

        if(clienteRepository.existsById(entidade.getCpf())) {
            throw new RuntimeException("Cliente com o cpf " + entidade.getCpf() + " já existe!");
        } else {
            ClienteEntity entidadeSalva = clienteRepository.save(entidade);
            return new Cliente(entidadeSalva.getNome(), entidadeSalva.getCpf(), entidadeSalva.getEmail());
        }


    }

}
