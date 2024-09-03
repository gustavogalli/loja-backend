package com.galli.loja.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String nome;
    public BigDecimal preco;
    public String categoria;
    public String foto;
    public int vendidos;

    @ManyToOne
    @JoinColumn(name = "vendedor_id")
    private Vendedor vendedor;
}

