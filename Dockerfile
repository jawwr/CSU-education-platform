FROM maven:3.9.9-amazoncorretto-21 as builder
WORKDIR /src
COPY . /src
RUN mvn clean package -DskipTests

FROM maven:3.9.9-amazoncorretto-21
WORKDIR /app
COPY --from=builder /src/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]