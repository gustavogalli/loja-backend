package com.galli.loja.controller;

import com.galli.loja.config.security.enums.RoleName;
import com.galli.loja.config.security.model.RoleModel;
import com.galli.loja.config.security.model.Usuario;
import com.galli.loja.config.security.repository.UserRepository;
import com.galli.loja.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import java.util.List;
import java.lang.Long;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UsuarioService service;

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll(){
        return new ResponseEntity<>(this.service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id){
        return new ResponseEntity<>(this.service.getById(id), HttpStatus.OK);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> createUser(@RequestBody Usuario usuario){
        if(this.repository.findByUsername(usuario.getUsername()).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!");
        } else {
            return new ResponseEntity<>(this.service.save(usuario), HttpStatus.CREATED);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Usuario loginRequest) {
        Optional<Usuario> user = this.service.findByUsername(loginRequest);

        if(user.isPresent()){
            if (new BCryptPasswordEncoder().matches(loginRequest.getPassword(), user.get().getPassword())) {
                return ResponseEntity.ok(user);
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
    }

    @GetMapping("/logout")
    public void logout() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            SecurityContextHolder.clearContext();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUser(@PathVariable Long id, @RequestBody Usuario usuario){
        Usuario original = this.service.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado."));

        this.service.update(original, usuario);

        return new ResponseEntity<>(this.service.update(original, usuario), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        Usuario user = this.service.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado."));

        this.service.delete(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
