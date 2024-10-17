package org.example.model.entities;

public class Funcionario {

    private String nome;
    private String cargo;
    private String crm;

    public Funcionario(String nome, String cargo, String crm) {
        this.nome = nome;
        this.cargo = cargo;
        this.crm = crm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }
}
