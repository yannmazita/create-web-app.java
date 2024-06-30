### Base stage ###
FROM eclipse-temurin:22-alpine as build

# Install Maven
RUN apk add --no-cache maven
WORKDIR /app

# Copy project file, download dependencies, copy other source files, then package application
COPY pom.xml /app
RUN mvn dependency:go-offline -B
COPY ./ /app
RUN mvn clean package -DskipTests

### Final stage ###
FROM eclipse-temurin:22-alpine as final

WORKDIR /app
# Copy the jar from the build stage
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8000
