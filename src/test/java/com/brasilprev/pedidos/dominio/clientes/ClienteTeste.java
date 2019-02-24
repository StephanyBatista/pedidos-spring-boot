package com.brasilprev.pedidos.dominio.clientes;

import com.brasilprev.pedidos.utils.ExcecaoDeNegocio;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class ClienteTeste {

    private String nome;
    private String email;
    private Endereco endereco;
    private String senha;

    @Before
    public void setUp(){
        nome = "João Maria";
        email = "joaomaria@gmail.com";
        senha = "@#$%aa";
        endereco = mock(Endereco.class);
    }

    private Cliente criarUmCliente(){ return new Cliente(nome, email, senha, endereco); }

    @Test
    public void deve_criar_cliente(){

        Cliente cliente = criarUmCliente();

        assertEquals(nome, cliente.getNome());
        assertEquals(email, cliente.getEmail());
        assertEquals(senha, cliente.getSenha());
        assertEquals(endereco, cliente.getEndereco());
    }

    @Test()
    public void nao_deve_criar_sem_nome(){
        this.nome = null;

        assertThatThrownBy(() -> {
            criarUmCliente();
        })
        .isInstanceOf(ExcecaoDeNegocio.class)
        .hasMessageContaining("Nome é obrigatório");
    }

    @Test()
    public void nao_deve_criar_com_nome_vazio(){
        this.nome = "";

        assertThatThrownBy(() -> {
            criarUmCliente();
        })
        .isInstanceOf(ExcecaoDeNegocio.class)
        .hasMessageContaining("Nome é obrigatório");
    }

    @Test()
    public void nao_deve_criar_sem_email(){
        this.email = null;

        assertThatThrownBy(() -> {
            criarUmCliente();
        })
        .isInstanceOf(ExcecaoDeNegocio.class)
        .hasMessageContaining("Email é obrigatório");
    }

    @Test()
    public void nao_deve_criar_com_email_vazio(){
        this.email = "";

        assertThatThrownBy(() -> {
            criarUmCliente();
        })
        .isInstanceOf(ExcecaoDeNegocio.class)
        .hasMessageContaining("Email é obrigatório");
    }

    @Test()
    public void nao_deve_criar_sem_senha(){
        this.senha = null;

        assertThatThrownBy(() -> {
            criarUmCliente();
        })
        .isInstanceOf(ExcecaoDeNegocio.class)
        .hasMessageContaining("Senha é obrigatório");
    }

    @Test()
    public void nao_deve_criar_com_senha_vazia(){
        this.senha = "";

        assertThatThrownBy(() -> {
            criarUmCliente();
        })
        .isInstanceOf(ExcecaoDeNegocio.class)
        .hasMessageContaining("Senha é obrigatório");
    }

    @Test()
    public void nao_deve_criar_senha_com_menos_de_6_caracteres(){
        this.senha = "12345";

        assertThatThrownBy(() -> {
            criarUmCliente();
        })
        .isInstanceOf(ExcecaoDeNegocio.class)
        .hasMessageContaining("Senha deve ter no mínio 6 caracteres");
    }

    @Test()
    public void nao_deve_criar_sem_endereco(){
        this.endereco = null;

        assertThatThrownBy(() -> {
            criarUmCliente();
        })
        .isInstanceOf(ExcecaoDeNegocio.class)
        .hasMessageContaining("Endereco é obrigatório");
    }
}
