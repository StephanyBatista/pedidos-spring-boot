package com.brasilprev.pedidos.repositorio;

import com.brasilprev.pedidos.dominio.produtos.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepositorio  extends JpaRepository<Categoria, Long> {

    Categoria findByNome(String nome);
}
