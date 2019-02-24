package com.brasilprev.pedidos.dominio.clientes;

import com.brasilprev.pedidos.dominio.clientes.Endereco;
import com.brasilprev.pedidos.utils.ExcecaoDeNegocio;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;

public class EnderecoTeste {

    private String rua;
    private String cidade;
    private String bairro;
    private String cep;
    private String estado;

    @Before
    public void setUp(){
        this.rua = "Abacaxi";
        this.cidade = "Campo Grande";
        this.bairro = "Estrela do Sul";
        this.cep = "79033231";
        this.estado = "MS";
    }

    public Endereco criarUmEndereco(){
        return new Endereco(rua, cidade, bairro, cep,  estado);
    }

    @Test
    public void deve_criar() {
        Endereco endereco = criarUmEndereco();

        assertEquals(rua, endereco.getRua());
        assertEquals(cidade, endereco.getCidade());
        assertEquals(bairro, endereco.getBairro());
        assertEquals(cep, endereco.getCep());
        assertEquals(estado, endereco.getEstado());
    }

    @Test()
    public void nao_deve_criar_sem_rua(){
        this.rua = null;

        assertThatThrownBy(() -> {
            criarUmEndereco();
        })
        .isInstanceOf(ExcecaoDeNegocio.class)
        .hasMessageContaining("Rua é obrigatório");
    }

    @Test()
    public void nao_deve_criar_com_rua_vazia(){
        this.rua = "";

        assertThatThrownBy(() -> {
            criarUmEndereco();
        })
                .isInstanceOf(ExcecaoDeNegocio.class)
                .hasMessageContaining("Rua é obrigatório");
    }

    @Test()
    public void nao_deve_criar_sem_cidade(){
        this.cidade = null;

        assertThatThrownBy(() -> {
            criarUmEndereco();
        })
        .isInstanceOf(ExcecaoDeNegocio.class)
        .hasMessageContaining("Cidade é obrigatório");
    }

    @Test()
    public void nao_deve_criar_com_cidade_vazia(){
        this.cidade = "";

        assertThatThrownBy(() -> {
            criarUmEndereco();
        })
        .isInstanceOf(ExcecaoDeNegocio.class)
        .hasMessageContaining("Cidade é obrigatório");
    }

    @Test()
    public void nao_deve_criar_sem_bairro(){
        this.bairro = null;

        assertThatThrownBy(() -> {
            criarUmEndereco();
        })
        .isInstanceOf(ExcecaoDeNegocio.class)
        .hasMessageContaining("Bairro é obrigatório");
    }

    @Test()
    public void nao_deve_criar_com_bairro_vazio(){
        this.bairro = "";

        assertThatThrownBy(() -> {
            criarUmEndereco();
        })
        .isInstanceOf(ExcecaoDeNegocio.class)
        .hasMessageContaining("Bairro é obrigatório");
    }

    @Test()
    public void nao_deve_criar_sem_cep(){
        this.cep = null;

        assertThatThrownBy(() -> {
            criarUmEndereco();
        })
        .isInstanceOf(ExcecaoDeNegocio.class)
        .hasMessageContaining("Cep é obrigatório");
    }

    @Test()
    public void nao_deve_criar_com_cep_vazio(){
        this.cep = "";

        assertThatThrownBy(() -> {
            criarUmEndereco();
        })
        .isInstanceOf(ExcecaoDeNegocio.class)
        .hasMessageContaining("Cep é obrigatório");
    }

    @Test()
    public void nao_deve_criar_sem_estado(){
        this.estado = null;

        assertThatThrownBy(() -> {
            criarUmEndereco();
        })
        .isInstanceOf(ExcecaoDeNegocio.class)
        .hasMessageContaining("Estado é obrigatório");
    }

    @Test()
    public void nao_deve_criar_com_estado_vazio(){
        this.estado = "";

        assertThatThrownBy(() -> {
            criarUmEndereco();
        })
        .isInstanceOf(ExcecaoDeNegocio.class)
        .hasMessageContaining("Estado é obrigatório");
    }
}
