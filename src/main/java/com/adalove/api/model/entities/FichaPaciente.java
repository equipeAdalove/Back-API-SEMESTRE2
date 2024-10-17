package org.example.model.entities;

public class FichaPaciente {

    private int id;
    private String cpfPaciente;
    private int idFuncionario;
    private String observacoes;

    public FichaPaciente(int id, String cpfPaciente, int idFuncionario, String observacoes) {
        this.id = id;
        this.cpfPaciente = cpfPaciente;
        this.idFuncionario = idFuncionario;
        this.observacoes = observacoes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpfPaciente() {
        return cpfPaciente;
    }

    public void setCpfPaciente(String cpfPaciente) {
        this.cpfPaciente = cpfPaciente;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}

