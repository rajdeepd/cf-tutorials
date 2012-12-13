## Expense Report Application using Spring MVC with Redis
This is a simple application that demonstrates how to use Spring MVC and Redis on Cloud Foundry.

## Prerequisites
Before you get started, you need the following:

+  A [Cloud Foundry account](http://cloudfoundry.com/signup).

+  The [vmc](/tools/vmc/installing-vmc.html) Cloud Foundry command line tool.

+  A [Spring Tool Suite™ (STS)](http://www.springsource.org/spring-tool-suite-download) installation.

+  A [Cloud Foundry plugin for STS](/tools/STS/configuring-STS.html).

+  A [Redis Database](http://redis.io) installation.

## Deployment to Local Server:
### From an Eclipse environment like the SpringSource Tool Suite equipped with the m2e:

1. Import the project into Eclipse using the m2e / m2eclipse plugin - **File > Import > Maven > Existing Maven Projects**. 
2. Select project and right click on it, select **Run As->Maven build**. Now enter the goal as **clean install**.
3. Drag and drop the application onto the vfabric tc server.

### You can also use mvn to run it from command line tool.
1. Run `mvn clean install tomcat:run` on the command line from the root of the project to run on tomcat server and access the application at [http://localhost:8080/springmvc-expensereport-redis/](http://localhost:8080/springmvc-expensereport-redis/). 

## Deployment to Cloud Foundry:
### From an Eclipse environment like the SpringSource Tool Suite equipped with the m2e and Cloud Foundry WTP connector support:

1. Import the project into Eclipse using the m2e / m2eclipse plugin - **File > Import > Maven > Existing Maven Projects**. 
2. Setup a Cloud Foundry WTP server pointing to the Cloud Foundry cloud you want to target.
3. Drag and drop the application onto the Cloud Foundry WTP instance, and specify that you need a Redis service and 512M of RAM.

### You can use the vmc command line tool, too.
1. Run `mvn clean install war:war` on the command line from the root of the project to create a binary. 
2. From the root of the project, run `vmc --path target/springmvc-expensereport-redis-1.0.0-BUILD-SNAPSHOT.war push`.

### You should also be able to deploy the project using the Maven Cloud Foundry plugin, which is already configured.
1. From the root of the project, run `mvn clean install`.
2. Then run `mvn cf:push -Dcf.username=****** -Dcf.password=**** -Dcf.services=****** -Dcf.url=****` here set your Cloud Foundry username, password, service and application url.
