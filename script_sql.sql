create schema gestion_pepiniere;
use gestion_pepiniere;

create table article(
	id int(10) not null primary key auto_increment,
    nom varchar(150) NOT NULL,
    description varchar(1500) NOT NULL,
    quantite int(10) not null,
    image largblob
);
