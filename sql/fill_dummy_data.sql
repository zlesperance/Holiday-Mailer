DELETE FROM contacts
WHERE email IN ('dummy1@eagles.ewu.edu', 'foobar@eagles.ewu.edu', 'muchachos@eagles.ewu.edu');

INSERT INTO contacts
	  SELECT 'dummy1@eagles.ewu.edu' AS 'email', 'Dummy' AS 'firstName', 'One' AS 'lastName', 2013 AS 'lastReceivedYear'
UNION SELECT 'foobar@eagles.ewu.edu', 'Foo', 'Bar', 2010
UNION SELECT 'muchachos@eagles.ewu.edu', 'Mustache', 'Muchacho', 2014;