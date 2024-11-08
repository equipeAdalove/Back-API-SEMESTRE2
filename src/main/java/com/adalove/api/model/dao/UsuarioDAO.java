package com.adalove.api.model.dao;

import com.adalove.api.model.entities.Patologia;
import com.adalove.api.model.entities.Usuario;
import com.adalove.api.model.services.PasswordUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private final Connection connection;

    public UsuarioDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    public Usuario authenticateUser(String username, String password) throws SQLException {
        String query = "SELECT username, password, administrador FROM users WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String hashedPassword = rs.getString("password");
                    boolean isAdmin = rs.getBoolean("administrador");
                    if (PasswordUtil.checkPassword(password, hashedPassword)) {
                        return new Usuario(username, hashedPassword, isAdmin); // Retorna o usuário autenticado com senha hash
                    }
                }
            }
        }
        return null; // Retorna null se a autenticação falhar
    }



    private boolean usernameExists(String username) throws SQLException {
        String query = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // Retorna true se o usuário existir
            }
        }
    }

    public boolean registerUser(String username, String password, boolean isAdmin) throws SQLException {
        if (usernameExists(username)) {
            return false; // O usuário já existe
        }

        String query = "INSERT INTO users (username, password, administrador) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, PasswordUtil.hashPassword(password));
            stmt.setBoolean(3, isAdmin); // Adiciona o valor do checkbox
            return stmt.executeUpdate() > 0;
        }
    }

    public List<Usuario> read() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT username, administrador FROM users";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setUsername(rs.getString("username"));
                usuario.setAdministrador(rs.getBoolean("administrador"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Trate o erro conforme necessário
        }

        return usuarios;
    }

    public boolean delete(String username) throws SQLException {
        String query = "DELETE FROM users WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            return stmt.executeUpdate() > 0;
        }
    }

    public List<Usuario> buscarPorNome(String nome) {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT username, password, administrador FROM users WHERE username LIKE ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nome + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getBoolean("administrador")
                );
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }
}
