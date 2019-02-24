package com.brasilprev.pedidos.utils;

public class ExcecaoDeNegocio extends RuntimeException {

    ExcecaoDeNegocio(String mensagem)
    {
        super(mensagem);
    }

    public static void Validar(Boolean validacao, String mensagemDeErro){
        if(validacao)
            throw new ExcecaoDeNegocio(mensagemDeErro);
    }
}
