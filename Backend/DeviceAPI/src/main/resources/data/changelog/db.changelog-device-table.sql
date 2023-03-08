--liquibase formatted sql

--changeset device:4
CREATE TABLE IF NOT EXISTS device(
	device_id INT NOT NULL AUTO_INCREMENT,
	device_number INT NOT NULL UNIQUE,
	arc_id INT NOT NULL,
	blocked_since VARCHAR(10),
	blocked_till VARCHAR(10),
	comments VARCHAR(60),
	device_model VARCHAR(10) NOT NULL,
	mac VARCHAR(20) NOT NULL,
	rack_id INT NOT NULL,
	dev_id INT NULL REFERENCES developer(dev_id) ON DELETE CASCADE,
	FOREIGN KEY(arc_id) REFERENCES architecture(arc_id),
	FOREIGN KEY(rack_id) REFERENCES rack(rack_id),
	PRIMARY KEY(device_id)
);
