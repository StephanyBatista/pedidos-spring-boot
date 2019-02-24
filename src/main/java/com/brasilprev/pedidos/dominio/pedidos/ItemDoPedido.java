package com.brasilprev.pedidos.dominio.pedidos;

import com.brasilprev.pedidos.dominio.produtos.Produto;
import com.brasilprev.pedidos.utils.ExcecaoDeNegocio;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class ItemDoPedido {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @NotNull
    @OneToOne
    private Produto produto;
    @NotNull
    private int quantidade;

    public ItemDoPedido(){}

    public ItemDoPedido(Produto produto, int quantidade) {

        ExcecaoDeNegocio.Validar(produto == null, "Produto é obrigatório");
        ExcecaoDeNegocio.Validar(quantidade <= 0, "Quantidade informada é inválida");

        this.produto = produto;
        this.quantidade = quantidade;
    }

    public long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValorTotal() {
        return quantidade * produto.getPreco();
    }
}
