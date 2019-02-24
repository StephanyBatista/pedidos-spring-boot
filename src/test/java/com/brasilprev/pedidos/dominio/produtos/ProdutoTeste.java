package com.brasilprev.pedidos.dominio.produtos;

import com.brasilprev.pedidos.utils.ExcecaoDeNegocio;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class ProdutoTeste {

    private Categoria categoria;
    private String nome;
    private double preco;
    private int quantidade;
    private String descricao;
    private String foto;

    @Before
    public void setUp(){

        nome = "Veja";
        preco = 10.5d;
        quantidade = 10;
        descricao = "";
        foto = "";

        categoria = mock(Categoria.class);
    }

    private Produto criarUmProduto(){
        return new Produto(nome, descricao, preco, quantidade, foto, categoria);
    }

    @Test
    public void deve_criar_produto(){
        Produto produto = criarUmProduto();

        assertEquals(nome, produto.getNome());
        assertEquals(descricao, produto.getDescricao());
        assertEquals(preco, produto.getPreco(), 0);
        assertEquals(quantidade, produto.getQuantidade());
        assertEquals(foto, produto.getFoto());
        assertEquals(categoria, produto.getCategoria());
    }

    @Test
    public void nao_deve_criar_produto_sem_nome(){
        this.nome = null;

        assertThatThrownBy(() -> {
            criarUmProduto();
        })
        .isInstanceOf(ExcecaoDeNegocio.class)
        .hasMessageContaining("Nome é obrigatório");
    }

    @Test
    public void nao_deve_criar_produto_com_nome_vazio(){
        this.nome = "";

        assertThatThrownBy(() -> {
            criarUmProduto();
        })
        .isInstanceOf(ExcecaoDeNegocio.class)
        .hasMessageContaining("Nome é obrigatório");
    }

    @Test
    public void nao_deve_criar_produto_com_preco_menor_que_zero(){
        this.preco = -1;

        assertThatThrownBy(() -> {
            criarUmProduto();
        })
        .isInstanceOf(ExcecaoDeNegocio.class)
        .hasMessageContaining("Preço é inválido");
    }

    @Test
    public void nao_deve_criar_produto_com_quantidade_menor_que_zero(){
        this.quantidade = -1;

        assertThatThrownBy(() -> {
            criarUmProduto();
        })
        .isInstanceOf(ExcecaoDeNegocio.class)
        .hasMessageContaining("Quantidade é inválida");
    }

    @Test
    public void nao_deve_criar_produto_sem_categoria(){
        this.categoria = null;

        assertThatThrownBy(() -> {
            criarUmProduto();
        })
        .isInstanceOf(ExcecaoDeNegocio.class)
        .hasMessageContaining("Categoria é obrigatória");
    }

    @Test
    public void nao_deve_remover_quantidade_de_estoque_maior_que_exista(){

        Produto produto = criarUmProduto();

        assertThatThrownBy(() -> {
            produto.removerQuantidade(produto.getQuantidade() + 1);
        })
        .isInstanceOf(ExcecaoDeNegocio.class)
        .hasMessageContaining("Produto não tem quantidade suficiente em estoque");
    }
}
