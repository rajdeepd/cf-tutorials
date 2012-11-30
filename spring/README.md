This is a simple application that demonstrates how to use Spring MVC and PostgreSQL on Cloud Foundry.

Deployment to Local Server:

From an Eclipse environment like the SpringSource Tool Suite equipped with the m2e: 
1) Import the project into Eclipse using the m2e / m2eclipse plugin - File > Import > Maven > Existing Maven Projects. 
2) Select project and right click on it, select Run As->Maven build. Now enter the goal as clean install.
3) Drag and drop the application onto the vfabric tc server.

You can use the vmc command line tool, too.
1) Run 'mvn clean install tomcat:run' on the command line from the root of the project to run on tomcat server. 

Deployment to Cloud Foundry: 

From an Eclipse environment like the SpringSource Tool Suite equipped with the m2e and Cloud Foundry WTP connector support: 
1) Import the project into Eclipse using the m2e / m2eclipse plugin - File > Import > Maven > Existing Maven Projects. 
2) Setup a Cloud Foundry WTP server pointing to the Cloud Foundry cloud you want to target
3) Drag and drop the application onto the Cloud Foundry WTP instance, and specify that you need a PostgreSQL service and 512M of RAM.

You can use the vmc command line tool, too.

1) Run 'mvn clean install' on the command line from the root of the project to create a binary. 
2) From the root of the project, run 'vmc --path target/html5expense-1.0.0-BUILD-SNAPSHOT.war push'

You should also be able to deploy the project using the Maven Cloud Foundry plugin, which is already configured.
1) from the root of the project, run 'mvn clean install'
2) then run 'mvn cf:push -Dcf.username=************* -Dcf.password=**** -Dcf.services=postgresql-16dcd -Dcf.url=expensereport.cloudfoundry.com' 
