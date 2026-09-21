FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /workspace

COPY gradlew build.gradle settings.gradle ./
COPY gradle ./gradle
RUN chmod +x gradlew

COPY src ./src
RUN ./gradlew clean bootWar --no-daemon

FROM eclipse-temurin:21-jre-alpine

RUN addgroup -S atumari && adduser -S atumari -G atumari

WORKDIR /app
COPY --from=builder /workspace/build/libs/*.war /app/atumari.war

USER atumari
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/atumari.war"]
