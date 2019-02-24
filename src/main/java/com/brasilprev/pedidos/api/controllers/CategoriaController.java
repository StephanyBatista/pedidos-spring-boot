package com.brasilprev.pedidos.api.controllers;

import com.brasilprev.pedidos.aplicacao.produtos.ArmazenadorDeCategoria;
import com.brasilprev.pedidos.aplicacao.produtos.CategoriaDto;
import com.brasilprev.pedidos.dominio.produtos.Categoria;
import com.brasilprev.pedidos.repositorio.CategoriaRepositorio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    private final ArmazenadorDeCategoria armazenador;
    private final CategoriaRepositorio repositorio;

    public CategoriaController(ArmazenadorDeCategoria armazenador, CategoriaRepositorio repositorio) {

        this.armazenador = armazenador;
        this.repositorio = repositorio;
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody CategoriaDto dto) {
        Categoria categoria = armazenador.armazenar(dto);
        return ResponseEntity.created(URI.create("api/categoria/" + categoria.getId())).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> get(@PathVariable long id) {
        Categoria categoria = repositorio.findById(id).orElse(null);

        if(categoria == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(categoria);
    }
}
