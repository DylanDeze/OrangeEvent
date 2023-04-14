drop database if exists orange_Event_JV_23;
create database orange_Event_JV_23;
use orange_Event_JV_23;

create table client (
    idclient int(3)  not null auto_increment,
    nom varchar(50),
    prenom varchar(50),
    adresse varchar(50),
    email varchar(50),
    primary key(idclient)
);

create table technicien (
    idtechnicien int(3)  not null auto_increment,
    nom varchar(50),
    prenom varchar(50),
    qualification varchar(50),
    email varchar(50),
    mdp varchar(50),
    primary key (idtechnicien)
);

create table intervention (
    idinter int(3)  not null auto_increment,
    materiel varchar(50),
    panne varchar(50),
    dateinter date,
    prix float,
    idclient int(3) not null,
    idtechnicien int(3)  not null,
    primary key (idinter),
    foreign key (idclient) references client(idclient),
    foreign key (idtechnicien) references technicien(idtechnicien)
);

insert into technicien values 
(null, "Thamila", "Dylan", "technicien", "a@gmail.com", "123");