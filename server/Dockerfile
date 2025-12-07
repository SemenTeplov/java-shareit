FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY target/shareit-server-0.0.1-SNAPSHOT.jar app.jar
RUN jar tf app.jar | grep -q BOOT-INF/ || \
    (echo "Error: Not a valid Spring Boot jar!" && exit 1)
ENTRYPOINT ["java", "-jar", "/app.jar"]