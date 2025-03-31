I setting up Dockerfile for my transactions-service Springboot application.

Points to conside while setting up Dockerfile:
1. My module has dependency on one of my custom jar file.

Can you please help me modify this file:

FROM maven:3.8.5-openjdk-22 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:22-jdk-slim
COPY --from=build /target/tarnsactions-1.0.0.jar tarnsactions.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","tarnsactions.jar"]