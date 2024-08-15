package com.galli.loja.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Vendedor extends Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "comprador_id")
    public List<Produto> estoque = new ArrayList<>();


}
