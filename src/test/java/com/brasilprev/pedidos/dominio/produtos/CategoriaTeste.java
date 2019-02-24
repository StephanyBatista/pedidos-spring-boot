package com.brasilprev.pedidos.dominio.produtos;

import com.brasilprev.pedidos.utils.ExcecaoDeNegocio;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;

public class CategoriaTeste {

    @Test
    public void deve_criar_categoria(){

        String nome = "Limpeza";

        Categoria categoria = new Categoria(nome);

        assertEquals(nome, categoria.getNome());
    }

    @Test
    public void nao_deve_criar_categoria_sem_nome(){

        assertThatThrownBy(() -> {
            new Categoria(null);
        })
        .isInstanceOf(ExcecaoDeNegocio.class)
        .hasMessageContaining("Nome é obrigatório");
    }

    @Test
    public void nao_deve_criar_categoria_com_nome_vazio(){

        assertThatThrownBy(() -> {
            new Categoria("");
        })
        .isInstanceOf(ExcecaoDeNegocio.class)
        .hasMessageContaining("Nome é obrigatório");
    }
}
