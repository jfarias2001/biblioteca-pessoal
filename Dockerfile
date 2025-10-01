# Stage 1: build
FROM eclipse-temurin:17 as build
WORKDIR /app

COPY pom.xml mvnw ./
COPY .mvn .mvn
COPY src src

# Corrige a permissão
RUN chmod +x mvnw

# Agora sim roda o Maven Wrapper
RUN ./mvnw -B -DskipTests clean package

# Stage 2: runtime
FROM eclipse-temurin:17-jre
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
