FROM amazoncorretto:11-alpine-jdk
ARG JAR_FILE=target/*.jar
ARG PROFILES
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILES}", "-jar", "app.jar"]
