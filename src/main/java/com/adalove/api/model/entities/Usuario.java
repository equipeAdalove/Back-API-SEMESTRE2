package com.adalove.api.model.entities;

public class Usuario {
    private int id;
    private String username;
    private String password;  // A senha será armazenada em formato hash
    private boolean administrador;

    // Construtor padrão
    public Usuario() {}

    // Construtor completo com ID, username, senha (hash) e status administrador
    public Usuario(int id, String username, String password, boolean administrador) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.administrador = administrador;
    }

    // Construtor sem ID, para novos usuários, com senha em hash
    public Usuario(String username, String password, boolean administrador) {
        this.username = username;
        this.password = password;
        this.administrador = administrador;
    }


    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    // Método toString para exibir as informações do usuário
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", administrador=" + administrador +
                '}';
    }
}
