DROP TABLE IF EXISTS contacts;

CREATE TABLE contacts (
	email varchar(50) not null,
	firstName varchar(50) not null,
	lastName varchar(50) not null,
	lastReceivedDate datetime null,
	CONSTRAINT pk_contacts PRIMARY KEY (email)
);