FROM openjdk:17-jdk
WORKDIR /app
COPY target/demo-with-docker-0.0.1-SNAPSHOT.jar /app/demo.jar
ENV APP_PORT=8080
EXPOSE $APP_PORT
CMD ["java", "-jar", "demo.jar"]