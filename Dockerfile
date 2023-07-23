FROM amazoncorretto:17.0.7-al2-generic

COPY ./build/libs/fine-svc-0.0.1-SNAPSHOT.jar /usr/app.jar
WORKDIR /usr
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]