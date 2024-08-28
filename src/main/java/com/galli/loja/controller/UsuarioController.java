package com.galli.loja.controller;

import com.galli.loja.config.security.enums.RoleName;
import com.galli.loja.config.security.model.RoleModel;
import com.galli.loja.config.security.model.UserModel;
import com.galli.loja.config.security.repository.UserRepository;
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

    @GetMapping
    public ResponseEntity<List<UserModel>> findAll(){
        return new ResponseEntity<>(this.repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> findById(@PathVariable Long id){
        return new ResponseEntity<>(this.repository.getById(id), HttpStatus.OK);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel user){
        if(this.repository.findByUsername(user.getUsername()).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!");
        } else {
            List<RoleModel> roles = List.of(new RoleModel(2L, RoleName.ROLE_USER));
            user.setRoles(roles);
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            UserModel savedUser = this.repository.save(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserModel loginRequest) {
        Optional<UserModel> user = repository.findByUsername(loginRequest.getUsername());

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
    public ResponseEntity<UserModel> updateUser(@PathVariable Long id, @RequestBody UserModel userModel){
        UserModel user = this.repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado."));

        user.setUsername(userModel.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
//        user.setRoles(userModel.getRoles());

        UserModel updatedUser = this.repository.save(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        UserModel user = this.repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado."));

        this.repository.delete(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
