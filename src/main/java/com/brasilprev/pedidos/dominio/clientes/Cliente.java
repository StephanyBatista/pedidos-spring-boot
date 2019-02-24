package com.brasilprev.pedidos.dominio.clientes;

import com.brasilprev.pedidos.utils.ExcecaoDeNegocio;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String nome;
    @NotNull
    private String email;
    @NotNull
    private String senha;
    @NotNull
    private Endereco endereco;

    private Cliente(){}

    public Cliente(String nome, String email, String senha, Endereco endereco) {

        ExcecaoDeNegocio.Validar(nome == null || nome.equals(""), "Nome é obrigatório");
        ExcecaoDeNegocio.Validar(email == null || email.equals(""), "Email é obrigatório");
        ExcecaoDeNegocio.Validar(senha == null || senha.equals(""), "Senha é obrigatório");
        ExcecaoDeNegocio.Validar(endereco == null, "Endereco é obrigatório");

        ExcecaoDeNegocio.Validar(senha.length() < 6, "Senha deve ter no mínio 6 caracteres");

        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public Endereco getEndereco() {
        return endereco;
    }
}
