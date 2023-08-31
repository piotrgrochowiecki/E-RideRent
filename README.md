# E-RideRent
My portfilio project in which I practise my backend development skills.

## Used technologies:
* Java 17
* MySQL
* Spring Boot
* Spring Security
* Java Servlet Pages
* Hibernate
* Maven
* Docker

## Inspiration
As a fan of Elon Musk, I decided to create a web application that allows users of electric cars rental company to book 
cars. At the moment, I focused on backend. As my skills grow, I also want to take care of frontend.

## How to set up a database using Docker
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

## About App
Application is translated into English and Polish.

to be continued...