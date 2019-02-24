package com.brasilprev.pedidos.api.controllers;

import com.brasilprev.pedidos.aplicacao.produtos.ArmazenadorDeProduto;
import com.brasilprev.pedidos.aplicacao.produtos.ProdutoDto;
import com.brasilprev.pedidos.dominio.produtos.Produto;
import com.brasilprev.pedidos.repositorio.ProdutoRepositorio;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ProdutoControllerTeste {

    private ProdutoDto dto;
    private ArmazenadorDeProduto armazenador;
    private ProdutoRepositorio repositorio;
    private ProdutoController controller;
    private Produto produto;

    @Before
    public void setUp(){
        dto = mock(ProdutoDto.class);
        produto = mock(Produto.class);
        armazenador = mock(ArmazenadorDeProduto.class);
        when(armazenador.armazenar(dto)).thenReturn(produto);
        repositorio = mock(ProdutoRepositorio.class);
        controller = new ProdutoController(armazenador, repositorio);
    }

    @Test
    public void deve_armazenar_prodtuo(){
        controller.post(dto);

        verify(armazenador).armazenar(dto);
    }

    @Test
    public void deve_retornar_api_do_produto_armazenado(){
        ResponseEntity<?> response = controller.post(dto);

        assertEquals(String.format("api/produto/%s", produto.getId()), response.getHeaders().get("location").get(0));
    }

    @Test
    public void deve_obter_produto(){
        final long produtoId = 10l;
        when(repositorio.findById(produtoId)).thenReturn(Optional.of(produto));

        ResponseEntity<?> response = controller.get(produtoId);

        assertEquals(produto, response.getBody());
    }

    @Test
    public void deve_retornar_nao_encontrado_quando_produto_nao_existe(){
        final long produtoId = 10l;

        ResponseEntity<?> response = controller.get(produtoId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void deve_obter_todos_produtos(){
        List<Produto> produtos = Arrays.asList(mock(Produto.class));
        when(repositorio.findAll()).thenReturn(produtos);

        ResponseEntity<?> response = controller.get();

        assertEquals(produtos, response.getBody());
    }

    @Test
    public void deve_retornar_nao_encontrado_quando_nenhum_produto_cadastrado(){
        ResponseEntity<?> response = controller.get();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
