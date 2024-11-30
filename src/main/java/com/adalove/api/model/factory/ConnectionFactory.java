package com.adalove.api.model.factory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static final String CONFIG_FILE = "db_config.properties";
    private static String url;
    private static String username;
    private static String password;

    static {
        loadConfiguration();
    }

    private static void loadConfiguration() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
            properties.load(fis);
            url = properties.getProperty("db.url", "jdbc:mysql://localhost:3306/api");
            username = properties.getProperty("db.username", "root");
            password = properties.getProperty("db.password", "fatec");
        } catch (IOException e) {
            System.err.println("Could not load database configuration. Using defaults.");
            url = "jdbc:mysql://localhost:3306/api";
            username = "root";
            password = "marcal2012";
        }
    }

    public static void updateConfiguration(String newUsername, String newPassword) {
        Properties properties = new Properties();
        properties.setProperty("db.url", url);
        properties.setProperty("db.username", newUsername);
        properties.setProperty("db.password", newPassword);

        try (FileOutputStream fos = new FileOutputStream(CONFIG_FILE)) {
            properties.store(fos, "Configuração do Banco de Dados");
            username = newUsername;
            password = newPassword;
            System.out.println("Configuração atualizada com sucesso!");
        } catch (IOException e) {
            throw new RuntimeException("Não foi possivel atualizar a configuração do banco de dados.", e);
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar no banco de dados", e);
        }
    }
}
