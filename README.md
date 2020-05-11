![Capturis Logo](https://www.capturis.com/wp-content/uploads/2014/07/capturis_logo.png)

# German Capstone Project 2020
## Capturis Session Share - Backend

## ✨ Overview
> Collaborative form editor, chat system, and feedback tool.

## Running the Project

### docker-compose
If you want to use docker-compose to run the backend server and database, you need to configure the following things:
* Install docker (https://docs.docker.com/) and make sure you can run the "hello-world" image
* (if on Linux) Install docker-compose
* Create and add a GitHub Personal Access Token for Docker
    * Log into your GitHub Account
    * Open https://github.com/settings/tokens
    * Select "Generate new Token"
    * Select "read:packages" as scope
    * Add a speaking Note for the Token ("Docker" for example)
    * Click on "Generate token"
* Use docker login to get access to GitHub-Packages
    * `docker login docker.pkg.github.com`
    * Enter your GitHub username as Username
    * Enter the generated private access token as password

After that clone and navigate into the backend server repository in a terminal and execute
```
docker-compose up
```

### In your terminal:
```
    // To run server locally on port 8080
    mvn clean test //<-- the test command is optional, though recommended
    mvn exec:java


    // If you want to run via Docker Image
    docker build . -t css-backend
    docker run -p 8081:8081 --name backend-testrun --rm -it css-backend


    // To run Angular web client on port 4200
    // Navigate to the frontend repository
    npm install // <-- do this to make sure all Node.js packages are up to date
    ng serve -o
```

- Visit localhost:4200 in your browser.

## For development

### Database

You can use Docker for the database, which will automatically be configured as needed.
Do do that, follow the configuration steps:
* Install docker (https://docs.docker.com/) and make sure you can run the "hello-world" image
* (if on Linux) Install docker-compose

After that you can execute

`docker-compose up database`

in your terminal to start the database.

## Authentication
### Login

* POST to `/api/auth/login` with Body
```json
{
  "username": "Mr. Foo",
  "password": "12345"
}
```
* Responses
  * Successful:
    * If user exists a JWT is generated (name `token`) and status 200 is returned
  * Unsuccessful:
    * If user does not exist, Status 401 is returned
    * If something goes wrong in the process (SQL or JWT problems), Status 500 is returned

### Check Authentication

* GET to `/api/auth`
* Responses
    * Status 200 is returned when logged in
    * Status 401 is returned when logged out
    
### Logout

* POST to `/api/logout`
* Responses
    * Status 200 is returned when logout successful
    * Status 401 is returned when logout not successful (for example when already logged out)

## Websocket Protocol
### Used objects
```json
{
  "User": {
    "id": "long",
    "username": "string"
  },
  "Group": {
    "id": "long",
    "title": "string"
  },
  "Invitation": {
    "host": "User",
    "message": "string",
    "timestamp": "long"
  },
  "ChatMessage": {
    "timestamp": "long",
    "sentBy": "User",
    "content": "string"
  },
  "InputfieldState": {
    "fieldId": "string",
    "value": "string",
    "selections": "InputfieldSelections[]"
  },
  "InputfieldSelection": {
    "user": "User",
    "start": "integer",
    "end": "integer"
  }
}
```

### Messages
```json
{
  "client-side": {
    "login": {
      "token": "string"
    },
    "start-session": {
      "message": "string",
      "group": "Group",
      "users": "User[]",
      "timestamp": "long",
      "inputfieldStates": "InputfieldState[]"
    },
    "join-session": {
      "host": "User"
    },
    "send-chat-message": {
      "timestamp": "long",
      "message": "string"
    },
    "inputfield-interaction": {
      "fieldId": "string",
      "changed": "boolean",
      "oldValue": "string",
      "newValue": "string",
      "selectionStart": "integer",
      "selectionEnd": "integer"
    },
    "leave-session": {},
    "kick-member": {
      "member": "User"
    }
  },
  "server-side": {
    "hello": {
      "invitations": "Invitation[]"
    },
    "invitation-list-update": {
      "invitations": "Invitation[]"
    },
    "session-started": {},
    "session-joined": {
      "host": "User",
      "state": "InputfieldState[]",
      "chatHistory": "ChatMessage[]"
    },
    "member-list-update": {
      "users": "User[]"
    },
    "chat-message": {
      "timestamp": "long",
      "sentBy": "User",
      "message": "string"
    },
    "inputfield-changed": {
      "user": "User",
      "state": "InputfieldState"
    },
    "session-left": {
      "reason": "string"
    },
    "error": {
      "status": "integer",
      "message": "string"
    }
  }
}
```    
    

## Recommended Technology Stack

- Database - [PostgreSQL](https://www.postgresql.org/)
- Backend Language - [Java](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html)
- Build & Dependency Management Backend - [Apache Maven](https://maven.apache.org/)
- RESTful API Framework - [Jersey](https://eclipse-ee4j.github.io/jersey/)
- Build & Dependency Management Frontend - [Node.js & NPM](https://nodejs.org/en/)
- Frontend Language - [TypeScript](https://www.typescriptlang.org/)
- Frontend Framework - [Angular](https://angular.io/)
