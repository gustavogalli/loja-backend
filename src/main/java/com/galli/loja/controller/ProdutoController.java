package com.galli.loja.controller;

import com.galli.loja.domain.Produto;
import com.galli.loja.repository.ProdutoRepository;
import com.galli.loja.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produto")
@CrossOrigin("*")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public ResponseEntity<List<Produto>> findAll(){
        return ResponseEntity.ok(this.service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Long id){
        return ResponseEntity.ok(this.service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Produto> save(@RequestBody Produto produto){
        return ResponseEntity.ok(this.service.save(produto));
    }

    @PutMapping
    public ResponseEntity<Produto> update(@RequestBody Produto produto){
        return ResponseEntity.ok(this.service.update(produto));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        this.service.deleteById(id);
    }

    @DeleteMapping
    public void deleteAll(){
        this.service.deleteAll();
    }
}
