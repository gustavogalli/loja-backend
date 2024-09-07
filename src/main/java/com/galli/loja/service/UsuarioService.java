package com.galli.loja.service;

import com.galli.loja.config.security.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> findAll();

    Optional<Usuario> findById(Long id);

    Usuario getById(Long id);

    Usuario save(Usuario usuario);

    Usuario update(Usuario original, Usuario editado);

    Optional<Usuario> findByUsername(Usuario usuario);

    void delete(Usuario usuario);
}
