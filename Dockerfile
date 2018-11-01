FROM anapsix/alpine-java:8

COPY ./app.jar app.jar

CMD ["java", "-jar", "app.jar"]
