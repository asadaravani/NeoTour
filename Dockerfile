FROM bellsoft/liberica-openjdk-alpine-musl:17

WORKDIR /app

COPY target/Neotour-0.0.1-SNAPSHOT.jar /app/NeoTour.jar

EXPOSE 8080


CMD ["java", "-jar", "Neotour.jar"]