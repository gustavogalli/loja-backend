package com.galli.loja.domain.util;

import com.galli.loja.config.security.model.UserModel;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public abstract class Usuario {

    public String nome;
    public String email;
    public String senha;
    public String foto;

    @OneToOne
    @JoinColumn(name = "user_id")
    public UserModel user;

}

