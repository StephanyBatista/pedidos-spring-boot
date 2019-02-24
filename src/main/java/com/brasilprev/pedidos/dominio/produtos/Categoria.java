package com.brasilprev.pedidos.dominio.produtos;

import com.brasilprev.pedidos.utils.ExcecaoDeNegocio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String nome;

    private Categoria(){}

    public Categoria(String nome) {

        ExcecaoDeNegocio.Validar(nome == null || nome.equals(""), "Nome é obrigatório");

        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public long getId() {
        return id;
    }
}
