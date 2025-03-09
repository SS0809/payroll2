FROM openjdk:17


WORKDIR /app

# Copy Maven wrapper and project files
COPY . /app

# Build the JAR inside the container
RUN ./mvnw clean package -DskipTests

# Copy the generated JAR file to the final container location
RUN cp target/*.jar app.jar

EXPOSE 8090

CMD ["java", "-jar", "/app/app.jar"]
