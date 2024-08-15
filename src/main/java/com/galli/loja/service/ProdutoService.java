package com.galli.loja.service;

import com.galli.loja.domain.Produto;

import java.util.List;

public interface ProdutoService {

    List<Produto> findAll();

    Produto findById(Long id);

    Produto save(Produto produto);

    Produto update(Produto produto);

    void deleteById(Long id);

    void deleteAll();
}
