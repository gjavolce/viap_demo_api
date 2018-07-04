# Project Title

Viaplay Work Sample - Artist API

## Getting Started

This project demonstrates implementation of MusicService that collects data from three different services and combines the responses into a single response.

### Prerequisites

Please have installed:

* JDK 10
* Maven 3.5+
* Docker 1.18 (optional)

### Installing: Run Locally

After checking out the repo, you can run the project by running the following command from the root folder of the app

```
mvn spring-boot:run
```

This will run the project in the default Jetty server locally available at The app will be available on http://localhost:8080/v1/artists/{mbid}

### Installing: Run in Docker

If you would like to run the app in a Docker container, there is a Dockerfile included as well. The base image is taken from the official Oracle Docker repo for Java 10. You can build the docker image with the following commands:

First package the executable jar:

```
mvn clean package
```

Then build the Docker image based on the Dockerfile:

```
docker build . -t viaplay-api-docker
```

And run the the Docker container with:

```
docker run -d -p 1337:8080 --name viaplay-api-docker viaplay-api-docker:latest
```

The app will be available on http://localhost:1337/v1/artists/{mbid}

### Installing: Run with Redis caching

In order to improve performance, caching was introduced by using Redis. In order to connect the app with Redis cache, you can uncomment the annotation for enabling the cache in the main app class DemoApplicaton.java:

```java
...
// Uncoment the annotation below in order to enable caching
// Additionally, update the port:url of Redis in application.properties

// @EnableCaching

@SpringBootApplication
@EnableCircuitBreaker
@EnableAsync
@ComponentScan("com.viaplay")
@Slf4j
public class DemoApplication {
...
```

and then uncomment and update the following params for the Redis server (IP, port and/or credentials) in the application.properties file:

```java
#spring.cache.type=redis
#spring.redis.host=redis
#spring.redis.port=6379
```

### Installing: App and Redis with docker-compose

docker-compose file is available in order to demo the app and Redis cache without any additional setup. In order to run this docker-compose, first create a custom docker network:

```
docker network create viaplay
```

and then run

```
docker-composer up
```

This will get the prebuilt image from the Docker repository containing the app, as well as the latest version of Redis and run them under the custom `viaplay` network.

and then run The app will be available on http://localhost:4321/v1/artists/{mbid}

### Live DemoApplicaton

The demo app is also deployed on Amazon AWS as a microservice with the following components:

* **VPC**
* **Elastic Load Balancer**
* **Security Group**
* **EC2** - Three instances running the app behind the ELB, deployed in three availability zones
* **Redis** - caching EC2 instance in the same security group

The link to the load balancer is http://viap-elb-1-722781242.eu-west-2.elb.amazonaws.com/v1/artists/{mbid}

## Technology

* **Spring Boot** - Server side framework and container
* **Lombok** - Provides automated getter/setters and other boilerplate code
* **Docker** - Docker containers
* **Junit** - Unit testing framework
* **Mockito** - Mock and Stubs framework
* **Hystrix** - Circuit breaker for external services
* **PoDaM** - Object generation and data randomizing
* **Weddini Throttling** - API Throttling framework (the only one with custom repository included in pom.xml)
* **Redis** - In memory caching
* **Gson** - Google library for converting objects to/from JSON
* **Slf4J** - Logging framework

## Components

The application is structured in 3 separate decoupled tiers, for the controllers, domain service and integrations.

### Controllers

* **GET /v1/artists/{mbid}**

  endpoint for retrieving artist data in the requested format, takes the MusicBrainzID UUID format as query parameter

  Sample response:

  ```json
  {
    "mbid": "1946a82a-f927-40c2-8235-38d64f50d043",
    "description": "Tom & Ed met in history class at Manchester University in 1988. They started off as DJs known as \"......":
    "albums" : [
      {
          "title": "Live at The Social, Volume 1",
          "id": "10bb5166-bc97-3c03-8e61-0483096ccf76",
          "image": "http://coverartarchive.org/release/29d7c118-324b-410c-a103-b8564d111c72/2294813687.jpg"
      },
      {
          "title": "Surrender",
          "id": "22ca85ec-ee39-3895-aef9-dee5d5c2f4d6",
          "image": "http://coverartarchive.org/release/0117b17b-7721-49a9-a202-089f8d1b3d42/12104358387.jpg"
      },
      {
          "title": "Digital Decks",
          "id": "23e42583-7049-46c9-85e6-279077bf052b"
      }
    ]
  ```

* **GET /healthcheck **

  endpoint for simple healthcheck, returns a list of services and whether they are reachable with simple Http request.

  ```json
  {
    "MusicBrainz Service":"UP",
    "CoverArt Archive Service":"UP","
    Discogs Service":"UP"
  }
  ```

### Service

* **ViaplayArtistService**

  Artist service that validates the input, connects to the integrated services and generates the expected output.

### Integrations

* **MusicBrainzService** - External service for gathering the main data of the artists
* **CoverArtArchiveService** - External service for gathering album art covers
* **DiscogsService** - External service for gathering artists portfolio data

### Models

* **Viaplay models** - Lombok annotated representation of models for the service and controllers
* **MusicBrainz, Discogs, CoverArtArchive** - POJO classes representing the response from the integrated services. The conversion from JSON to Java objects was done with the help of http://www.jsonschema2pojo.org.

### Utils

Helper classes with various helper methods

## Business Logic

### External services

The app tries to reach out to the main data source, MusicBrainz which contains list of albums and list of other data sources. In case this service is unavailable, the controller responds with error status and code.

Based on the MusicBrainz data, the app tries to gather album art from CoverArtArchive. In order to gracefully degrade, if for some reason (missing ID, unparsable data, cover doesn't exist) the cover image is not found in the response, the final response does not include the `image` element.

The Discogs service provides detailed artist description and again if the service is unreachable, the app just picks up the artist name from the MusicBrainz response and does not respond with error.

### Circuit Breaking

The Hystrix framework provides easy to use mechanism for circuit breaking in case the requests towards the external services timeout or get stuck. In that case, the framework falls back to another default method. The timeout is set to 15 seconds.

### API Request Throttling

Based on the documentation from the external API's there's an limit of number of requests with the value of 1 request per second. For that reason, the API throttling was also introduced in the app. The limiting is 1 request / second from a single IP address.

### Parallel async requests

An artist can have lots of albums, which means we need to create async requests in order to have a reasonably fast response. If the requests were sequential, it would process them significantly slower.

### Unit testing

There is test coverage for a controlle and a service method with jUnit tests and fully mocked services and data.

## Sample requests

* **The Prodigy**
  https://musicbrainz.org/artist/4a4ee089-93b1-4470-af9a-6ff575d32704
* MBID: 4a4ee089-93b1-4470-af9a-6ff575d32704

```
curl  "viap-elb-1-722781242.eu-west-2.elb.amazonaws.com/v1/artists/4a4ee089-93b1-4470-af9a-6ff575d32704" | json_pp
```

* **The Chemical Brothers**
  https://musicbrainz.org/artist/1946a82a-f927-40c2-8235-38d64f50d043
* MBID: 1946a82a-f927-40c2-8235-38d64f50d043

```
curl  "viap-elb-1-722781242.eu-west-2.elb.amazonaws.com/v1/artists/1946a82a-f927-40c2-8235-38d64f50d043" | json_pp
```

* **The Beatles**
  https://musicbrainz.org/artist/b10bbbfc-cf9e-42e0-be17-e2c3e1d2600d
*

```
curl  "viap-elb-1-722781242.eu-west-2.elb.amazonaws.com/v1/artists/b10bbbfc-cf9e-42e0-be17-e2c3e1d2600d" | json_pp
```

* **Rage Against The Machine**
  https://musicbrainz.org/artist/3798b104-01cb-484c-a3b0-56adc6399b80
* MBID: 3798b104-01cb-484c-a3b0-56adc6399b80

```
curl  "viap-elb-1-722781242.eu-west-2.elb.amazonaws.com/v1/artists/3798b104-01cb-484c-a3b0-56adc6399b80" | json_pp
```
