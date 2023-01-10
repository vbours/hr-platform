# hr-platform

### Technologies ###

* Java Spring Boot
* Rest / Graphql
* JPA - Hibernate
* Minio
* Unit tests with test containers
* Docker
* Swagger

### Description ###

Java Spring Boot application (microservice) which is connected with
a postgres db in order to execute the basic hr functionalities. There are tables about 
company's employees, company's branches, employee's days off, employee's overtime and
some information about the reporting files that we produce from our platform.
The hr user can see all of those data from those tables and finally he can export some
reports. The produced reports are stored to s3/minio storage and the user 
can download them and see them.

### Specification ###

<li>Language : Java
<li>Framework : Spring boot, JPA, Hibernate
<li>Rest Api , Graphql
<li>Unit Tests
<li>Dockerized
<li>Swagger - Open-api Documentation
<li>Object storage : Minio
<li>Database storage : Postgres

### Deploy external services ###
* Go in db directory
* Run
  ```
  docker-compose up --build
  ```

### Important Links ###

swagger: http://localhost:8090/swagger-ui.html

minio server: localhost:9001

graphql: http://localhost:8090/graphiql