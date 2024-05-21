# *SpringBoot Telemanagement App*

A simple Spring App with java, SpringBoot & Postgres.

> [!IMPORTANT]
> This development project is mostly for learning purpose, indeed code readability might not be at its finest, so get rid of the multiline comments.

## Overview :mag_right:

This application, was centered on the implementation of two microservices that would relate to the openweathermapapi service.

The two microservices are: 
1. `dataRetriever`
2. `weatherDataService`
   
The first microservice, `dataRetriever`, is responsible for connecting to the MQTT Broker (mosquitto in this case) and listening to the **weather-data** channel, where, at time intervals of 10 min, weather data in json format related to 5 specific cities are uploaded.
Specifically, this data will be parsed and using the jpa and hibernate libraries, stored in the corresponding tables of a relational database.

The second microservice, `weatherDataService`, is in charge of exposing a **REST** layer through which the user can query the database and request different types of data, selecting, among others, the possibility that the data are returned in xml format (as required by the **CIM** protocol)

## What you will need :pushpin:

- Java
- Maven 
- SpringBoot
- Postgres RDBMS
- Mosquitto (or any other publicly available mqtt brokers of your choice)

## Installation :inbox_tray:

### 1. Clone the repo

```git
https://github.com/andreasblendorio/SpringBoot-Telemanagement-App.git
```

## Structure :open_file_folder:

Project files for the first microservice are structured as follows:

```text
└── SpringBootTelemanagementApp
       ├── .idea
       ├── dataRetriever [jpa]
       │   ├── .idea 
       │   ├── .mvn
       │   └── src
       │       └── main
       │           ├── java
       │           │   └── com.example.demo                          
       │           │       ├── configs          
       │           │       │   └── MqttBeans           
       │           │       ├── entities  
       │           │       │   ├── CityEntity
       │           │       │   ├── CoordinatesEntity
       │           │       │   ├── MainDataEntity
       │           │       │   ├── WeatherEntity
       │           │       │   └── WindEntity   
       │           │       ├── gateway
       │           │       │   └── MqttGateway     
       │           │       ├── repos                               
       │           │       │   └── CityWeatherRepo          
       │           │       ├── startup
       │           │       │   └── StartupUtility         
       │           │       ├── utils
       │           │       │   └── MessageParser 
       │           │       └── JpaApplication                   
       │           └── resources
       │               └── application.properties  
       ├── .gitignore
       ├── mvnw
       ├── mvnw.cmd
       ├── pom.xml
       │
       ├── weatherDataService
       │   ├── .idea 
       │   ├── .mvn
       │   └── src
       │       └── main
       │           ├── java
       │           │   └── com.dataservice.weatherDataService                   
       │           │       ├── controller          
       │           │       │   └── DataController
       │           │       ├── dto
       │           │       │   └── WeatherDataDTO           
       │           │       ├── entities  
       │           │       │   ├── CityEntity
       │           │       │   ├── CoordinatesEntity
       │           │       │   ├── MainDataEntity
       │           │       │   ├── WeatherEntity
       │           │       │   └── WindEntity    
       │           │       ├── repos                               
       │           │       │   └── CityWeatherRepo          
       │           │       └── WeatherDataServiceApplication                 
       │           └── resources
       │               └── application.properties  
       ├── .gitignore
       ├── mvnw
       ├── mvnw.cmd
       └── pom.xml
```
