--liquibase formatted sql

--changeset dev:3
CREATE TABLE IF NOT EXISTS developer(
	dev_id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(30) UNIQUE NOT NULL,
	email VARCHAR(60) UNIQUE NOT NULL,
	password VARCHAR(255) NOT NULL,
	PRIMARY KEY(dev_id)
);
