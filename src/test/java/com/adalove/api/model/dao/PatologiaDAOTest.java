package com.adalove.api.model.dao;

import com.adalove.api.model.entities.Patologia;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PatologiaDAOTest {

    @BeforeAll
    void setupDatabase() {
        ConnectionFactory.setConnectionParams("jdbc:h2:mem:testdb", "sa", "");
        try (var conn = ConnectionFactory.getConnection();
             var stmt = conn.createStatement()) {
            String createTable = """
                CREATE TABLE IF NOT EXISTS patologias (
                    codigo_cid VARCHAR(10) PRIMARY KEY,
                    grau INT NOT NULL,
                    nome VARCHAR(100) NOT NULL
                )
            """;
            stmt.execute(createTable);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao configurar o banco de testes", e);
        }
    }

    @BeforeEach
    void clearDatabase() {
        try (var conn = ConnectionFactory.getConnection();
             var stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM patologias");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao limpar o banco de testes", e);
        }
    }

    @Test
    @Order(1)
    void testCreateAndRead() {
        PatologiaDAO dao = new PatologiaDAO();
        Patologia patologia = new Patologia("C01", 1, "Doença Teste");
        dao.create(patologia);

        List<Patologia> patologias = dao.read();
        assertEquals(1, patologias.size(), "Deve haver exatamente 1 registro na tabela");
        assertEquals("C01", patologias.get(0).getCid());
        assertEquals("Doença Teste", patologias.get(0).getNome());
    }

    @Test
    @Order(2)
    void testBuscarPorNome() {
        PatologiaDAO dao = new PatologiaDAO();
        dao.create(new Patologia("C01", 1, "Doença Teste"));
        dao.create(new Patologia("C02", 2, "Outra Doença"));

        List<Patologia> patologias = dao.buscarPorNome("Doença");
        assertEquals(2, patologias.size(), "Deve encontrar 2 registros com 'Doença' no nome");

        List<Patologia> resultadoParcial = dao.buscarPorNome("Outra");
        assertEquals(1, resultadoParcial.size(), "Deve encontrar apenas 1 registro com 'Outra'");
        assertEquals("Outra Doença", resultadoParcial.get(0).getNome());
    }

    @Test
    @Order(3)
    void testUpdate() {
        PatologiaDAO dao = new PatologiaDAO();
        Patologia patologia = new Patologia("C01", 1, "Doença Teste");
        dao.create(patologia);

        patologia.setNome("Doença Atualizada");
        dao.update(patologia);

        List<Patologia> patologias = dao.read();
        assertEquals(1, patologias.size(), "Deve haver exatamente 1 registro após atualização");
        assertEquals("Doença Atualizada", patologias.get(0).getNome());
    }

    @Test
    @Order(4)
    void testDelete() {
        PatologiaDAO dao = new PatologiaDAO();
        dao.create(new Patologia("C01", 1, "Doença Teste"));

        dao.delete("C01");

        List<Patologia> patologias = dao.read();
        assertTrue(patologias.isEmpty(), "Deve não haver mais registros após a exclusão");
    }
}
