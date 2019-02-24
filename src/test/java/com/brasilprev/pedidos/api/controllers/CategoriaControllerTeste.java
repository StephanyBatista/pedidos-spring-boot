package com.brasilprev.pedidos.api.controllers;

import com.brasilprev.pedidos.aplicacao.produtos.ArmazenadorDeCategoria;
import com.brasilprev.pedidos.aplicacao.produtos.CategoriaDto;
import com.brasilprev.pedidos.dominio.produtos.Categoria;
import com.brasilprev.pedidos.repositorio.CategoriaRepositorio;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CategoriaControllerTeste {

    private CategoriaRepositorio repositorio;
    private ArmazenadorDeCategoria armazenador;
    private CategoriaController controller;
    private CategoriaDto dto;
    private long categoriaId;
    private Categoria categoria;

    @Before
    public void setUp(){

        categoriaId = 54l;
        dto = new CategoriaDto();
        categoria = mock(Categoria.class);
        when(categoria.getId()).thenReturn(categoriaId);
        repositorio = mock(CategoriaRepositorio.class);
        when(repositorio.findByNome(dto.nome)).thenReturn(categoria);
        armazenador = mock(ArmazenadorDeCategoria.class);
        when(armazenador.armazenar(dto)).thenReturn(categoria);
        controller = new CategoriaController(armazenador, repositorio);

    }

    @Test
    public void deve_armazenar_categoria(){
        controller.post(dto);

        verify(armazenador).armazenar(dto);
    }

    @Test
    public void deve_retornar_api_do_cliente_armazenado(){
        ResponseEntity<?> response = controller.post(dto);

        assertEquals(String.format("api/categoria/%s", categoriaId), response.getHeaders().get("location").get(0));
    }

    @Test
    public void deve_obter_categoria(){
        when(repositorio.findById(categoriaId)).thenReturn(Optional.of(categoria));

        ResponseEntity<?> response = controller.get(categoriaId);

        assertEquals(categoria, response.getBody());
    }

    @Test
    public void deve_retornar_nao_encontrado_quando_categoria_nao_existe(){
        ResponseEntity<?> response = controller.get(categoriaId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
