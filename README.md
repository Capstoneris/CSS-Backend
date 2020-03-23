![Capturis Logo](https://www.capturis.com/wp-content/uploads/2014/07/capturis_logo.png)

# German Capstone Project 2020
## Capturis Session Share - Backend

## ✨ Overview
> Collaborative form editor, chat system, and feedback tool.

## Running the Project

##### In your terminal:
```
    // To run server locally on port 8080
    mvn clean test //<-- the test command is optional, though recommended
    mvn exec:java

    // If you want to run via Docker Image
    docker build . -t css-backend
    docker run -p 8081:8081 css-backend

    // To run Angular web client on port 4200
    // Navigate to the frontend repository
    npm install // <-- do this to make sure all Node.js packages are up to date
    ng serve -o
```

- Visit localhost:4200 in your browser.

## Learn Git with BitBucket Cloud

  - [https://www.atlassian.com/git/tutorials/learn-git-with-bitbucket-cloud#copy-and-add-files](https://www.atlassian.com/git/tutorials/learn-git-with-bitbucket-cloud#copy-and-add-files)

## Recommended Technology Stack

- Database - [PostgreSQL](https://www.postgresql.org/)
- Backend Language - [Java](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html)
- Build & Dependency Management Backend - [Apache Maven](https://maven.apache.org/)
- RESTful API Framework - [Jersey](https://eclipse-ee4j.github.io/jersey/)
- Build & Dependency Management Frontend - [Node.js & NPM](https://nodejs.org/en/)
- Frontend Language - [TypeScript](https://www.typescriptlang.org/)
- Fontend Framework - [Angular](https://angular.io/)
