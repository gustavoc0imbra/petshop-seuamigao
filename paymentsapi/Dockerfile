FROM maven:3.8.4-openjdk-17-slim as builder

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn package -DskipTests

FROM openjdk:17-alpine

COPY --from=builder /app/target/*.jar ./app.jar

EXPOSE 8083

CMD ["java", "-jar", "app.jar"]

