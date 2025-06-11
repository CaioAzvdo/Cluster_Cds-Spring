FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY target/crud-spring-0.0.1-SNAPSHOT.jar deploy_ghactions-1.0.0.jar
EXPOSE 8080
CMD ["java","-jar","deploy_ghactions-1.0.0.jar"]

