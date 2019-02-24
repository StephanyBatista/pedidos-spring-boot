package com.brasilprev.pedidos.aplicacao.produtos;

import com.brasilprev.pedidos.dominio.produtos.Categoria;
import com.brasilprev.pedidos.dominio.produtos.Produto;
import com.brasilprev.pedidos.repositorio.CategoriaRepositorio;
import com.brasilprev.pedidos.repositorio.ProdutoRepositorio;
import com.brasilprev.pedidos.utils.ExcecaoDeNegocio;
import org.springframework.stereotype.Service;

@Service
public class ArmazenadorDeProduto {

    private final ProdutoRepositorio produtoRepositorio;
    private final CategoriaRepositorio categoriaRepositorio;

    public ArmazenadorDeProduto(ProdutoRepositorio produtoRepositorio, CategoriaRepositorio categoriaRepositorio) {

        this.produtoRepositorio = produtoRepositorio;
        this.categoriaRepositorio = categoriaRepositorio;
    }

    public Produto armazenar(ProdutoDto dto) {
        Categoria categoria = categoriaRepositorio.findById(dto.categoriaId).orElse(null);

        ExcecaoDeNegocio.Validar(categoria == null, "Categoria não encontrada");

        Produto produto = new Produto(dto.nome, dto.descricao, dto.preco, dto.quantidade, dto.foto, categoria);
        produtoRepositorio.save(produto);
        return produto;
    }
}
