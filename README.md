# jwt-angular-spring-boot-security

## Quick Overview

Pre-installed Requirements: Java 1.8+, Maven, Node.js, Node Package Manager (NPM), MySQL.

### Set-up: (*requires a few initialisation commands for both the Angular and Spring-Boot side*)

Go to the directory where you want to install the project and then run the following commands:
```bash
git clone https://github.com/mccarthyr/jwt-angular-spring-boot-security
cd jwt-angular-spring-boot-security/UI
npm install
cd ../API/athlete
```

From this location you need to edit the src/main/resource/application.properties file to add in your own username and password for the database connection. Once that is done then contine with the following:
```shell
mvn install
```
### Verify UI & API: 

**UI**

Go to jwt-angular-spring-boot-security/UI and issue the command:
```shell
ng serve
```
Then in your browser go to: ***localhost:4200***

**API**

Go to jwt-angular-spring-boot-security/API/athlete and issue the command:
```sh
mvn spring-boot:run
```
Then in your brwoser go to: ***localhost:8080***

