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
