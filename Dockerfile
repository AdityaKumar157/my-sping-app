FROM maven:3.8.5-openjdk-22 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:22-jdk-slim
COPY --from=build /target/my-spring-app-0.0.1-SNAPSHOT.jar my-spring-app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","my-spring-app.jar"]
