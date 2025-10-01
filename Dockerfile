# Stage 1: build
FROM eclipse-temurin:17 AS build
WORKDIR /app

# Copia arquivos necessários
COPY pom.xml mvnw ./
COPY .mvn .mvn
COPY src src

# Corrige permissão do mvnw dentro do container
RUN chmod +x mvnw

# Build do projeto
RUN ./mvnw -B -DskipTests clean package

# Stage 2: runtime
FROM eclipse-temurin:17-jre
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
