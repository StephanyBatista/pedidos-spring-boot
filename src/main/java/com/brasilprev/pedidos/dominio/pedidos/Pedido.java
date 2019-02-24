package com.brasilprev.pedidos.dominio.pedidos;

import com.brasilprev.pedidos.dominio.clientes.Cliente;
import com.brasilprev.pedidos.dominio.produtos.Produto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @NotNull
    @OneToOne
    private Cliente cliente;
    @NotNull
    private StatusDoPedido status;
    @NotNull
    private String sessao;
    @NotNull
    @OneToMany(cascade = CascadeType.ALL)
    private List<ItemDoPedido> items;

    private Pedido(){}

    public Pedido(Cliente cliente) {

        this.cliente = cliente;
        this.status = StatusDoPedido.PENDENTE;
        this.sessao = UUID.randomUUID().toString();
        this.items = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public StatusDoPedido getStatus() {
        return status;
    }

    public String getSessao() {
        return sessao;
    }

    public List<ItemDoPedido> getItems() {
        return items;
    }

    public void adcionarProduto(Produto produto, int quantidade) {

        ItemDoPedido item = new ItemDoPedido(produto, quantidade);
        items.add(item);
    }

    public double getValorTotal() {
        double valorTotal = 0;
        for (ItemDoPedido item: items) {
            valorTotal += item.getValorTotal();
        }
        return valorTotal;
    }
}
