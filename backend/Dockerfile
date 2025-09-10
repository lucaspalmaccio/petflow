# Usando Java 17
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copie o JAR já construído pelo Maven
COPY target/*.jar app.jar

# Exponha a porta do Spring Boot
EXPOSE 8080

# Rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
