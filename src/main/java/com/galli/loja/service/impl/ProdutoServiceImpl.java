package com.galli.loja.service.impl;

import com.galli.loja.domain.Produto;
import com.galli.loja.repository.ProdutoRepository;
import com.galli.loja.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public List<Produto> findAll(){
        return this.repository.findAll();
    }

    @Override
    public List<Produto> findAllByCategoria(String categoria) {
        return this.repository.findAllByCategoria(categoria);
    }

    @Override
    public List<String> findAllCategorias(){
        return this.repository.findAllCategorias();
    }

    public Produto findById(Long id){
        return this.repository.findById(id).orElse(null);
    }

    public Produto save(Produto produto) {
        return this.repository.save(produto);
    }

    public Produto update(Produto produto) {
        return this.repository.save(produto);
    }

    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }

    public void deleteAll() {
        this.repository.deleteAll();
    }
}
