package com.galli.loja.controller;

import com.galli.loja.config.security.model.UserModel;
import com.galli.loja.config.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UserRepository repository;

    @GetMapping
    public ResponseEntity<List<UserModel>> findAll(){
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> findById(@PathVariable UUID id){
        return ResponseEntity.ok(this.repository.getById(id));
    }
}
