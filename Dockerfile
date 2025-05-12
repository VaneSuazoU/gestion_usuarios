FROM openjdk:24-jdk-slim
WORKDIR /app

# Copia el jar
COPY target/gestion_usuarios-0.0.1-SNAPSHOT.jar app.jar

# Copia el wallet
COPY src/main/resources/wallet /app/wallet

ENTRYPOINT ["java", "-jar", "app.jar"]
