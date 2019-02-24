package com.brasilprev.pedidos.aplicacao.clientes;

import com.brasilprev.pedidos.dominio.clientes.Cliente;
import com.brasilprev.pedidos.repositorio.ClienteRepositorio;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ArmazenadorDeClienteTeste {

    private ClienteDto clienteDto;
    private ClienteRepositorio repositorio;
    private ArmazenadorDeCliente armazenadorDeCliente;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    final String senhaEncriptada = "XPTIRAQW$E#DMFD";

    @Before
    public void setUp(){

        clienteDto = new ClienteDto();
        clienteDto.nome = "João Maria";
        clienteDto.email = "joaomaria@gmail.com";
        clienteDto.senha = "123456";
        clienteDto.rua = "Avenida";
        clienteDto.bairro = "Bairro";
        clienteDto.cep = "12331";
        clienteDto.cidade = "Campo Grande";
        clienteDto.estado = "Estado";

        repositorio = mock(ClienteRepositorio.class);
        bCryptPasswordEncoder = mock(BCryptPasswordEncoder.class);
        when(bCryptPasswordEncoder.encode(clienteDto.senha)).thenReturn(senhaEncriptada);
        armazenadorDeCliente = new ArmazenadorDeCliente(repositorio, bCryptPasswordEncoder);
    }

    @Test
    public void deve_armazenar_cliente(){

        Cliente cliente = armazenadorDeCliente.armazenar(clienteDto);

        verify(repositorio).save(cliente);
    }

    @Test
    public void deve_senha_ser_encriptada(){

        Cliente cliente = armazenadorDeCliente.armazenar(clienteDto);

        assertEquals(senhaEncriptada, cliente.getSenha());
    }
}
