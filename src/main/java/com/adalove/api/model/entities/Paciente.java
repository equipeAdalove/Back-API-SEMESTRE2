package org.example.model.entities;

public class Paciente {

    private String nome;
    private String sexo;
    private String cpf;
    private String cid;
    private int idMedico;

    public Paciente(String nome, String sexo, String cpf, String cid, int idMedico) {
        this.nome = nome;
        this.sexo = sexo;
        this.cpf = cpf;
        this.cid = cid;
        this.idMedico = idMedico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }
}
