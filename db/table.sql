drop database if exists test;
create database test;
use test;

create table customer(
    custid INT primary key,
    cname varchar(30) not null,
    adhar_number varchar(20) unique not null,
    city varchar(10),
    state varchar(30)
);

create table orders(
    ordid INT primary key,
    orderdate date not null,
    orderamt decimal(9,2),
    custid INT,
    foreign key(custid) references customer(custid)
);