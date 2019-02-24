package com.brasilprev.pedidos.api.controllers;

import com.brasilprev.pedidos.aplicacao.clientes.ArmazenadorDeCliente;
import com.brasilprev.pedidos.aplicacao.clientes.ClienteDto;
import com.brasilprev.pedidos.dominio.clientes.Cliente;
import com.brasilprev.pedidos.repositorio.ClienteRepositorio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    private ArmazenadorDeCliente armazenadorDeCliente;
    private ClienteRepositorio repositorio;

    ClienteController(ArmazenadorDeCliente armazenadorDeCliente, ClienteRepositorio repositorio){

        this.armazenadorDeCliente = armazenadorDeCliente;
        this.repositorio = repositorio;
    }

    @PostMapping()
    ResponseEntity<?> post(@RequestBody ClienteDto clienteDto)
    {
        Cliente cliente = armazenadorDeCliente.armazenar(clienteDto);

        return ResponseEntity.created(URI.create("api/cliente/" + cliente.getId())).build();
    }

    @GetMapping("{id}")
    ResponseEntity<?> get(@PathVariable long id){
        Cliente cliente = repositorio.findById(id).orElse(null);

        if(cliente == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(cliente);
    }
}
