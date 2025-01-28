package com.catalisa.sistema_e_commerce.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.annotation.Id;

@Entity
public class ClienteEntity {

    private String nome;

    @Id
    @NotBlank(message = "O CPF não pode estar em branco!")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter exatamente 11 dígitos!")
    private String cpf;

    @Email
    @NotBlank(message = "O email não pode estar em branco!")
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
