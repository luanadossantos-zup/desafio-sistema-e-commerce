package com.catalisa.sistema_e_commerce.services;

import com.catalisa.sistema_e_commerce.models.Cliente;
import com.catalisa.sistema_e_commerce.models.ClienteEntity;
import com.catalisa.sistema_e_commerce.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    private final String CLIENTE_SERVICE = "ClienteService:: ";

    public Cliente criarCliente(@Valid Cliente cliente) {
        ClienteEntity entidade = new ClienteEntity(cliente.nome(), cliente.cpf(), cliente.email());

        validaCpf(entidade);
        validaEmail(entidade);

        ClienteEntity entidadeSalva = clienteRepository.save(entidade);
        return new Cliente(entidadeSalva.getNome(), entidadeSalva.getCpf(), entidadeSalva.getEmail());

    }

    public Cliente atualizarCliente (@Valid String cpf,Cliente clienteAtualizado) {

        ClienteEntity entidadeExistente = clienteRepository
                .findById(cpf)
                .orElseThrow(() -> new RuntimeException(CLIENTE_SERVICE + "produto com o nome " + cpf + " não foi encontrado."));

        entidadeExistente.setNome(clienteAtualizado.nome());
        entidadeExistente.setCpf(clienteAtualizado.cpf());
        entidadeExistente.setEmail(clienteAtualizado.email());

        ClienteEntity entidadeSalva = clienteRepository.save(entidadeExistente);

        return new Cliente(entidadeSalva.getNome(), entidadeSalva.getCpf(), entidadeSalva.getEmail());
    }

    public void excluirCliente(String cpfId) {

        boolean entidadeExiste = clienteRepository.existsById(cpfId);

        if (!entidadeExiste) {
            System.out.println(CLIENTE_SERVICE + "cliente com o cpf " + cpfId + " não foi encontrado.");
            throw new RuntimeException("Cliente com o cpf " + cpfId + " não foi encontrado.");
        }

        clienteRepository.deleteById(cpfId);
    }

    public List<Cliente> listarTodosClientes() {
        return clienteRepository
                .findAll()
                .stream()
                .map(entity -> new Cliente(entity.getNome(), entity.getCpf(), entity.getEmail()))
                .collect(Collectors.toList());
    }

    private void validaCpf(ClienteEntity entidade) {
        boolean cpfJaExisteNoBD = clienteRepository.existsById(entidade.getCpf());
        if(cpfJaExisteNoBD) {
            throw new RuntimeException("Cliente com o cpf " + entidade.getCpf() + " já existe!");
        }
    }

    private void validaEmail(ClienteEntity entidade) {
        boolean emailJaExisteNoBD = clienteRepository.existsByEmail(entidade.getEmail());

        if (emailJaExisteNoBD) {
            throw new RuntimeException("Cliente com o e-mail " + entidade.getEmail() + " já existe!");
        }
    }


}
