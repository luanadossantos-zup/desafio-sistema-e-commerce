package com.catalisa.sistema_e_commerce.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
public class ClienteEntity {

    @NotNull(message = "O nome não pode estar em branco!")
    private String nome;


    @NotNull(message = "O CPF não pode estar em branco!")
    @Size(min = 11, max = 11, message = "O CPF deve conter exatamente 11 dígitos!")
    @Column(unique = true)
    @Id
    private String cpf;

    @Email
    @NotNull(message = "O email não pode estar em branco!")
    @Column(unique = true)
    private String email;

    public ClienteEntity() {
    }

    public ClienteEntity(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
