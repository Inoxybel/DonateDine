FROM openjdk:17-jdk-alpine3.14

LABEL maintainer="rm92900@fiap.com.br"

WORKDIR /app

COPY donatedine/target/donatedine-0.0.1-SNAPSHOT.jar /app

EXPOSE 8080

CMD ["java", "-jar", "/app/donatedine-0.0.1-SNAPSHOT.jar"]
