# First stage: build the application
FROM maven:3.8.3 AS build
COPY . /app
WORKDIR /app

RUN mvn package -DskipTests

# Second stage: create a image
FROM openjdk:17

WORKDIR /app

COPY --from=build app/target/desafio-anotai-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]