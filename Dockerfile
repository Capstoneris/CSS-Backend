FROM openjdk:8-alpine
WORKDIR /app
COPY ./target/CSS-Backend-1.0-SNAPSHOT-jar-with-dependencies.jar app.jar
EXPOSE 8081
EXPOSE 8082
ENTRYPOINT  ["java","-jar","app.jar"]