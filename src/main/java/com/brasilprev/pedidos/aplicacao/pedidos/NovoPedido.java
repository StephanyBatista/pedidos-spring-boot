package com.brasilprev.pedidos.aplicacao.pedidos;

import com.brasilprev.pedidos.dominio.clientes.Cliente;
import com.brasilprev.pedidos.dominio.pedidos.Pedido;
import com.brasilprev.pedidos.dominio.produtos.Produto;
import com.brasilprev.pedidos.repositorio.ClienteRepositorio;
import com.brasilprev.pedidos.repositorio.PedidoRepositorio;
import com.brasilprev.pedidos.repositorio.ProdutoRepositorio;
import com.brasilprev.pedidos.utils.ExcecaoDeNegocio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NovoPedido {

    private final ProdutoRepositorio produtoRepositorio;
    private final ClienteRepositorio clienteRepositorio;
    private final PedidoRepositorio pedidoRepositorio;

    public NovoPedido(ProdutoRepositorio produtoRepositorio, ClienteRepositorio clienteRepositorio, PedidoRepositorio pedidoRepositorio) {

        this.produtoRepositorio = produtoRepositorio;
        this.clienteRepositorio = clienteRepositorio;
        this.pedidoRepositorio = pedidoRepositorio;
    }

    public Pedido criar(String emailDoCliente, List<PedidoDeProdutoDto> pedidosDeProduto) {

        ExcecaoDeNegocio.Validar(pedidosDeProduto.isEmpty(), "Pelo menos um produto deve ser informado no pedido");

        Cliente cliente = clienteRepositorio.findByEmail(emailDoCliente);
        ExcecaoDeNegocio.Validar(cliente == null, "Cliente não encontrado para efetuar o pedido");

        Pedido pedido = new Pedido(cliente);

        for (PedidoDeProdutoDto item: pedidosDeProduto)
        {
            Produto produto = produtoRepositorio.findById(item.produtoId).orElse(null);
            ExcecaoDeNegocio.Validar(produto == null, "Produto não encontrado para efetuar o pedido");

            produto.removerQuantidade(item.quantidade);
            pedido.adcionarProduto(produto, item.quantidade);
        }

        pedidoRepositorio.save(pedido);

        return pedido;
    }
}
