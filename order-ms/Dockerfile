FROM openjdk:11
COPY target/order-0.0.5-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker","/app.jar"]
