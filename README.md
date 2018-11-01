# Weathersong

A project that integrates with Spotify and OpenWeather API's
to suggest a playlist based on the temperature of a city.

## Running the app

The app is intended to be run with Docker and Docker Compose.

First you need to replace the API keys on docker-compose with your own.
All of `SPOTIFY_CLIENT_ID`, `SPOTIFY_CLIENT_SECRET` and `OPEN_WEATHER_API_KEY`
need to be provided on `docker-compose`.

When that's set just run

The utility make command `make up` is used to start the container on background.

The application can be reached on your `<docker-host>:8080`

## Usage

The app exposes two endpoints. One expects a city name and the other
the geographic coordinates for the location. They can be used as followed:

```
(GET) <host>/playlist/city/{city_name}
```

```
(GET) <host>/playlist/lat/{lat}/lng/{lng}
```

## Technologies

### Kotlin

I chose Kotlin for the familiarity and for the niceties that it brings.
Using it makes development a lot more productive.

### Spring Boot

Another choice based on familiarity. I've worked with the tool in the past
and chose to use it to make development quicker.

### RxJava 2

RxJava is a tool for making easier to deal with threads and sequencing
of asynchronous operations. It provides several operators for that purpose.
I've used it extensively here, for several reasons:

* Being able to chain operations makes error handling easier to centralize
* Useful operators like retryWhen make very idiomatic tasks like requesting
a new token when a new one has expired.
* Being supported by both Retrofit and Spring Boot it enables the code to be
read like a sequence of transformations making it more readable.
* Facilitates Threading through the usage of `Schedulers`. Useful in making
for example the authentication with Spotify single threaded just by passing
a Scheduler to`subscribeWith` 

### Retrofit

A very nice client for REST APIs. It is a very flexible tool enabling you to
use whatever serializer and http client that you want. It also supports
RxJava so that was a strong reason for me to use it.

### Docker

Docker is a technology based on LLC containers of Linux and it makes possible
to have isolated environments on a Linux machine. It is also an image format that
allows you to package your dependencies inside a Docker Image and have your application
be deployed in a predictable manner. I would have liked to use a mult-stage build
here which means that I would have two images, one for build and one for deployment which makes
the final image much lighter. However due to errors and time constraints I just provided the
jar and the Docker image just copies and runs it inside it.

### Monitoring

Used Actuator as a healthcheck tool for monitoring tools like Pingdom to verify if the
server is running. Ideally you would also use an exporter of Docker metrics to
a tool like Prometheus.

### AssertJ

Though tests may be lacking a lot in the project AssertJ was used for its nice
API and fluent assertions. 

### Cache

Spring Data Key was used to store values on a cache. It uses as HasMap as a underlying structure
and it is used primarily to store Spotify's authentication token. It could
have been used to cache a lot more like Spotify playlists and tracks. Ideally a more specific solution
like `Redis` would have facilitated more interesting logic. For example a timed cache could
have been used to suggest a different playlist by day. It also would have benefited
the scalability of the system.
