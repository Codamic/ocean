FROM java:8-alpine
MAINTAINER Your Name <you@example.com>

ADD target/ocean-0.0.1-SNAPSHOT-standalone.jar /ocean/app.jar

EXPOSE 8080

CMD ["java", "-jar", "/ocean/app.jar"]
