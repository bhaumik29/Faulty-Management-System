# Build stage
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Runtime stage
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/target/CSProConnect-0.0.1-SNAPSHOT.jar CSProConnect.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "CSProConnect.jar"]