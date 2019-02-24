package com.brasilprev.pedidos.aplicacao.produtos;

import com.brasilprev.pedidos.dominio.produtos.Categoria;
import com.brasilprev.pedidos.dominio.produtos.Produto;
import com.brasilprev.pedidos.repositorio.CategoriaRepositorio;
import com.brasilprev.pedidos.repositorio.ProdutoRepositorio;
import com.brasilprev.pedidos.utils.ExcecaoDeNegocio;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class ArmazenadorDeProdutoTeste {

    private ProdutoRepositorio produtoRepositorio;
    private CategoriaRepositorio categoriaRepositorio;
    private ArmazenadorDeProduto armazenadorDeProduto;
    private ArgumentCaptor<Produto> produtoArgumento;
    private ProdutoDto dto;
    private Categoria categoria;

    @Before
    public void setUp(){

        dto = new ProdutoDto();
        dto.nome = "Veja";
        dto.preco = 10;
        dto.quantidade = 11;
        dto.categoriaId = 54;
        categoria = mock(Categoria.class);
        produtoArgumento = ArgumentCaptor.forClass(Produto.class);
        produtoRepositorio = mock(ProdutoRepositorio.class);
        categoriaRepositorio = mock(CategoriaRepositorio.class);
        armazenadorDeProduto = new ArmazenadorDeProduto(produtoRepositorio, categoriaRepositorio);
    }

    @Test
    public void deve_armazenar_cliente(){

        when(categoriaRepositorio.findById(dto.categoriaId)).thenReturn(Optional.of(categoria));

        Produto produto = armazenadorDeProduto.armazenar(dto);

        verify(produtoRepositorio).save(produto);
    }

    @Test
    public void nao_deve_armazenar_prodito_quando_categoria_nao_encontrada(){

        assertThatThrownBy(() -> {
            armazenadorDeProduto.armazenar(dto);
        })
        .isInstanceOf(ExcecaoDeNegocio.class)
        .hasMessageContaining("Categoria não encontrada");
    }
}
