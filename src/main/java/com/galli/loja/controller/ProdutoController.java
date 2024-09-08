package com.galli.loja.controller;

import com.galli.loja.domain.Produto;
import com.galli.loja.repository.ProdutoRepository;
import com.galli.loja.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produto")
@CrossOrigin("*")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<List<Produto>> findAll(){
        return ResponseEntity.ok(this.service.findAll());
    }

    @GetMapping("/categoria/{categoria}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<List<Produto>> findByCategoria(@PathVariable String categoria){
        return ResponseEntity.ok(this.service.findAllByCategoria(categoria));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<Produto> findById(@PathVariable Long id){
        return ResponseEntity.ok(this.service.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<Produto> save(@RequestBody Produto produto){
        return ResponseEntity.ok(this.service.save(produto));
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<Produto> update(@RequestBody Produto produto){
        return ResponseEntity.ok(this.service.update(produto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public void deleteById(@PathVariable Long id){
        this.service.deleteById(id);
    }

    @DeleteMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public void deleteAll(){
        this.service.deleteAll();
    }
}
