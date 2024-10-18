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

CREATE TABLE patologias (
    codigo_cid VARCHAR(10) PRIMARY KEY,
    grau INT,
    nome VARCHAR(100)
);

CREATE TABLE paciente (
    nome VARCHAR(100),
    sexo CHAR(1),
    cpf_paciente VARCHAR(11) PRIMARY KEY,
    codigo_cid VARCHAR(10), -- Paciente vinculado à patologia
    id_medico INT, -- Médico (não obrigatoriamente vinculado ao paciente)
    FOREIGN KEY(codigo_cid) REFERENCES patologias(codigo_cid),
    FOREIGN KEY(id_medico) REFERENCES funcionario(id_funcionario)
);

CREATE TABLE ficha_paciente (
    id_ficha INT AUTO_INCREMENT,
    cpf_paciente VARCHAR(11),
    id_funcionario INT,
    observacoes TEXT,
    data_hora DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id_ficha),
    FOREIGN KEY (cpf_paciente) REFERENCES paciente(cpf_paciente),
    FOREIGN KEY (id_funcionario) REFERENCES funcionario(id_funcionario)
);
