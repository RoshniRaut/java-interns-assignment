--liquibase formatted sql

--changeset device:4
CREATE TABLE device(
	device_id INT NOT NULL AUTO_INCREMENT,
	device_number INT NOT NULL UNIQUE,
	arc_id INT NOT NULL,
	blocked_since VARCHAR(10),
	blocked_till VARCHAR(10),
	comments VARCHAR(60),
	dev_id INT,
	device_model VARCHAR(10) NOT NULL,
	mac VARCHAR(20) NOT NULL,
	rack_id INT NOT NULL,
	FOREIGN KEY(arc_id) REFERENCES architecture(arc_id),
	FOREIGN KEY(dev_id) REFERENCES developer(dev_id),
	FOREIGN KEY(rack_id) REFERENCES rack(rack_id),
	PRIMARY KEY(device_id)
);
