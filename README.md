# wcc-coding-test

## Requirement

- Java SDK 11
- Maven 3.6.3 and above
- Internet connection to download dependencies from Maven repository

## Build

At the command prompt, type   

    mvn package

to build the jar file.

The build might take longer to build for first time. This because the test will try to import existing postal code with coordinate from the "ukpostcodes.csv" into internal h2 database. 

## Run

At the command prompt, type  

    java -jar interview-0.0.1-SNAPSHOT.jar

to run the application.  
This application using internal H2 database with database name 'wcc-test-db' which located in under $USERHOME. Where $USERHOME refer to current user home directory. You should able to find the database under $USERHOME/wcc-test-db.mv.db
## Provided API

Once the application had started up, you can try out API provided by this application from [here](http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config). 

## Security Concern

This application is not secure which means it can be access by anyone without giving any premission.
