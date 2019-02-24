package com.brasilprev.pedidos.api.controllers;

import com.brasilprev.pedidos.aplicacao.produtos.ArmazenadorDeProduto;
import com.brasilprev.pedidos.aplicacao.produtos.ProdutoDto;
import com.brasilprev.pedidos.dominio.produtos.Produto;
import com.brasilprev.pedidos.repositorio.ProdutoRepositorio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/produto")
public class ProdutoController {

    private final ArmazenadorDeProduto armazenador;
    private final ProdutoRepositorio repositorio;

    public ProdutoController(ArmazenadorDeProduto armazenador, ProdutoRepositorio repositorio) {

        this.armazenador = armazenador;
        this.repositorio = repositorio;
    }

    @PostMapping
    ResponseEntity<?> post(@RequestBody ProdutoDto dto) {
        Produto produto = armazenador.armazenar(dto);
        return ResponseEntity.created(URI.create("api/produto/" + produto.getId())).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> get(@PathVariable long id) {
        Produto produto = repositorio.findById(id).orElse(null);

        if(produto == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(produto);
    }

    @GetMapping()
    public ResponseEntity<?> get() {
        List<Produto> produtos = repositorio.findAll();

        if(produtos == null || produtos.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(produtos);
    }
}
