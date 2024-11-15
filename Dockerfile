FROM openjdk:23-jdk

ADD target/Ziggs_Backend-0.0.1-SNAPSHOT.jar app.jar

COPY target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 8080
