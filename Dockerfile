FROM eclipse-temurin:17-jre
WORKDIR app
ADD trains-api-rest/target/trains-api-rest-0.0.1-SNAPSHOT.jar application.jar

CMD java -jar application.jar