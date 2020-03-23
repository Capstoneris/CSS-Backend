FROM openjdk:8-alpine
VOLUME /tmp
EXPOSE 8081
COPY ./target/CSS-Backend-1.0-SNAPSHOT-jar-with-dependencies.jar app.jar
ENTRYPOINT  ["java","-jar","/app.jar"]