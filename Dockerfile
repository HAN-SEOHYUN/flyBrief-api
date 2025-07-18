FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app

# JAR 파일 복사
COPY build/libs/flybrief-api-0.0.1-snapshot.jar app.jar

# (선택) 환경변수 파일 복사
COPY .env .env
COPY .env.prod .env.prod

EXPOSE 8080

ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]