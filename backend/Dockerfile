# Docker 镜像构建
FROM maven:3.5-jdk-8-alpine as builder

WORKDIR /app

COPY backend-0.0.1-SNAPSHOT.jar .

CMD ["java","-jar","backend-0.0.1-SNAPSHOT.jar","--spring.profiles.active=prod"]

# 构建：docker build -t freefish-community-backend:v0.0.1 .
# 运行：docker run -p 9001:9001 -d freefish-community-backend:v0.0.1