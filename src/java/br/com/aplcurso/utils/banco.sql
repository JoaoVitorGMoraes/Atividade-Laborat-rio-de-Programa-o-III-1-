/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  João Vitor
 * Created: 9 de abr. de 2026
 */

create table usuario (
	id serial primary key,
	nome varchar(100) not null,
	datanascimento date not null,
	cpf varchar(11) unique not null,
	email varchar(100) unique not null,
	senha varchar(20) not null,
	salario decimal(15,2) not null
);

insert into usuario (nome, datanascimento, cpf, email, senha, salario)
values ('João José Gomes da Silva', '1990-08-10', '08243060073', 'joaojosegomes@gmail.com', 'senha123', 5200.00);

select * from usuario;