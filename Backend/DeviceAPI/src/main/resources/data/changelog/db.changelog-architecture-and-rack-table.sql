--liquibase formatted sql

--changeset arch:1
CREATE TABLE architecture(
	arc_id INT NOT NULL AUTO_INCREMENT,
	arc_name VARCHAR(10) UNIQUE NOT NULL,
	PRIMARY KEY(arc_id)
);

--changeset rack:2
CREATE TABLE rack(
	rack_id INT NOT NULL AUTO_INCREMENT,
	rack_name VARCHAR(10) UNIQUE NOT NULL,
	PRIMARY KEY(rack_id)
);
