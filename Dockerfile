FROM openjdk:latest
COPY ./target/seMethodsGroup6.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "seMethodsGroup6.jar", "db:3306"]