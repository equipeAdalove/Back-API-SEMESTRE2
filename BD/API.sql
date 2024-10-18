CREATE DATABASE api;
USE api;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
);

INSERT INTO users (username, password) VALUES ('root', '$2a$12$oz7UDRWd/7x1g/biUQSoN.jrqG4oGRam2QF/nrC9L/g6I/eg2.a5S');

CREATE TABLE funcionario (
    id_funcionario INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100),
    cargo VARCHAR(50), 
    crm VARCHAR(15), Para médicos (não obrigatório)
);
