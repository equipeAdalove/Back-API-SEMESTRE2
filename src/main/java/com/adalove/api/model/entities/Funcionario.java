package com.adalove.api.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Funcionario {

    private int id;
    private String nome;
    private String cargo;
    private String crm;


    public Funcionario(String nome, String cargo, String crm) {
        this.nome = nome;
        this.cargo = cargo;
        this.crm = crm;
    }

}
