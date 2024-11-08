package com.adalove.api.model.dao;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactoryTest {
    private static Connection connection;

    @BeforeAll
    public static void setUp() {
        connection = ConnectionFactory.getConnection();
    }

    @Test
    public void testConnectionIsNotNull() {
        Assertions.assertNotNull(connection, "A conexão com o banco de dados falhou.");
    }

    @Test
    public void testConnectionIsValid() throws SQLException {
        Assertions.assertTrue(connection.isValid(2), "A conexão com o banco de dados não é válida.");
    }

    @AfterAll
    public static void tearDown() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
