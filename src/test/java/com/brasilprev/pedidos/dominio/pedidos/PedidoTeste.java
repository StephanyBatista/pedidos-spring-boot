package com.brasilprev.pedidos.dominio.pedidos;

import com.brasilprev.pedidos.dominio.clientes.Cliente;
import com.brasilprev.pedidos.dominio.produtos.Produto;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PedidoTeste {

    private Cliente cliente;

    @Before
    public void setUp(){
        cliente = mock(Cliente.class);
    }

    private Pedido criarUmPedido(){
        return new Pedido(cliente);
    }

    @Test
    public void deve_criar_pedido(){
        Pedido pedido = criarUmPedido();

        assertEquals(cliente, pedido.getCliente());
    }

    @Test
    public void deve_pedido_ser_criado_com_status_pedente(){
        Pedido pedido = criarUmPedido();

        assertEquals(StatusDoPedido.PENDENTE, pedido.getStatus());
    }

    @Test
    public void deve_pedido_ser_criado_com_uma_sessao(){
        Pedido pedido = criarUmPedido();

        assertNotNull(pedido.getSessao());
    }

    @Test
    public void deve_adicionar_produto_ao_pedido(){
        final int quantidade = 5;
        Produto produto = mock(Produto.class);
        Pedido pedido = criarUmPedido();

        pedido.adcionarProduto(produto, quantidade);

        assertEquals(produto, pedido.getItems().get(0).getProduto());
        assertEquals(quantidade, pedido.getItems().get(0).getQuantidade());
    }

    @Test
    public void deve_informar_valor_total_do_pedido(){
        final int quantidadeDeVeja = 5;
        Produto vejaProduto = mock(Produto.class);
        when(vejaProduto.getPreco()).thenReturn(5d);
        final int quantidadeDePinho = 3;
        Produto pinhoProduto = mock(Produto.class);
        when(vejaProduto.getPreco()).thenReturn(2d);
        final double valorTotal =
                (vejaProduto.getPreco() * quantidadeDeVeja) + (pinhoProduto.getPreco() * quantidadeDePinho);
        Pedido pedido = criarUmPedido();

        pedido.adcionarProduto(vejaProduto, quantidadeDeVeja);
        pedido.adcionarProduto(pinhoProduto, quantidadeDePinho);

        assertEquals(valorTotal, pedido.getValorTotal(), 0);
    }
}
