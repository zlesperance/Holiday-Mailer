CREATE TABLE contacts_temp (
	email varchar(50) not null,
	firstName varchar(50) not null,
	lastName varchar(50) not null,
	lastReceivedDate datetime null,
	CONSTRAINT pk_contacts_temp PRIMARY KEY (email)
);

INSERT INTO contacts_temp(email, firstName, lastName, lastReceivedDate)
SELECT email, fristName, lastName, lastReceivedDate FROM contacts;

DROP TABLE contacts;

CREATE TABLE contacts (
	email varchar(50) not null,
	firstName varchar(50) not null,
	lastName varchar(50) not null,
	lastReceivedDate datetime null,
	CONSTRAINT pk_contacts PRIMARY KEY (email)
);

INSERT INTO contacts(email, firstName, lastName, lastReceivedDate)
SELECT email, fristName, lastName, lastReceivedDate FROM contacts_temp;

DROP TABLE contacts_temp;
