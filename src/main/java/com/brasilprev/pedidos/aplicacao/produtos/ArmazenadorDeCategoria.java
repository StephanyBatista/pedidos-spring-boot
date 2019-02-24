package com.brasilprev.pedidos.aplicacao.produtos;

import com.brasilprev.pedidos.dominio.produtos.Categoria;
import com.brasilprev.pedidos.repositorio.CategoriaRepositorio;
import org.springframework.stereotype.Service;

@Service
public class ArmazenadorDeCategoria {

    private CategoriaRepositorio repositorio;

    public ArmazenadorDeCategoria(CategoriaRepositorio repositorio) {

        this.repositorio = repositorio;
    }

    public Categoria armazenar(CategoriaDto dto) {

        Categoria categoria = new Categoria(dto.nome);
        repositorio.save(categoria);

        return categoria;
    }
}
