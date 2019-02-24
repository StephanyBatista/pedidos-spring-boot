package com.brasilprev.pedidos.api.seguranca;

import com.brasilprev.pedidos.dominio.clientes.Cliente;
import com.brasilprev.pedidos.repositorio.ClienteRepositorio;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import static java.util.Collections.emptyList;

@Component
public class ValidadorDeUsuario implements UserDetailsService {

    private ClienteRepositorio repositorio;

    ValidadorDeUsuario(ClienteRepositorio repositorio){

        this.repositorio = repositorio;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Cliente cliente = repositorio.findByEmail(email);

        if(cliente == null)
            throw new UsernameNotFoundException(email);

        return new User(cliente.getEmail(), cliente.getSenha(), emptyList());
    }
}
