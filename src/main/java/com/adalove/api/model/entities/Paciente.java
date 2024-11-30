package com.adalove.api.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Paciente {

    private String nome;
    private String sexo;
    private String cpf;
    private String cid;
    private int idMedico;

}
