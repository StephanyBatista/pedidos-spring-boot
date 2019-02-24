package com.brasilprev.pedidos.aplicacao.produtos;

import com.brasilprev.pedidos.dominio.produtos.Categoria;
import com.brasilprev.pedidos.repositorio.CategoriaRepositorio;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ArmazenadorDeCategoriaTeste {

    private ArmazenadorDeCategoria armazenador;
    private CategoriaRepositorio repositorio;

    @Before
    public void setUp(){

        repositorio = mock(CategoriaRepositorio.class);
        armazenador = new ArmazenadorDeCategoria(repositorio);
    }

    @Test
    public void deve_armazenar_categoria(){

        CategoriaDto dto = new CategoriaDto();
        dto.nome = "Limpeza";

        Categoria categoria = armazenador.armazenar(dto);

        verify(repositorio).save(categoria);
    }
}
