# Movies REST API Using Spring Boot
Movies REST API for **Xsis Mitra Utama** backend developer test.
Contains common endpoints for CRUD operation through GET, POST, PATCH & DELETE HTTP methods.

## Installation
- Make sure java and maven is installed
- Navigate to project root
### Option 1
- Run `mvn spring-boot:run`
### Option 2
- Run `mvn clean package`
- Run `java -jar target/rest-0.0.1-SNAPSHOT.jar`
### Option 3: Docker
- Make sure docker & docker-compose is installed
- Run `docker-compose up`

App is now running on http://localhost:8080

## Endpoints
- `GET` /movies --- returns a list of all movies
- `GET` /movies/{id} --- returns a movie detail
- `POST` /movies --- inserts a movie
- `PATCH` /movies/{id} --- updates a movie
- `DELETE` /movies/{id} --- deletes a movie

