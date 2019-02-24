package com.brasilprev.pedidos.dominio.produtos;

import com.brasilprev.pedidos.utils.ExcecaoDeNegocio;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String nome;
    private String descricao;
    @NotNull
    private double preco;
    @NotNull
    private int quantidade;
    private String foto;
    @NotNull
    @OneToOne
    private Categoria categoria;

    private Produto(){}

    public Produto(String nome, String descricao, double preco, int quantidade, String foto, Categoria categoria) {

        ExcecaoDeNegocio.Validar(nome == null || nome.equals(""), "Nome é obrigatório");
        ExcecaoDeNegocio.Validar(preco < 0, "Preço é inválido");
        ExcecaoDeNegocio.Validar(quantidade < 0, "Quantidade é inválida");
        ExcecaoDeNegocio.Validar(categoria == null, "Categoria é obrigatória");

        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
        this.foto = foto;
        this.categoria = categoria;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getFoto() {
        return foto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void removerQuantidade(int quantidadeParaRemover) {
        ExcecaoDeNegocio.Validar(quantidade - quantidadeParaRemover < 0, "Produto não tem quantidade suficiente em estoque");

        quantidade -= quantidadeParaRemover;
    }
}
