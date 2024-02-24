# 멀티 스테이지 빌드를 위한 베이스 이미지
FROM maven:3.8.4-openjdk-11 AS builder

# 작업 디렉토리 설정
WORKDIR /app

# 필요한 파일 복사
COPY pom.xml .

# 필요한 패키지 설치
RUN mvn dependency:go-offline

# 애플리케이션 소스 코드 복사
COPY src ./src

# 애플리케이션 빌드
RUN mvn package -DskipTests

# 최종 이미지 생성
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
CMD ["java", "-jar", "app.jar"]