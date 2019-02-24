package com.brasilprev.pedidos.api.controllers;

import com.brasilprev.pedidos.aplicacao.pedidos.NovoPedido;
import com.brasilprev.pedidos.aplicacao.pedidos.PedidoDeProdutoDto;
import com.brasilprev.pedidos.dominio.pedidos.Pedido;
import com.brasilprev.pedidos.repositorio.PedidoRepositorio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/pedido")
public class PedidoController {

    private final NovoPedido novoPedido;
    private final PedidoRepositorio pedidoRepositorio;

    PedidoController(NovoPedido novoPedido, PedidoRepositorio pedidoRepositorio){

        this.novoPedido = novoPedido;
        this.pedidoRepositorio = pedidoRepositorio;
    }

    @PostMapping
    ResponseEntity<?> post(@RequestBody List<PedidoDeProdutoDto> pedidosDeProduto, Principal principal) {
        Pedido pedido = novoPedido.criar(principal.getName(), pedidosDeProduto);
        return ResponseEntity.created(URI.create("api/pedido/" + pedido.getId())).build();
    }

    @GetMapping("{id}")
    ResponseEntity<?> get(@PathVariable long id) {
        Pedido pedido = pedidoRepositorio.findById(id).orElse(null);
        if(pedido == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(pedido);
    }
}
