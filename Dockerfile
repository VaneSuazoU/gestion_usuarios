FROM openjdk:24-jdk-slim
WORKDIR /app
COPY target/gestion_usuarios-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
