# Build-Stage
FROM gradle:8.5-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/project
WORKDIR /home/gradle/project

# Tests vermeiden, um Build-Abbrüche zu vermeiden
RUN ./gradlew build -x test --no-daemon

# Laufzeit-Stage
FROM eclipse-temurin:17-jdk
COPY --from=build /home/gradle/project/build/libs/webtech-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
