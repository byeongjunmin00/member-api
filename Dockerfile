# Java 17 베이스 이미지
FROM eclipse-temurin:17-jdk-alpine

# jar 파일 복사
COPY build/libs/member-api-0.0.1-SNAPSHOT.jar app.jar

# 8080 포트
EXPOSE 8080

# 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
