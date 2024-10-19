package com.adalove.api.model.entities;

public class Patologia {
    private String cid;
    private int grau;
    private String nome;

    public Patologia(String cid, int grau, String nome) {
        this.cid = cid;
        this.grau = grau;
        this.nome = nome;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public int getGrau() {
        return grau;
    }

    public void setGrau(int grau) {
        this.grau = grau;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Patologia{" +
                "cid=" + cid +
                ", grau=" + grau +
                ", nome='" + nome + '\'' +
                '}';
    }
}
