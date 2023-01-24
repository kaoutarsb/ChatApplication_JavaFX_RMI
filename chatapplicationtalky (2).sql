drop database if exists chatapplicationtalky;

create database chatapplicationtalky;

use chatapplicationtalky;

create table compte (
    id int(30) not null auto_increment,
	username varchar(15) not null,
    firstname varchar(15) not null,
    lastname varchar(15),
    birthsday text(15) ,
    gender varchar(10) ,
    passwordmot text(30),
    recoverquestion varchar(30),
    recoveranswer varchar(30),
    urlimage varchar(100),
    primary key (id)
);
select * from compte;