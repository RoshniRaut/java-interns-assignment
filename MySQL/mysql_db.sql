CREATE DATABASE db;
use db;

CREATE TABLE Device(
    architecture varchar(100) not null,
    blocked_since date,
    blocked_till date,
    comments varchar(100),
    developer varchar(100),
    device_model varchar(100),
    device_number int not null,
    mac varchar(100),
    rack varchar(100) not null,
    sr_no int not null AUTO_INCREMENT
    PRIMARY KEY (sr_no)
);

CREATE TABLE Developer(
    email varchar(100) not null,
    name varchar(100),
    PRIMARY KEY (email)
);

insert into Device values("E2900","2022-04-04","2022-07-03","EV 10.11.14.167","VinayK","2900A",10,"54:39:68:16:D5:7F","Rack 1",1);
insert into Device values("E4700","","","",null,"4700",11,"54:39:68:03:FF:76","Rack 1",2);

insert into Developer values("madhur@ness.com","madhur");
insert into Developer values("roshni@ness.com","roshni");