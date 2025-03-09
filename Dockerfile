FROM openjdk:17
ENV PAYROLL_URL=jdbc:mysql://turntable.proxy.rlwy.net:55140/railway
ENV PAYROLL_DB_USERNAME=root
ENV PAYROLL_DB_PASSWORD=YDuaSHQFXmATzrZOtIDZpnPvoCoyPnEz

WORKDIR /app

# Copy Maven wrapper and project files
COPY . /app

RUN chmod +x mvnw
# Build the JAR inside the container
RUN ./mvnw clean package -DskipTests

# Copy the generated JAR file to the final container location
RUN cp target/*.jar app.jar

EXPOSE 8090

CMD ["java", "-jar", "/app/app.jar"]
