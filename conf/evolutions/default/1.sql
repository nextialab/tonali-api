# --- !Ups

CREATE TABLE app (
	id INT NOT NULL AUTO_INCREMENT,
	name TEXT NOT NULL,
	version INT NOT NULL,
	password CHAR(60) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE user (
	id INT NOT NULL AUTO_INCREMENT,
	email TEXT NOT NULL,
	password CHAR(60) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE token (
	id INT NOT NULL AUTO_INCREMENT,
	user INT NOT NULL,
	token CHAR(36) NOT NULL,
	expires TIMESTAMP NOT NULL,
	app INT NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (user) REFERENCES user(id),
	FOREIGN KEY (app) REFERENCES app(id)
);

# web_password
INSERT INTO app (name, version, password) VALUES ('web', 1, '$2a$04$Y3egpcUwPGkJbSNoTfjb9OSBHIvCL7PaJj4U2kITAmPOLSk5O4x2.');

# password
INSERT INTO user (email, password) VALUES ('admin@foo.com', '$2a$04$GLM2NIxoELWX6zaUQuUxL.r0OvVP.lkXUF5uCxv9Buoa1YH/Uv1PS');

# --- !Downs

DROP TABLE app;
DROP TABLE user;
DROP TABLE token;