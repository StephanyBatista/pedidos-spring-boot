package com.brasilprev.pedidos.api.controllers;

import com.brasilprev.pedidos.aplicacao.pedidos.NovoPedido;
import com.brasilprev.pedidos.aplicacao.pedidos.PedidoDeProdutoDto;
import com.brasilprev.pedidos.dominio.pedidos.Pedido;
import com.brasilprev.pedidos.repositorio.PedidoRepositorio;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class PedidoControllerTeste {

    private NovoPedido novoPedido;
    private PedidoRepositorio repositorio;
    private PedidoController controller;
    private List<PedidoDeProdutoDto> dto;
    private Principal principal;
    private String emailDoUsuarioLogado;
    private Pedido pedido;

    @Before
    public void setUp(){
        dto = Arrays.asList();
        pedido = mock(Pedido.class);
        emailDoUsuarioLogado = "teste@teste.com.br";
        principal = mock(Principal.class);
        when(principal.getName()).thenReturn(emailDoUsuarioLogado);
        novoPedido = mock(NovoPedido.class);
        when(novoPedido.criar(emailDoUsuarioLogado, dto)).thenReturn(pedido);
        repositorio = mock(PedidoRepositorio.class);
        controller = new PedidoController(novoPedido, repositorio);
    }

    @Test
    public void deve_criar_pedido(){
        controller.post(dto, principal);

        verify(novoPedido).criar(emailDoUsuarioLogado, dto);
    }

    @Test
    public void deve_retornar_api_do_pedido_criado(){
        ResponseEntity<?> response = controller.post(dto, principal);

        assertEquals(String.format("api/pedido/%s", pedido.getId()), response.getHeaders().get("location").get(0));
    }

    @Test
    public void deve_obter_pedido(){
        final long pedidoId = 10l;
        when(repositorio.findById(pedidoId)).thenReturn(Optional.of(pedido));

        ResponseEntity<?> response = controller.get(pedidoId);

        assertEquals(pedido, response.getBody());
    }

    @Test
    public void deve_retornar_nao_encontrado_quando_pedido_nao_existe(){
        final long pedidoId = 10l;

        ResponseEntity<?> response = controller.get(pedidoId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
