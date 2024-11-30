package com.adalove.api.model.entities;

import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Setter
@Getter
public class FichaPaciente {

    private int id;
    private String cpfPaciente;
    private int idFuncionario;
    private String observacoes;
    private LocalDateTime dataHora;

    public FichaPaciente(int id, String cpfPaciente, int idFuncionario, String observacoes, LocalDateTime dataHora) {
        this.id = id;
        this.cpfPaciente = cpfPaciente;
        this.idFuncionario = idFuncionario;
        this.observacoes = observacoes;
        this.dataHora = dataHora;
    }

    public FichaPaciente(String cpfPaciente, int idFuncionario, String observacoes, LocalDateTime dataHora) {
        this.cpfPaciente = cpfPaciente;
        this.idFuncionario = idFuncionario;
        this.observacoes = observacoes;
        this.dataHora = dataHora;
    }

}