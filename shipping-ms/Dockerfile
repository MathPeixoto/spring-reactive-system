FROM openjdk:11
COPY target/shipping-0.0.4-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker","/app.jar"]
