FROM openjdk:latest
COPY ./target/seMethodsGroup6-0.1.0.3-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "seMethodsGroup6-0.1.0.3-jar-with-dependencies.jar"]