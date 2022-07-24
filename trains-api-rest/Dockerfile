FROM maven:3 as building-app
WORKDIR app

ADD src src
ADD pom.xml pom.xml
RUN mvn package

FROM eclipse-temurin:17-jre
WORKDIR app
COPY --from=building-app /app/target/trains-api-rest-0.0.1-SNAPSHOT.jar application.jar

CMD java -jar application.jar