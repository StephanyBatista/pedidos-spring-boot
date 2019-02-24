package com.brasilprev.pedidos.aplicacao.clientes;

import com.brasilprev.pedidos.dominio.clientes.Cliente;
import com.brasilprev.pedidos.dominio.clientes.Endereco;
import com.brasilprev.pedidos.repositorio.ClienteRepositorio;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ArmazenadorDeCliente {
    private ClienteRepositorio repositorio;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public ArmazenadorDeCliente(ClienteRepositorio repositorio, BCryptPasswordEncoder bCryptPasswordEncoder) {

        this.repositorio = repositorio;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Cliente armazenar(ClienteDto clienteDto) {

        String senhaEncriptada = bCryptPasswordEncoder.encode(clienteDto.senha);
        Endereco endereco = new Endereco(clienteDto.rua, clienteDto.cidade, clienteDto.bairro, clienteDto.cep, clienteDto.estado);
        Cliente cliente = new Cliente(clienteDto.nome, clienteDto.email, senhaEncriptada, endereco);

        repositorio.save(cliente);

        return cliente;
    }
}
