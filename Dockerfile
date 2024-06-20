FROM maven:3.9.3-eclipse-temurin-17

COPY src /apitesting/Restassured/src

COPY pom.xml /apitesting/Restassured/pom.xml

WORKDIR /apitesting/Restassured

ENTRYPOINT mvn clean test