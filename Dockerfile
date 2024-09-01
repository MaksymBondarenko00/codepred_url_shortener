FROM openjdk:17

ENTRYPOINT ["java","-jar","/shortener.jar"]
EXPOSE 8080