FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY . /app
RUN mvn clean package
COPY /app/target/*.jar thepoc.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "thepoc.jar"]