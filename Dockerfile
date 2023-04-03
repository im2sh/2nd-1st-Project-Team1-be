FROM openjdk:17-jdk-slim

ARG PROFILE
ENV JAR app.jar
COPY ./build/libs/${JAR} ${JAR}

ENTRYPOINT java -jar -Dspring.profiles.active=${PROFILE} /app.jar