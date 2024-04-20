FROM adoptopenjdk/openjdk17:alpine

WORKDIR /app

COPY target/Neotour-0.0.1-SNAPSHOT.jar /app/Neotour.jar

EXPOSE 8080


CMD ["java", "-jar", "Neotour.jar"]
