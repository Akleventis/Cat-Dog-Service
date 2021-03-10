# Cat Dog Service

<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#runapp">Run App</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#API-Call-Examples">API Calls</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project
This Cat/Dog API was developed with Spring boot as a foundation for simple CRUD operations on a database.

Features:
* Created with Spring Boot and MySql database
* Client is able to add, update, and delete the Cats and Dogs stored in the database
* Organized by controller, entity, repository, and service packages



### Built With

* [Java](https://www.java.com/en/)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [MySQL](https://www.mysql.com)



<!-- GETTING STARTED -->
## Getting Started

### Prerequisites

This is an example of how to list things you need to use the software and how to install them.
* [Java](https://www.java.com/en/)
* [MySQL](https://dev.mysql.com/doc/mysql-installation-excerpt/5.7/en/) Server
* [Postman](https://www.postman.com) (optional, can use curl commands instead)

### Run App
1. Start MySQL database
2. Build: ```$ gradle build```
3. Run ```./gradlew bootRun```


<!-- Api Calls -->
# API Call Examples
GET: ```/dogs``` ```/dogs/{id}```

POST: ```/dogs```
Request Body: 
```
{
  "name": "Taco", 
  "age": "2", 
  "color": "Grey", 
  "gender": "Male", 
  "breed": "Bulldog", 
  "weight": "5"
}
```

DELETE: ```/dogs/{id}```

PUT: ```/dogs/{id}``` 
Request Body: 
```
{
  "age": "4",
  "color": "Black",
  "name": "Sammy"
}
```
