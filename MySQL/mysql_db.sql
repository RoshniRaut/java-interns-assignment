CREATE DATABASE SB_Security;
use SB_Security;

-- CREATE TABLE Device(
--     architecture varchar(100) not null,
--     blocked_since date default null,
--     blocked_till date default null,
--     comments varchar(100),
--     developer varchar(100),
--     device_model varchar(100),
--     device_number int not null,
--     mac varchar(100),
--     rack varchar(100) not null,
--     sr_no int PRIMARY KEY AUTO_INCREMENT
-- );

-- CREATE TABLE Developer(
--     email varchar(100) not null,
--     name varchar(100),
--     PRIMARY KEY (email)
-- );

-- CREATE TABLE IF NOT EXISTS User(
--     email varchar(100) not null,
--     password varchar(100),
--     PRIMARY KEY (email)
-- );

-- -- insert into Device values("E2900","2022-04-04","2022-07-03","EV 10.11.14.167","VinayK","2900A",10,"54:39:68:16:D5:7F","Rack 1",1);
-- -- insert into Device values("E4700",null,null,null,null,"4700",11,"54:39:68:03:FF:76","Rack 1",2);

-- -- insert into Developer values("madhur@ness.com","madhur");
-- -- insert into Developer values("roshni@ness.com","roshni");

-- insert into User values("roshni@ness.com","roshni");


-- anupam 
-- CREATE TABLE device( 
--     device_id INT NOT NULL AUTO_INCREMENT, 
--     arcId INT NOT NULL, 
--     blocked_since VARCHAR(10), 
--     blocked_till VARCHAR(10), 
--     comments VARCHAR(60), 
--     devId INT NOT NULL, 
--     device_model VARCHAR(10) NOT NULL, 
--     mac VARCHAR(20) NOT NULL, 
--     rackId INT NOT NULL, 
--     FOREIGN KEY(arcId) REFERENCES architecture(arc_id), 
--     FOREIGN KEY(devId) REFERENCES developers(dev_id), 
--     FOREIGN KEY(rackId) REFERENCES rack(rack_id), 
--     PRIMARY KEY(device_id));

-- CREATE TABLE architecture(
-- 	arc_id INT NOT NULL AUTO_INCREMENT,
-- 	arc_name VARCHAR(10) UNIQUE,
-- 	PRIMARY KEY(arc_id)
-- );

-- CREATE TABLE developers(
-- 	dev_id INT NOT NULL AUTO_INCREMENT,
-- 	name VARCHAR(30),
-- 	email VARCHAR(60) UNIQUE,
-- 	PRIMARY KEY(arc_id)
-- );

-- CREATE TABLE device( 
-- 	device_id INT NOT NULL AUTO_INCREMENT, 
-- 	arcId INT NOT NULL, 
-- 	blocked_since VARCHAR(10), 
-- 	blocked_till VARCHAR(10), 
-- 	comments VARCHAR(60), 
-- 	devId INT NOT NULL, 
-- 	device_model VARCHAR(10) NOT NULL, 
-- 	mac VARCHAR(20) NOT NULL, 
-- 	rackId INT NOT NULL, 
-- 	FOREIGN KEY(arcId) REFERENCES architecture(arc_id), 
-- 	FOREIGN KEY(devId) REFERENCES developers(dev_id), 
-- 	FOREIGN KEY(rackId) REFERENCES rack(rack_id), 
-- 	PRIMARY KEY(device_id)
-- );

-- CREATE TABLE rack(
-- 	rack_id INT NOT NULL AUTO_INCREMENT,
-- 	rack_name VARCHAR(10) UNIQUE,
-- 	PRIMARY KEY(rack_id)
-- );