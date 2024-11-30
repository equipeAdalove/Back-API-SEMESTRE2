package com.adalove.api.model.entities;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Patologia {
    private String cid;
    private int grau;
    private String nome;

}
