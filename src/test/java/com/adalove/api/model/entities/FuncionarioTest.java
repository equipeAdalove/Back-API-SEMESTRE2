package com.adalove.api.model.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FuncionarioTest {

    private Funcionario funcionario;

    @BeforeEach
    void setUp() {
        funcionario = new Funcionario(1, "João Silva", "Médico", "12345-CRM");
    }

    @Test
    void testConstructorWithAllParameters() {
        assertEquals(1, funcionario.getId());
        assertEquals("João Silva", funcionario.getNome());
        assertEquals("Médico", funcionario.getCargo());
        assertEquals("12345-CRM", funcionario.getCrm());
    }

    @Test
    void testConstructorWithoutId() {
        Funcionario funcionarioSemId = new Funcionario("Ana Souza", "Enfermeira", "67890-CRM");

        assertEquals("Ana Souza", funcionarioSemId.getNome());
        assertEquals("Enfermeira", funcionarioSemId.getCargo());
        assertEquals("67890-CRM", funcionarioSemId.getCrm());
    }

    @Test
    void testSetters() {
        funcionario.setId(2);
        funcionario.setNome("Maria Oliveira");
        funcionario.setCargo("Cirurgião");
        funcionario.setCrm("54321-CRM");

        assertEquals(2, funcionario.getId());
        assertEquals("Maria Oliveira", funcionario.getNome());
        assertEquals("Cirurgião", funcionario.getCargo());
        assertEquals("54321-CRM", funcionario.getCrm());
    }
}
