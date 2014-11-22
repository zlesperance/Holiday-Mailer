CREATE TABLE contacts_temp (
	email varchar(50) not null,
	firstName varchar(50) not null,
	lastName varchar(50) not null,
	lastReceivedYear int null,
	CONSTRAINT pk_contacts_temp PRIMARY KEY (email)
);

INSERT INTO contacts_temp(email, firstName, lastName, lastReceivedYear)
SELECT email, firstName, lastName, lastReceivedYear FROM contacts;

DROP TABLE contacts;

CREATE TABLE contacts (
	email varchar(50) not null,
	firstName varchar(50) not null,
	lastName varchar(50) not null,
	lastReceivedYear int null,
	CONSTRAINT pk_contacts PRIMARY KEY (email)
);

INSERT INTO contacts(email, firstName, lastName, lastReceivedYear)
SELECT email, fristName, lastName, lastReceivedYear FROM contacts_temp;

DROP TABLE contacts_temp;
