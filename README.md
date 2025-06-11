# ToDoList API - Documentação

![Java](https://img.shields.io/badge/Java-17-007396?logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.5-6DB33F?logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-4169E1?logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-20.10-2496ED?logo=docker&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-3.0-85EA2D?logo=swagger&logoColor=black)
![Status](https://img.shields.io/badge/Status-Production-brightgreen)

Documentação técnica da API ToDoList desenvolvida com Spring Boot e implantada no Render.com.

### 📋 Funcionalidades

✅ CRUD completo de tarefas

✅ Autenticação e autorização (JWT)

✅ Validação de dados

✅ Documentação automática com Swagger

✅ Containerização com Docker

✅ Pronta para deploy em Render.com

### 🛠 Tecnologias
Java 17

Spring Boot 3.1

Spring Web

Spring Data JPA

Spring Security

Lombok - Para redução de boilerplate code

PostgreSQL - Banco de dados relacional

Swagger - Documentação da API

Docker - Containerização da aplicação

Render.com - Plataforma de deploy

### 🚀 Como executar
Pré-requisitos
Java 17 JDK

Maven

Docker (opcional)

Conta no Render.com (para deploy)

### 🚦 Endpoints da API
<h4> Autenticação </h4>

POST	/api/auth/signup	-- Registrar novo usuário

POST	/api/auth/signin	-- Autenticar usuário

<h4> Tarefas (Requirem autenticação) </h4>

GET	/api/tasks	-- Listar todas as tarefas

POST	/api/tasks	-- Criar nova tarefa

GET	/api/tasks/{id}	 -- Obter tarefa específica

PUT	/api/tasks/{id} 	-- Atualizar tarefa

DELETE	/api/tasks/{id}	 -- Remover tarefa


### Execução Local
1. Clone o repositório 

2. Configure o arquivo application.properties:

<h4> # Database </h4>

spring.datasource.url=jdbc:postgresql://localhost:5432/todolist
spring.datasource.username=postgres
spring.datasource.password=senha

<h4> # JWT </h4>

jwt.secret=seuSegredoSuperSecreto
jwt.expiration=86400000 # 24h em ms

3. Inicie a aplicação:

mvn spring-boot:run

###  📚 Documentação Interativa
A API possui documentação Swagger disponível em:


https://[SEU-APP].onrender.com/swagger-ui.html

Ou localmente:


http://localhost:8080/swagger-ui.html


###  🚀 Deploy no Render.com

1. Crie um novo Web Service no Render.com

2. Selecione "Build and deploy from a Git repository"

3. Configure as variáveis de ambiente

4. Defina as configurações de build:

build:
  image: openjdk:17-jdk-slim
  commands:
    - ./mvnw clean package

5. Defina o comando de inicialização:

java -jar target/todolist-0.0.1-SNAPSHOT.jar

6. Salve e aguarde o deploy completar



