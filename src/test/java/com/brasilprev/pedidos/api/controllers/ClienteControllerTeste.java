package com.brasilprev.pedidos.api.controllers;

import com.brasilprev.pedidos.aplicacao.clientes.ArmazenadorDeCliente;
import com.brasilprev.pedidos.aplicacao.clientes.ClienteDto;
import com.brasilprev.pedidos.dominio.clientes.Cliente;
import com.brasilprev.pedidos.repositorio.ClienteRepositorio;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ClienteControllerTeste {

    private ClienteController controller;
    private long clienteId;
    private ClienteDto dto;
    private ArmazenadorDeCliente armazenador;
    private Cliente cliente;
    private ClienteRepositorio repositorio;

    @Before
    public void setUp(){
        clienteId = 10l;
        cliente = mock(Cliente.class);
        when(cliente.getId()).thenReturn(clienteId);
        dto = mock(ClienteDto.class);
        armazenador = mock(ArmazenadorDeCliente.class);
        when(armazenador.armazenar(dto)).thenReturn(cliente);
        repositorio = mock(ClienteRepositorio.class);
        when(repositorio.findByEmail(cliente.getEmail())).thenReturn(cliente);
        controller = new ClienteController(armazenador, repositorio);
    }

    @Test
    public void deve_armazenar_cliente(){
        controller.post(dto);

        verify(armazenador).armazenar(dto);
    }

    @Test
    public void deve_retornar_api_do_cliente_armazenado(){
        ResponseEntity<?> response = controller.post(dto);

        assertEquals(String.format("api/cliente/%s", clienteId), response.getHeaders().get("location").get(0));
    }

    @Test
    public void deve_obter_cliente(){
        when(repositorio.findById(clienteId)).thenReturn(Optional.of(cliente));

        ResponseEntity<?> response = controller.get(clienteId);

        assertEquals(cliente, response.getBody());
    }

    @Test
    public void deve_retornar_nao_encontrado_quando_cliente_nao_existe(){
        ResponseEntity<?> response = controller.get(clienteId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
