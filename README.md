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
  - `marvel.comics.api.public-key=` (Replace with your Marvel API public key)
  - `marvel.comics.api.private-key=` (Replace with your Marvel API private key)
  - `marvel.comics.api.base.url=http://gateway.marvel.com:80/v1/public` (Marvel API base url)
  - `marvel.comics.api.characters.cache.update=300` (Characters cache update interval defined in seconds)
  - `google.translation.script.app.url=https://script.google.com/macros/s/AKfycbwzQswwgs0_nqT5KwlkejYGyYsJBQnsj6iHz1LVt9s-B9yIQd8c/exec` (Google LanguageApp translation script app url)

###### Sign up for Marvel Developer API Key

https://developer.marvel.com/

###### Google translation script

```html
var mock = {
  parameter:{
    q:'Hello',
    target:'pt'
  }
};

function doGet(t) {
  t = t || mock;

  var sourceText = t.parameter.q || '';
  var targetLang = t.parameter.target || 'en';
  var translatedText = LanguageApp.translate(sourceText, 'en', targetLang, {contentType: 'html'});

  return ContentService.createTextOutput(translatedText).setMimeType(ContentService.MimeType.JSON);
}
```

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

###### Swagger UI

http://localhost:8080/swagger-ui.html


###### Test with Postman

https://www.getpostman.com/collections/c2dfc62a690edc774320