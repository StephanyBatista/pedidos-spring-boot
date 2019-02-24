package com.brasilprev.pedidos.dominio.clientes;

import com.brasilprev.pedidos.utils.ExcecaoDeNegocio;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class Endereco {

    @NotNull
    private String rua;
    @NotNull
    private String cidade;
    @NotNull
    private String bairro;
    @NotNull
    private String cep;
    @NotNull
    private String estado;

    private Endereco(){}

    public Endereco(String rua, String cidade, String bairro, String cep, String estado) {

        ExcecaoDeNegocio.Validar(rua == null || rua.equals(""), "Rua é obrigatório");
        ExcecaoDeNegocio.Validar(cidade == null || cidade.equals(""), "Cidade é obrigatório");
        ExcecaoDeNegocio.Validar(bairro == null || bairro.equals(""), "Bairro é obrigatório");
        ExcecaoDeNegocio.Validar(cep == null || cep.equals(""), "Cep é obrigatório");
        ExcecaoDeNegocio.Validar(estado == null || estado.equals(""), "Estado é obrigatório");

        this.rua = rua;
        this.cidade = cidade;
        this.bairro = bairro;
        this.cep = cep;
        this.estado = estado;
    }

    public String getRua() {
        return this.rua;
    }

    public String getCidade() {
        return this.cidade;
    }

    public String getBairro() {
        return this.bairro;
    }

    public String getCep() {
        return this.cep;
    }

    public String getEstado() {
        return this.estado;
    }
}
