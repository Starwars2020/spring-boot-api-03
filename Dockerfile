FROM openjdk:8-jdk-alpine
RUN apk add --no-cache curl tar bash
VOLUME /tmp
ADD build/libs/spring-boot-api-03-0.1.0.jar app.jar
ENV JAVA_OPTS=""
<<<<<<< HEAD
ENTRYPOINT ["java","-jar","/app.jar"]
=======
ENTRYPOINT ["java","-jar","/app.jar"]
>>>>>>> 510312b03be0e08eeb0ed281c1aedbf283711462
