FROM openjdk:17
WORKDIR /app
COPY . /app
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests
RUN cp target/*.jar app.jar

EXPOSE 8090

CMD ["java", "-jar", "/app/app.jar"]
