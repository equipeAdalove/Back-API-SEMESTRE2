package com.adalove.api.model.dao;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import java.sql.*;
import java.util.List;

// Importe a classe Paciente
import com.adalove.api.model.entities.Paciente;

public class PacienteDAOTest {

    private PacienteDAO pacienteDAO;
    private Connection conn;

    @Before
    public void setUp() throws SQLException {
        // Configuração do banco de dados em memória (H2)
        conn = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "sa", "");
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE paciente (" +
                "nome VARCHAR(255), " +
                "sexo VARCHAR(10), " +
                "cpf_paciente VARCHAR(14) PRIMARY KEY, " +
                "codigo_cid VARCHAR(10), " +
                "id_medico INT)");

        pacienteDAO = new PacienteDAO();
    }

    @Test
    public void testCreate() {
        Paciente paciente = new Paciente("João", "M", "12345678901", "A00", 1);
        pacienteDAO.create(paciente);

        Paciente pacienteDb = pacienteDAO.buscarPorCpf("12345678901");
        assertNotNull(pacienteDb);
        assertEquals("João", pacienteDb.getNome());
        assertEquals("M", pacienteDb.getSexo());
        assertEquals("12345678901", pacienteDb.getCpf());
        assertEquals("A00", pacienteDb.getCid());
    }

    @Test
    public void testRead() {
        Paciente paciente1 = new Paciente("João", "M", "12345678901", "A00", 1);
        Paciente paciente2 = new Paciente("Maria", "F", "98765432100", "B01", 2);

        pacienteDAO.create(paciente1);
        pacienteDAO.create(paciente2);

        List<Paciente> pacientes = pacienteDAO.read();
        assertEquals(2, pacientes.size());
    }

    @Test
    public void testBuscarPorCpf() {
        Paciente paciente = new Paciente("João", "M", "12345678901", "A00", 1);
        pacienteDAO.create(paciente);

        Paciente pacienteDb = pacienteDAO.buscarPorCpf("12345678901");
        assertNotNull(pacienteDb);
        assertEquals("João", pacienteDb.getNome());
    }

    @Test
    public void testBuscarPorNome() {
        Paciente paciente1 = new Paciente("João", "M", "12345678901", "A00", 1);
        Paciente paciente2 = new Paciente("Maria", "F", "98765432100", "B01", 2);

        pacienteDAO.create(paciente1);
        pacienteDAO.create(paciente2);

        List<Paciente> pacientes = pacienteDAO.buscarPorNome("João");
        assertEquals(1, pacientes.size());
        assertEquals("João", pacientes.get(0).getNome());
    }

    @Test
    public void testUpdate() {
        Paciente paciente = new Paciente("João", "M", "12345678901", "A00", 1);
        pacienteDAO.create(paciente);

        paciente.setNome("João Updated");
        paciente.setSexo("M");
        pacienteDAO.update(paciente, "1");

        Paciente pacienteDb = pacienteDAO.buscarPorCpf("12345678901");
        assertEquals("João Updated", pacienteDb.getNome());
    }

    @Test
    public void testDelete() {
        Paciente paciente = new Paciente("João", "M", "12345678901", "A00", 1);
        pacienteDAO.create(paciente);

        pacienteDAO.delete("12345678901");

        Paciente pacienteDb = pacienteDAO.buscarPorCpf("12345678901");
        assertNull(pacienteDb);
    }

    @After
    public void tearDown() throws SQLException {
        // Fechar a conexão e limpar os recursos
        conn.close();
    }
}
