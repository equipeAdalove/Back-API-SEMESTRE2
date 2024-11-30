package com.adalove.api.model.factory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseInitialize {

    private static final String CONFIG_FILE = "db_config.properties";

    public static boolean isConfigFileExists() {
        return new File(CONFIG_FILE).exists();
    }

    public static void saveConfig(String url, String username, String password) {
        Properties properties = new Properties();
        properties.setProperty("db.url", url);
        properties.setProperty("db.username", username);
        properties.setProperty("db.password", password);

        try (FileOutputStream fos = new FileOutputStream(CONFIG_FILE)) {
            properties.store(fos, "Database Configuration");
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar as configurações do banco de dados.", e);
        }
    }

    public static void createIfNotExist(String dbUrl, String dbUsername, String dbPassword) throws SQLException {
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             Statement statement = connection.createStatement()) {


            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS api");


            statement.executeUpdate("USE api");


            statement.executeUpdate("""
            CREATE TABLE IF NOT EXISTS usuarios (
                id INT AUTO_INCREMENT PRIMARY KEY,
                username VARCHAR(50) NOT NULL UNIQUE,
                password VARCHAR(255) NOT NULL,
                administrador BOOLEAN NOT NULL
            )
        """);
        } catch (SQLException e) {
            throw new SQLException("Erro ao criar o banco de dados e as tabelas.", e);
        }
    }


    private static void createTables(Statement statement) throws SQLException {
        // Mesma lógica para criar tabelas como na versão anterior
        statement.executeUpdate("""
            CREATE TABLE IF NOT EXISTS users (
                id INT AUTO_INCREMENT PRIMARY KEY,
                username VARCHAR(50) NOT NULL UNIQUE,
                password VARCHAR(255) NOT NULL,
                administrador BOOLEAN DEFAULT FALSE
            )
        """);
        statement.executeUpdate("""
            CREATE TABLE IF NOT EXISTS funcionario (
                id_funcionario INT PRIMARY KEY AUTO_INCREMENT,
                nome VARCHAR(100),
                cargo VARCHAR(50),
                crm VARCHAR(15)
            )
        """);
        statement.executeUpdate("""
            CREATE TABLE IF NOT EXISTS patologias (
                codigo_cid VARCHAR(10) PRIMARY KEY,
                grau INT,
                nome VARCHAR(100)
            )
        """);
        statement.executeUpdate("""
            CREATE TABLE IF NOT EXISTS paciente (
                nome VARCHAR(100),
                sexo CHAR(1),
                cpf_paciente VARCHAR(11) PRIMARY KEY,
                codigo_cid VARCHAR(10),
                id_medico INT,
                FOREIGN KEY(codigo_cid) REFERENCES patologias(codigo_cid),
                FOREIGN KEY(id_medico) REFERENCES funcionario(id_funcionario)
            )
        """);
        statement.executeUpdate("""
            CREATE TABLE IF NOT EXISTS ficha_paciente (
                id_ficha INT AUTO_INCREMENT,
                cpf_paciente VARCHAR(11),
                id_funcionario INT,
                observacoes TEXT,
                data_hora DATETIME,
                PRIMARY KEY (id_ficha),
                FOREIGN KEY (cpf_paciente) REFERENCES paciente(cpf_paciente),
                FOREIGN KEY (id_funcionario) REFERENCES funcionario(id_funcionario)
            )
        """);
    }
}
