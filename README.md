# *SpringBoot Telemanagement App*

A simple Spring App with java, SpringBoot & Postgres.

> [!IMPORTANT]
> This development project is mostly for learning purpose, indeed code readability might not be at its finest, so get rid of the multiline comments.

## Overview :mag_right:

This application, was centered on the implementation of two microservices that would relate to the openweathermapapi service.

The two microservices are: 
1. `dataRetriever`
2. `weatherDataService`
   
The first microservice, [`/dataRetriever`](dataRetriever), is responsible for connecting to the MQTT Broker (mosquitto in this case) and listening to the **weather-data** channel, where, at time intervals of 10 min, weather data in json format related to 5 specific cities are uploaded.
Specifically, this data will be parsed and using the jpa and hibernate libraries, stored in the corresponding tables of a relational database.

The second microservice, [`/weatherDataService`](weatherDataService), is in charge of exposing a **REST** layer through which the user can query the database and request different types of data, selecting, among others, the possibility that the data are returned in xml format (as required by the **CIM** protocol)

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

Project files for the two microservices are structured as follows:

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
       │           │       │   └── MqttBeans            // Broker configs
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

## Further explanations:
### dataRetriever
The functionality of this microservice is related to the arrival of messages on the broker, it sets up a channel called "weather-data" where messages containing weather data with respect to 5 cities in json format arrive continuously, one for each city selected in the auxiliary microservice today (external to this application).
More precisely, by means of the [`/MqttBeans`](dataRetriever/src/main/java/com/example/demo/configs/MqttBeans.java) class, which handles the configurations for connecting to the broker and setting up the Inbound and Outbound channels, the Client can connect to the broker and subscribe to the channel of interest (i.e. 'weather-data') thus receiving the json messages.
The aforementioned jsons are retrieved from the [openweathermap.org](https://openweathermap.org/api) API service and are composed as follows:
```json
{
    "coord": {
        "lon": 15.709,
        "lat": 50.814
    },
    "weather": [
        {
            "id": 803,
            "main": "Clouds",
            "description": "broken clouds",
            "icon": "04d"
        }
    ],
    "base": "stations",
    "main": {
        "temp": 294.87,
        "feels_like": 294.48,
        "temp_min": 291.77,
        "temp_max": 295.54,
        "pressure": 1011,
        "humidity": 53,
        "sea_level": 1011,
        "grnd_level": 930
    },
    "visibility": 10000,
    "wind": {
        "speed": 3.41,
        "deg": 113,
        "gust": 5.99
    },
    "clouds": {
        "all": 75
    },
    "dt": 1716278700,
    "sys": {
        "type": 2,
        "id": 2093379,
        "country": "PL",
        "sunrise": 1716260392,
        "sunset": 1716317279
    },
    "timezone": 7200,
    "id": 7531962,
    "name": "Podgórzyn",
    "cod": 200
}
```
Once this receiving process is established, the parsing function defined in [`/MessageParser`](dataRetriever/src/main/java/com/example/demo/utils/MessageParser.java) (that relies on the Object Mapper) which is responsible of mapping the json's fields to the specific [`/CityEntity`](dataRetriever/src/main/java/com/example/demo/entities/CityEntity.java), will then be invoked by the `MessageHandler` method of [`/MqttBeans`](dataRetriever/src/main/java/com/example/demo/configs/MqttBeans.java) enabling the the payloads serialization for each message that are seamlessly received on the topic and saving of the latters in the corresponding [`/CityWeatherRepo`](dataRetriever/src/main/java/com/example/demo/repos/CityWeatherRepo.java).
Starting from the json fields, the parsed values will be saved in the corresponding postgres tables. 

### weatherDataService
The logic of this microservice orbits around the [`/DataController`](weatherDataService/src/main/java/com/dataservice/weatherDataService/controller/DataController.java) class which implements a REST layer using the Data Transfer Object (DTO) design pattern in order to facilitate the transfer of weather data between the application layers. This controller is annotated with `@RestController` and `@RequestMapping` to define a base path for all endpoints, ensuring that all paths are exposed under the `/api` path. The endpoints include capabilities for retrieving temperature data, minimum and maximum temperatures, average temperatures over various periods, and humidity data. In addition, the controller provides an endpoint for downloading a weather summary in XML format. The use of DTOs, such as [`WeatherDataDTO`](weatherDataService/src/main/java/com/dataservice/weatherDataService/dto/WeatherDataDTO.java), allows multiple related data to be encapsulated in a single object, which is useful for constructing comprehensive summaries and maintaining a clear separation between data access and presentation layers. The controller interacts with the same entities used in the [`/dataRetriever`](dataRetriever) microserviceand with the [`/CityWeatherRepo`](dataRetriever/src/main/java/com/example/demo/repos/CityWeatherRepo.java) repository to retrieve the necessary data, processes it, and returns it in a structured format through the defined endpoints.

