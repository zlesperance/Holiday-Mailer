DELETE FROM contacts
WHERE email IN ('dummy1@eagles.ewu.edu', 'foobar@eagles.ewu.edu', 'muchachos@eagles.ewu.edu');

INSERT INTO contacts
	  SELECT 'dummy1@eagles.ewu.edu' AS 'email', 'Dummy' AS 'firstName', 'One' AS 'lastName', '2013-01-01' AS 'lastReceivedDate'
UNION SELECT 'foobar@eagles.ewu.edu', 'Foo', 'Bar', '2010-01-01'
UNION SELECT 'muchachos@eagles.ewu.edu', 'Mustache', 'Muchacho', '2014-01-01';