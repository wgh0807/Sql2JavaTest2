drop database if exists db_a;
create database db_a charset utf8mb4;


drop table if exists db_a.lucky_person;
create table db_a.lucky_person (
  id       int auto_increment primary key
  comment 'id PK',
  name    varchar(255) not null unique
  comment 'name NN UN',
  money double  not null
  comment 'money NN'
)
  comment 'person table';


select *
from db_a.lucky_person;

drop table if exists db_a.cute_cat;
create table db_a.cute_cat (
  id   int primary key auto_increment comment 'ID PK',
  name varchar(255) not null,
  sex  varchar(5)      default '未知',
  age  int             default 0
);


select *
from db_a.cute_cat;