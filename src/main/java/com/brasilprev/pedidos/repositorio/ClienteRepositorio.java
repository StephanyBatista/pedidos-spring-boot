package com.brasilprev.pedidos.repositorio;

import com.brasilprev.pedidos.dominio.clientes.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

    Cliente findByEmail(String email);
}
