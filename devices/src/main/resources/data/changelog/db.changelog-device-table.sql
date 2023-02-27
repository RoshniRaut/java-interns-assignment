--liquibase formatted sql

--changeset device:4
CREATE TABLE device(
	device_id INT NOT NULL AUTO_INCREMENT,
	arcId INT NOT NULL,
	blocked_since VARCHAR(10),
	blocked_till VARCHAR(10),
	comments VARCHAR(60),
	devId INT NOT NULL,
	device_model VARCHAR(10) NOT NULL,
	mac VARCHAR(20) NOT NULL,
	rackId INT NOT NULL,
	FOREIGN KEY(arcId) REFERENCES architecture(arc_id),
	FOREIGN KEY(devId) REFERENCES developers(dev_id),
	FOREIGN KEY(rackId) REFERENCES rack(rack_id),
	PRIMARY KEY(device_id)
);
