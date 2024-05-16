<p align="center">
    <img src=https://img.freefish.love/logo.png width=188/>
</p>
<h1 align="center">社区文章管理系统</h1>
<p align="center"><strong>基于SpringBoot + MyBatis + MySQL + Redis + Vue2 + ElementUI 的社区文章管理系统</strong></p>
<div align="center">
<a target="_blank" href="https://github.com/which0113/api-backend">
    <img alt="" src="https://github.com/which0113/api-backend/badge/star.svg?theme=gvp"/>
</a>
    <img alt="Maven" src="https://raster.shields.io/badge/Maven-3.8.1-red.svg"/>
<a target="_blank" href="https://www.oracle.com/technetwork/java/javase/downloads/index.html">
        <img alt="" src="https://img.shields.io/badge/JDK-1.8+-green.svg"/>
</a>
    <img alt="SpringBoot" src="https://raster.shields.io/badge/SpringBoot-2.3+-green.svg"/>
</div>

## 项目介绍

社区文章管理系统基于SpringBoot + MyBatis + MySQL + Redis + Vue2 + ElementUI，实现了用户注册、登录、发帖、评论、关注、搜索等其他基础的文章增删改查功能。

## 使用指导

### 克隆项目到本地

```bash
git clone git@github.com:which0113/community-post-manage.git
```

### 运行前端

#### 切换到前端项目

```bash
cd frontend
```

确保 node >= 16，查看 node 版本

```bash
node -v
```

#### 安装依赖

```bash
npm install
```

#### 启动

```bash
npm run serve
```

#### 其他

- [vue.config.js](frontend%2Fvue.config.js) 文件可修改前端启动地址， 默认：http://localhost:3001
- [.env](frontend%2F.env) 文件可修改对接后端服务器接口的地址，默认：http://localhost:9001

#### 部署前端

方法一：生成 dist 文件部署

```bash
npm run build
```

方法二：使用 docker 容器部署

构建：

```bash
docker build -t freefish-community-frontend:v0.0.1 .
```

运行：

```bash
docker run -p 80:80 -d freefish-community-frontend:v0.0.1
```

### 运行后端

#### 注意事项

- JDK 版本为 1.8
- MySQL 版本为 8.0+
- Maven 版本为 3.9+
- Redis 版本为 5.0+

#### 其他

- [application-dev.yml](backend%2Fapplication-dev.yml)  文件可修改 **服务启动端口、MySQL、Redis 配置等等**
- [ddl.sql](backend%2Fddl.sql) 文件是 MySQL 数据库文件，可  **Ctrl A 全选 + Ctrl Enter 执行** 快速初始化数据库
- 管理员默认 **账号 | 密码** 为：**admin | 123456**

#### 部署后端

方法一：生成 jar 包部署（跳过单元测试）

```
mvn package -DskipTests
```

方法二：使用 docker 容器部署

构建：

```bash
docker build -t freefish-community-backend:v0.0.1 .
```

运行：

```bash
docker run -p 9001:9001 -d freefish-community-backend:v0.0.1
```

## 项目展示

#### 主页

![home.png](doc%2Fhome.png)

#### 暗黑主题

![dark.png](doc%2Fdark.png)

#### 文章详情

![post.png](doc%2Fpost.png)

#### 文章评论

![pl.png](doc%2Fpl.png)

#### 文章发布

![post_f.png](doc%2Fpost_f.png)

#### 文章搜索

![search.png](doc%2Fsearch.png)

#### 文章管理

![all_post.png](doc%2Fall_post.png)

#### 文章编辑

![update.png](doc%2Fupdate.png)

## 技术栈

### 前端

- Vue2 构建 JavaScript 的框架
- ElementUI 美观易用的 UI 组件库

### 后端

- SpringBoot
- Spring MVC
- MySQL 数据库
- Redis 数据缓存
- Spring Security（JWT 安全校验）
- MyBatis-Plus 及 MyBatis X 代码自动生成
- Hutool、Apache Common Utils、Gson 等工具库