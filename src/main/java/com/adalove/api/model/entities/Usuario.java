package com.adalove.api.model.entities;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Usuario {
    private int id;
    private String username;
    private String password;
    private boolean administrador;


    // Construtor sem ID, para novos usuários, com senha em hash
    public Usuario(String username, String password, boolean administrador) {
        this.username = username;
        this.password = password;
        this.administrador = administrador;
    }



}
