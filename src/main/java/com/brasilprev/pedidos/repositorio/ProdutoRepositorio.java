package com.brasilprev.pedidos.repositorio;

import com.brasilprev.pedidos.dominio.produtos.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepositorio extends JpaRepository<Produto, Long> {}
