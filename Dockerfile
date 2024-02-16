
FROM openjdk:17-jdk-slim as build
WORKDIR /app
COPY target/PsyTestBalance-0.0.1-SNAPSHOT.jar /app/psytestbalance.jar
EXPOSE 1666
ENTRYPOINT ["java","-jar","/app/psytestbalance.jar"]



