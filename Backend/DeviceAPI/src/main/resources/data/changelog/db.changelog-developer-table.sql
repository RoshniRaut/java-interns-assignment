--liquibase formatted sql

--changeset dev:3
CREATE TABLE developers(
	dev_id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(30),
	email VARCHAR(60) UNIQUE,
	password VARCHAR(255) NOT NULL,
	PRIMARY KEY(dev_id)
);
