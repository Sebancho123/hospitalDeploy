
FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/centro-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app_hospital.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_hospital.jar"]
