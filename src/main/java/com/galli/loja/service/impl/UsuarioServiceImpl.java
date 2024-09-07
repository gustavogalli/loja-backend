package com.galli.loja.service.impl;

import com.galli.loja.config.security.enums.RoleName;
import com.galli.loja.config.security.enums.Tipo;
import com.galli.loja.config.security.model.RoleModel;
import com.galli.loja.config.security.model.Usuario;
import com.galli.loja.config.security.repository.UserRepository;
import com.galli.loja.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UserRepository repository;

    @Override
    public List<Usuario> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public Usuario getById(Long id) {
        return this.repository.getById(id);
    }

    @Override
    public Usuario save(Usuario usuario) {
        List<RoleModel> roles = List.of(new RoleModel(2L, RoleName.ROLE_USER));
        usuario.setRoles(roles);
        usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
        usuario.setTipo(Tipo.COMPRADOR);
        return this.repository.save(usuario);
    }

    @Override
    public Usuario update(Usuario original, Usuario editado) {
        original.setUsername(editado.getUsername());
        original.setPassword(new BCryptPasswordEncoder().encode(editado.getPassword()));

        return this.repository.save(original);
    }

    @Override
    public Optional<Usuario> findByUsername(Usuario usuario) {
        return this.repository.findByUsername(usuario.getUsername());
    }

    @Override
    public void delete(Usuario usuario) {
        this.repository.deleteById(usuario.getUserId());
    }
}
