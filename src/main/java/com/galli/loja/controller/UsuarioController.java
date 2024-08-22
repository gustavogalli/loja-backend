package com.galli.loja.controller;

import com.galli.loja.config.security.model.UserModel;
import com.galli.loja.config.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UserRepository repository;

    @GetMapping
    public ResponseEntity<List<UserModel>> findAll(){
        return new ResponseEntity<>(this.repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> findById(@PathVariable UUID id){
        return new ResponseEntity<>(this.repository.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        UserModel savedUser = this.repository.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserModel> updateUser(@PathVariable UUID id, @RequestBody UserModel userModel){
        UserModel user = this.repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado."));

        user.setUsername(userModel.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRoles(userModel.getRoles());

        UserModel updatedUser = this.repository.save(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        UserModel user = this.repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado."));

        this.repository.delete(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
