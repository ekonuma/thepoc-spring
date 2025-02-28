FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY . /app
RUN ./mvnw package
COPY target/*.jar /app/target/thepoc.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/target/thepoc.jar"]