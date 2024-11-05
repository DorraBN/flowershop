# Step 1: Build the application
FROM maven:3.8.5-openjdk-17 AS build


WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Step 2: Create a new image with only the necessary artifacts
FROM openjdk:11-jre-slim
COPY --from=build /app/target/flowers-0.0.1-SNAPSHOT.jar app.jar

# Expose the port on which Spring Boot listens (default 8080)
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
