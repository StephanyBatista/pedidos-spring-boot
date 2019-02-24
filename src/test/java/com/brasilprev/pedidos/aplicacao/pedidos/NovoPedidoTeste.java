package com.brasilprev.pedidos.aplicacao.pedidos;

import com.brasilprev.pedidos.dominio.clientes.Cliente;
import com.brasilprev.pedidos.dominio.pedidos.Pedido;
import com.brasilprev.pedidos.dominio.produtos.Produto;
import com.brasilprev.pedidos.repositorio.ClienteRepositorio;
import com.brasilprev.pedidos.repositorio.PedidoRepositorio;
import com.brasilprev.pedidos.repositorio.ProdutoRepositorio;
import com.brasilprev.pedidos.utils.ExcecaoDeNegocio;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class NovoPedidoTeste {

    private String emailDoCliente;
    private ProdutoRepositorio produtoRepositorio;
    private ClienteRepositorio clienteRepositorio;
    private List<PedidoDeProdutoDto> pedidosDeProduto;
    private Produto produto;
    private Cliente cliente;
    private NovoPedido novoPedido;
    private PedidoRepositorio pedidoRepositorio;

    @Before
    public void setUp(){
        emailDoCliente = "teste@teste.com.br";
        PedidoDeProdutoDto pedidoDeProdutoDto = new PedidoDeProdutoDto();
        pedidoDeProdutoDto.produtoId = 51;
        pedidoDeProdutoDto.quantidade = 3;
        produto = mock(Produto.class);
        cliente = mock(Cliente.class);

        produtoRepositorio = mock(ProdutoRepositorio.class);
        when(produtoRepositorio.findById(pedidoDeProdutoDto.produtoId)).thenReturn(Optional.of(produto));
        clienteRepositorio = mock(ClienteRepositorio.class);
        when(clienteRepositorio.findByEmail(emailDoCliente)).thenReturn(cliente);
        pedidoRepositorio = mock(PedidoRepositorio.class);

        pedidosDeProduto = Arrays.asList(pedidoDeProdutoDto);
        novoPedido = new NovoPedido(produtoRepositorio, clienteRepositorio, pedidoRepositorio);
    }

    @Test
    public void deve_criar_pedido(){
        Pedido pedido = novoPedido.criar(emailDoCliente, pedidosDeProduto);

        assertEquals(cliente, pedido.getCliente());
    }

    @Test
    public void deve_adicionar_produtos_ao_pedido(){
        Pedido pedido = novoPedido.criar(emailDoCliente, pedidosDeProduto);

        assertEquals(produto, pedido.getItems().get(0).getProduto());
        assertEquals(pedidosDeProduto.get(0).quantidade, pedido.getItems().get(0).getQuantidade());
    }

    @Test
    public void deve_armazenar_pedido(){
        Pedido pedido = novoPedido.criar(emailDoCliente, pedidosDeProduto);

        verify(pedidoRepositorio).save(pedido);
    }

    @Test
    public void nao_deve_criar_pedido_sem_produtos(){
        pedidosDeProduto = Arrays.asList();

        assertThatThrownBy(() -> {
            novoPedido.criar(emailDoCliente, pedidosDeProduto);
        })
        .isInstanceOf(ExcecaoDeNegocio.class)
        .hasMessageContaining("Pelo menos um produto deve ser informado no pedido");
    }

    @Test
    public void nao_deve_criar_pedido_quando_cliente_nao_encontrado(){
        final String emailDoClienteQueNaoExiste = "email@invalido.com.br";

        assertThatThrownBy(() -> {
            novoPedido.criar(emailDoClienteQueNaoExiste, pedidosDeProduto);
        })
        .isInstanceOf(ExcecaoDeNegocio.class)
        .hasMessageContaining("Cliente não encontrado para efetuar o pedido");
    }

    @Test
    public void nao_deve_criar_pedido_quando_produto_nao_encontrado(){
        final long produtoInvalido = 100;
        pedidosDeProduto.get(0).produtoId = produtoInvalido;

        assertThatThrownBy(() -> {
            novoPedido.criar(emailDoCliente, pedidosDeProduto);
        })
        .isInstanceOf(ExcecaoDeNegocio.class)
        .hasMessageContaining("Produto não encontrado para efetuar o pedido");
    }

    @Test
    public void deve_remover_quantidade_de_produto_disponivel(){
        novoPedido.criar(emailDoCliente, pedidosDeProduto);

        verify(produto).removerQuantidade(pedidosDeProduto.get(0).quantidade);
    }
}
