FROM anapsix/alpine-java:8

COPY ./target/weathersong-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]
