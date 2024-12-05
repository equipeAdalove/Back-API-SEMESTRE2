package com.adalove.api.model.dao;

import com.adalove.api.model.entities.Funcionario;
import org.junit.jupiter.api.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FuncionarioDAOTest {

    private FuncionarioDAO funcionarioDAO;
    private Funcionario funcionario;

    @BeforeAll
    public void setUp() {
        funcionarioDAO = new FuncionarioDAO();
        funcionario = new Funcionario("João Silva", "Médico", "CRM1234");
    }

    @Test
    @Order(1)
    public void testCreate() {
        funcionarioDAO.create(funcionario);

        // Obtendo o funcionário recém-criado para capturar seu ID
        Funcionario criado = funcionarioDAO.read().stream()
                .filter(f -> f.getNome().equals("João Silva") && f.getCrm().equals("CRM1234"))
                .findFirst()
                .orElse(null);

        assertNotNull(criado, "O funcionário não foi criado corretamente.");
        funcionario.setId(criado.getId());  // Define o ID para uso em testes subsequentes
    }

    @Test
    @Order(2)
    public void testRead() {
        List<Funcionario> funcionarios = funcionarioDAO.read();
        assertNotNull(funcionarios);
        assertFalse(funcionarios.isEmpty());
    }

    @Test
    @Order(3)
    public void testBuscarPorNome() {
        List<Funcionario> funcionarios = funcionarioDAO.buscarPorNome("João");
        assertNotNull(funcionarios);
        assertTrue(funcionarios.stream().anyMatch(f -> f.getNome().contains("João")));
    }

    @Test
    @Order(4)
    public void testUpdate() {
        funcionario.setNome("João Silva Atualizado");
        funcionarioDAO.update(funcionario, funcionario.getId());

        // Obter novamente o funcionário para verificar a atualização
        Funcionario updatedFuncionario = funcionarioDAO.read().stream()
                .filter(f -> f.getId() == funcionario.getId())
                .findFirst()
                .orElse(null);

        assertNotNull(updatedFuncionario, "Funcionário atualizado não encontrado.");
        assertEquals("João Silva Atualizado", updatedFuncionario.getNome());
    }

    @Test
    @Order(5)
    public void testDelete() {
        funcionarioDAO.delete(funcionario.getId());
        List<Funcionario> funcionarios = funcionarioDAO.read();
        assertFalse(funcionarios.stream().anyMatch(f -> f.getId() == funcionario.getId()));
    }
}