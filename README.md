# E-RideRent
My portfolio project in which I practise my backend development skills.

## Important note:
In October '23 I started migration of this monolithic application into a number of hexagonal microservices in order to learn and 
practise this modern software development style. To support this activity, I hired an experienced tutor for him to teach
and show me best practices.

This particular application is no longer under development.

## Used technologies:
* Java 17
* MySQL
* Spring Boot
* Spring Security
* Java Servlet Pages (I am aware JSP is outdated technology. Front-end will be replaced with separate React/Angular 
application, after I finish migration of backend to microservices)
* Hibernate
* Maven
* Docker

## About the app
<p>
This web application allows customers to make reservations of cars. It also allows admins to see all reservations, edit 
user data and add/edit cars in the system. Authorization and authentication are implemented with <b>Spring 
Security</b>.
</p>
<p>
Customers specify period for which they want to make a booking, based on that, system presents them cars available in
that period.
</p>
<p>
Application is <b>internationalized</b>, translated into Polish and English.
</p>

To be continued...

## Set up a database using Docker
This part assumes that you have Docker engine already installed and running. 
To set up a database, use terminal and navigate to main directory of the project. If opened in IntelliJ, you can use "Terminal"
card there.
Run following commands in order:
````
docker run --name=eriderentdb -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=1 -e MYSQL_DATABASE=eriderentdb -d mysql:latest
````
````
docker cp src/main/resources/eriderentdb.sql eriderentdb:/home
````
````
docker exec -it eriderentdb bash
````
````
mysql -u root eriderentdb < /home/eriderentdb.sql
````
````
\exit
````
MySQL database is up and running as Docker container. These commands could be replaced with docker-compose.yaml file, however, I have run into issues with coping a dump.sql file there.
