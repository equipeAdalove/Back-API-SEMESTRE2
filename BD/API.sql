create database api;
use api;

create table funcao (
ID_funcao int primary key,
cargo varchar(100)
);

create table funcionario (
ID_funcionario int primary key,
sexo char(1),
nome varchar(100)
idFuncao int,
foreign key (idFuncao) references funcao(ID_funcao)
);

create table patologias (
codigo_cid int primary key,
grau int,
nome varchar(100)
);

create table paciente (
nome varchar(100),
sexo char (1),
cpf_paciente bigint primary key,
codigo_cid int,
foreign key(codigo_cid) references patologias(codigo_cid)
);
