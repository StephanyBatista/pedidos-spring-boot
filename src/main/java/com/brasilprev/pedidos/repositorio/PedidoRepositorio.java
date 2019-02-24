package com.brasilprev.pedidos.repositorio;

import com.brasilprev.pedidos.dominio.pedidos.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepositorio extends JpaRepository<Pedido, Long> {}
