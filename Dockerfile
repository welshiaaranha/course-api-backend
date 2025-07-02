# Use official OpenJDK image for Java 17
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml
COPY mvnw .
COPY mvnw.cmd .
COPY pom.xml .

# Copy .mvn directory
COPY .mvn .mvn

# Fix permissions explicitly
RUN chmod +x mvnw 

# Now use the executable
RUN ./mvnw dependency:go-offline

# Copy source code
COPY src src

# Package the application
RUN ./mvnw package -DskipTests

# Expose port 8080
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/courses-api-0.0.1-SNAPSHOT.jar"]
