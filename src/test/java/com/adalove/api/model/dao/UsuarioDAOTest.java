package com.adalove.api.model.dao;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import java.sql.*;
import java.util.List;

// Importe as classes necessárias
import com.adalove.api.model.dao.UsuarioDAO;
import com.adalove.api.model.entities.Usuario;
import com.adalove.api.model.services.PasswordUtil;

public class UsuarioDAOTest {

    private UsuarioDAO usuarioDAO;
    private Connection conn;

    @Before
    public void setUp() throws SQLException {
        // Configuração do banco de dados em memória (H2)
        conn = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "sa", "");
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE users (" +
                "username VARCHAR(255) PRIMARY KEY, " +
                "password VARCHAR(255), " +
                "administrador BOOLEAN)");

        usuarioDAO = new UsuarioDAO();
    }

    @Test
    public void testAuthenticateUser_Success() throws SQLException {
        // Primeiro, registre um usuário
        String username = "admin";
        String password = "password123";
        usuarioDAO.registerUser(username, password, true);

        // Agora, tente autenticar esse usuário
        Usuario usuario = usuarioDAO.authenticateUser(username, password);

        assertNotNull(usuario);
        assertEquals(username, usuario.getUsername());
        assertTrue(usuario.isAdministrador());
    }

    @Test
    public void testAuthenticateUser_Fail() throws SQLException {
        // Tente autenticar com um usuário que não existe
        Usuario usuario = usuarioDAO.authenticateUser("nonexistentUser", "password123");

        assertNull(usuario); // Deve retornar null porque o usuário não existe
    }

    @Test
    public void testRegisterUser_Success() throws SQLException {
        String username = "newUser";
        String password = "newPassword";
        boolean isAdmin = false;

        boolean result = usuarioDAO.registerUser(username, password, isAdmin);
        assertTrue(result);

        // Verifique se o usuário foi registrado
        Usuario usuario = usuarioDAO.authenticateUser(username, password);
        assertNotNull(usuario);
        assertEquals(username, usuario.getUsername());
        assertFalse(usuario.isAdministrador());
    }

    @Test
    public void testRegisterUser_UsernameAlreadyExists() throws SQLException {
        String username = "existingUser";
        String password = "password123";
        boolean isAdmin = false;

        // Registra o primeiro usuário
        usuarioDAO.registerUser(username, password, isAdmin);

        // Tente registrar o mesmo nome de usuário
        boolean result = usuarioDAO.registerUser(username, "newPassword", false);

        assertFalse(result); // O usuário não deve ser registrado, pois o nome já existe
    }

    @Test
    public void testRead() throws SQLException {
        // Registra alguns usuários
        usuarioDAO.registerUser("user1", "password1", true);
        usuarioDAO.registerUser("user2", "password2", false);

        List<Usuario> usuarios = usuarioDAO.read();

        assertEquals(2, usuarios.size()); // Deve haver dois usuários registrados
    }

    @Test
    public void testDelete_Success() throws SQLException {
        String username = "userToDelete";
        usuarioDAO.registerUser(username, "password", true);

        // Verifica que o usuário existe antes de deletar
        Usuario usuario = usuarioDAO.authenticateUser(username, "password");
        assertNotNull(usuario);

        // Exclui o usuário
        boolean result = usuarioDAO.delete(username);
        assertTrue(result);

        // Verifica que o usuário foi realmente deletado
        usuario = usuarioDAO.authenticateUser(username, "password");
        assertNull(usuario); // Usuário não deve existir após ser excluído
    }

    @Test
    public void testDelete_Fail() throws SQLException {
        // Tente excluir um usuário inexistente
        boolean result = usuarioDAO.delete("nonexistentUser");

        assertFalse(result); // Não deve ser possível excluir um usuário que não existe
    }

    @Test
    public void testBuscarPorNome() throws SQLException {
        // Registra alguns usuários
        usuarioDAO.registerUser("Joao", "password", true);
        usuarioDAO.registerUser("Maria", "password", false);

        List<Usuario> usuarios = usuarioDAO.buscarPorNome("Jo");

        assertEquals(1, usuarios.size()); // Deve retornar apenas "Joao"
        assertEquals("Joao", usuarios.get(0).getUsername());
    }

    @After
    public void tearDown() throws SQLException {
        // Fechar a conexão e limpar os recursos
        conn.close();
    }
}
