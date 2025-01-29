package com.catalisa.sistema_e_commerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.annotation.Id;

@Entity
public class ClienteEntity {

    @NotNull(message = "O nome não pode estar em branco!")
    private String nome;

    @Id
    @NotNull(message = "O CPF não pode estar em branco!")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter exatamente 11 dígitos!")
    @Column(unique = true)
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
