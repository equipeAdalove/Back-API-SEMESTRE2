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

create table atende (
ID_funcionario int,
cpf_paciente int,
primary key (ID_funcionario, cpf_paciente),
foreign key(ID_funcionario) references funcionario(ID_funcionario),
foreign key(cpf_paciente) references paciente(cpf_paciente)
); 

create table possui (
cpf_paciente bigint,
codigo_cid int,
primary key (cpf_paciente, codigo_cid),
foreign key(cpf_paciente) references paciente(cpf_paciente),
foreign key(codigo_cid) references patologias(codigo_cid)
);

show tables;
