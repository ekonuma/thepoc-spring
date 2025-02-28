FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY . /app
RUN ./mvnw package
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "target/*.jar"]