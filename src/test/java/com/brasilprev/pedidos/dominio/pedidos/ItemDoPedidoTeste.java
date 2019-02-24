package com.brasilprev.pedidos.dominio.pedidos;

import com.brasilprev.pedidos.dominio.produtos.Produto;
import com.brasilprev.pedidos.utils.ExcecaoDeNegocio;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ItemDoPedidoTeste {

    private Produto produto;
    private int quantidade;

    @Before
    public void setUp(){
        produto = mock(Produto.class);
        when(produto.getPreco()).thenReturn(133.5d);
        quantidade = 10;
    }

    private ItemDoPedido criarUmItemDoPedido(){
        return new ItemDoPedido(produto, quantidade);
    }

    @Test
    public void deve_criar_item_do_pedido(){
        ItemDoPedido item = criarUmItemDoPedido();

        assertEquals(produto, item.getProduto());
        assertEquals(quantidade, item.getQuantidade());
    }

    @Test
    public void deve_infomar_valor_total(){
        final double valorTotal = quantidade * produto.getPreco();

        ItemDoPedido item = criarUmItemDoPedido();

        assertEquals(valorTotal, item.getValorTotal(), 0);
    }

    @Test
    public void nao_deve_criar_sem_produto(){
        produto = null;

        assertThatThrownBy(() -> {
            criarUmItemDoPedido();
        })
        .isInstanceOf(ExcecaoDeNegocio.class)
        .hasMessageContaining("Produto é obrigatório");
    }

    @Test
    public void nao_deve_criar_com_quantidade_zero(){
        quantidade = 0;

        assertThatThrownBy(() -> {
            criarUmItemDoPedido();
        })
        .isInstanceOf(ExcecaoDeNegocio.class)
        .hasMessageContaining("Quantidade informada é inválida");
    }

    @Test
    public void nao_deve_criar_com_quantidade_meor_que_zero(){
        quantidade = -1;

        assertThatThrownBy(() -> {
            criarUmItemDoPedido();
        })
        .isInstanceOf(ExcecaoDeNegocio.class)
        .hasMessageContaining("Quantidade informada é inválida");
    }
}
