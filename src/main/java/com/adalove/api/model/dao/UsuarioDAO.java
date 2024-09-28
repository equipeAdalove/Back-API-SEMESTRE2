package com.adalove.api.model.dao;

import com.adalove.api.model.services.PasswordUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    private final Connection connection;

    public UsuarioDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    public boolean authenticateUser(String username, String password) throws SQLException {
        String query = "SELECT password FROM users WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String hashedPassword = rs.getString("password");
                    return PasswordUtil.checkPassword(password, hashedPassword);
                } else {
                    return false;
                }
            }
        }
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

    public boolean registerUser(String username, String password) throws SQLException {
        if (usernameExists(username)) {
            return false; // O usuário já existe
        }

        String query = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, PasswordUtil.hashPassword(password));
            return stmt.executeUpdate() > 0;
        }
    }
}
