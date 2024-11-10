package com.adalove.api.model.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class FichaPacienteTest {

    private FichaPaciente fichaPaciente;
    private LocalDateTime dataHora;

    @BeforeEach
    void setUp() {
        dataHora = LocalDateTime.now();
        fichaPaciente = new FichaPaciente(1, "12345678900", 10, "Observação inicial", dataHora);
    }

    @Test
    void testConstructorWithAllParameters() {
        assertEquals(1, fichaPaciente.getId());
        assertEquals("12345678900", fichaPaciente.getCpfPaciente());
        assertEquals(10, fichaPaciente.getIdFuncionario());
        assertEquals("Observação inicial", fichaPaciente.getObservacoes());
        assertEquals(dataHora, fichaPaciente.getDataHora());
    }

    @Test
    void testConstructorWithoutId() {
        FichaPaciente fichaPacienteSemId = new FichaPaciente("98765432100", 20, "Observação nova", dataHora);

        assertEquals("98765432100", fichaPacienteSemId.getCpfPaciente());
        assertEquals(20, fichaPacienteSemId.getIdFuncionario());
        assertEquals("Observação nova", fichaPacienteSemId.getObservacoes());
        assertEquals(dataHora, fichaPacienteSemId.getDataHora());
    }

    @Test
    void testSetters() {
        fichaPaciente.setId(2);
        fichaPaciente.setCpfPaciente("11111111111");
        fichaPaciente.setIdFuncionario(15);
        fichaPaciente.setObservacoes("Nova observação");
        LocalDateTime novaDataHora = LocalDateTime.now().minusDays(1);
        fichaPaciente.setDataHora(novaDataHora);

        assertEquals(2, fichaPaciente.getId());
        assertEquals("11111111111", fichaPaciente.getCpfPaciente());
        assertEquals(15, fichaPaciente.getIdFuncionario());
        assertEquals("Nova observação", fichaPaciente.getObservacoes());
        assertEquals(novaDataHora, fichaPaciente.getDataHora());
    }
}
