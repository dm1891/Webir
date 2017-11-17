drop database IF EXISTS webir;

create database webir;

\c webir

drop table IF EXISTS vehiculos;
drop table IF EXISTS empresas;

create table empresas (
id SERIAL PRIMARY KEY,
nombre text,
url text
);

create table vehiculos (
id SERIAL PRIMARY KEY,
titulo text,
categoria text,
urlimg text,
cantpasajeros text,
cantvalijas text,
transmision text,
cantpuertas text,
aire boolean,
cilindrada text,
preciobajo int,
precioalto int,
idempresa int references empresas(id)
);

insert into empresas (nombre,url) values ('Multicar','http://www.multicar.com.uy');
insert into empresas (nombre,url) values ('Plus rent a car','http://www.plusrentacar.com.uy/');