##### REST API to get Marvel Characters - Spring Boot, Java 8 and Maven

###### Capabilities:
*	GET - Get all Marvel character ids in a JSON array of numbers
*	GET - Get a Marvel character: id, name, description, thumbnail given an Id
*	GET - Get a Marvel character with the description translated in real time


###### REST API URLs
* GET `http://localhost:8080/characters`
* GET `http://localhost:8081/characters/{id}`
* GET `http://localhost:8081/characters/{id}?language=pt`

###### Properties
  - `server.port=8080` (Default server port)
  - `marvel.comics.api.public-key=` (Marvel API public key)
  - `marvel.comics.api.private-key=` (Marvel API private key)
  - `marvel.comics.api.base.url=` (Marvel API base url)
  - `marvel.comics.api.translation.app.url=` (Translation script app url)
  - `marvel.comics.api.characters.cache.flush=` (Characters cache flush interval defined in seconds, default: 3600s  )



###### How to run

* Clone this repository 
* Make sure you are using JDK 1.8 and Maven 3.x
* You can build the project and run the tests by running ```mvn clean package```
* Once successfully built, you can run the application by one of these two methods:
```
        java -jar target/marvel-characters-api-0.0.1-SNAPSHOT.jar
or
        mvn spring-boot:run
```
