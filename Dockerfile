# Stage 1 - Build the application
FROM maven:3.9.9-eclipse-temurin-21 As version
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2 - Run the application
FROM eclipse-temurin:21-jre
COPY --from=version /app/target/expensemanager-0.0.1-SNAPSHOT.jar app.jar
CMD ["java","-jar","app.jar"]


