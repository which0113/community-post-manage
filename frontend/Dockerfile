FROM nginx

WORKDIR /usr/share/nginx/html/
USER root

COPY ./docker/nginx.conf /etc/nginx/conf.d/default.conf

COPY ./dist  /usr/share/nginx/html/

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]

# 构建：docker build -t freefish-community-frontend:v0.0.1 .
# 运行：docker run -p 80:80 -d freefish-community-frontend:v0.0.1