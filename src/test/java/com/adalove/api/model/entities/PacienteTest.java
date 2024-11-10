package com.adalove.api.model.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PacienteTest {

    private Paciente paciente;

    @BeforeEach
    void setUp() {
        paciente = new Paciente("Carlos Silva", "Masculino", "12345678900", "A00", 101);
    }

    @Test
    void testConstructor() {
        assertEquals("Carlos Silva", paciente.getNome());
        assertEquals("Masculino", paciente.getSexo());
        assertEquals("12345678900", paciente.getCpf());
        assertEquals("A00", paciente.getCid());
        assertEquals(101, paciente.getIdMedico());
    }

    @Test
    void testSetters() {
        paciente.setNome("Ana Maria");
        paciente.setSexo("Feminino");
        paciente.setCpf("09876543210");
        paciente.setCid("B99");
        paciente.setIdMedico(202);

        assertEquals("Ana Maria", paciente.getNome());
        assertEquals("Feminino", paciente.getSexo());
        assertEquals("09876543210", paciente.getCpf());
        assertEquals("B99", paciente.getCid());
        assertEquals(202, paciente.getIdMedico());
    }
}
