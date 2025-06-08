FROM maven:3-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package

FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /app/target/*.jar thepoc.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "thepoc.jar"]
