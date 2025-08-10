FROM maven:3.9.11-eclipse-temurin-21 as build
COPY pom.xml .
COPY src/ src/
RUN mvn -f pom.xml -Pprod clean package

FROM eclipse-temurin:21-jre as run
RUN useradd dyma
USER dyma
COPY --from=build target/dyma-tennis.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
