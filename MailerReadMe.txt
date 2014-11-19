To make sure the mailer works javax.mail.jar (provided) needs to be present on the system running the mailer
Grab javax.mail.jar and place it some where on you system, open you ide and set the class path to where ever
you put the javax.mail.jar file and the program should run. in jgrasp is is under settings, path/classpath
SendMail() takes in three strings, a Email to send to, a Subject line, and a body of txt.sendMailRun is just
a tester that will not be nessicary after wednesday. 

steps to add javax.mail.jar in eclipse
download Javamail 1.5 jar.
 Next step, Right click on your Project, select Properties. 
Select Java Build Path, Click the Libraries Tab and Click Add external Jars. 
Browse to the location of your library and Add it. 
Expand the javamail library. 
Click Source attachment and click the Edit button. 
enter image description here Browse to the location of your source zip file and add it.
