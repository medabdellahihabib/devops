# Use a base image with JDK 17
FROM openjdk:17-jdk-slim

# Set environment 
ENV APP_HOME=/app
WORKDIR $APP_HOME

# Copy the JAR file from the build
COPY target/*.jar app.jar

# Expose the application port
EXPOSE 4000

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
