![Status: Concluído](https://img.shields.io/badge/status-concluído-green)
![Java](https://img.shields.io/badge/Java-21-blue?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green?logo=spring)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue?logo=postgresql)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-Frontend-green?logo=thymeleaf)
![JWT](https://img.shields.io/badge/JWT-Security-yellow)
![RSA](https://img.shields.io/badge/RSA-Criptografia-orange)

---

## 🧑‍💻 Autores

<div align="center">

| Nome | RM |
| :--- | :--- |
| **Vinicius Murtinho Vicente** | 551151 |
| **Lucas Barreto Consentino** | 557107 |
| **Gustavo Bispo Cordeiro** | 558515 |

</div>

## ✨ Funcionalidades Principais

Autenticação & Autorização com Spring Security usando JWT.
Criptografia de login e senha com RSA.
Cadastro de usuários com roles (ADMIN e USUARIO).
CRUD básico de usuários.
Proteção de endpoints por roles (ROLE_ADMIN e ROLE_USER).
Login via front-end com senha cifrada.
Endpoint para fornecer chave pública RSA para o front-end.
Geração de token JWT para autenticação em endpoints protegidos.

---

##🏗️ Arquitetura do Sistema
A aplicação segue arquitetura em camadas (Layered Architecture)

Frontend (HTML + JS + JSEncrypt)
      |
Controller Layer (AutenticacaoController)
      |
Service Layer (AuthorizationService, RSAService)
      |
Repository Layer (UsuarioRepository)
      |
Database (PostgreSQL)

---
##🛡️ Detalhes de Segurança
Autenticação: Usuários logam com login e senha criptografada em BCrypt.

Autorização: Perfis ROLE_ADMIN e ROLE_USER.

Endpoints Públicos:

/autenticacao/public-key → retorna chave pública RSA

/autenticacao/login → login do usuário

/autenticacao/registrar → cadastro de usuário

Endpoints Protegidos: qualquer outro endpoint exige JWT válido.

Criptografia RSA: login e senha enviados do front-end para o back-end são cifrados com RSA.

JWT: tokens para autenticação de endpoints protegidos, com expiração configurável.
---

##🛠️ Tecnologias Utilizadas
#### **Backend**

* Java 17
* Spring Boot 3.x
* Spring Web (MVC)
* Spring Data JPA
* Spring Security
* JWT
* Lombok

#### **Frontend**
HTML + JS + JSEncrypt (para criptografia RSA)

#### **Banco de Dados & Ferramentas**
PostgreSQL
Maven

---
Estrutura de Classes Importantes
* UsuarioEntity
* Representa o usuário; implementa UserDetails.
* Campos: id, login, password (BCrypt), role.
* Métodos: getAuthorities(), getUsername(), isAccountNonLocked(), etc.
* UsuarioRoles
* Enum (ADMIN, USUARIO) para roles.
* AuthorizationService
* Implementa UserDetailsService, carrega usuários do banco.
* UserConfig
* Define PasswordEncoder (BCrypt) e DaoAuthenticationProvider.
* SecurityConfig
* Define regras de acesso, endpoints públicos e privados, registra filtro JWT.
* JwtUtil
* Gera e valida tokens JWT, extrai username e verifica expiração.
* JwtAuthFilter
* Filtra requisições protegidas, valida JWT e autentica usuário.
* RSAService
* Criptografa e descriptografa login e senha via RSA.
* AutenticacaoController
* Endpoints:

GET /autenticacao/public-key → retorna chave pública RSA

POST /autenticacao/login → autentica usuário e retorna JWT
POST /autenticacao/registrar → cadastra novo usuário

---

<div>
  🗄️ Banco de Dados PostgreSQL
  
<img width="984" height="325" alt="image" src="https://github.com/user-attachments/assets/e1d3a43b-d32a-48e8-a032-9c9e0f57272f" />


</div>

---
🚀 Como Executar Localmente

* Pré-requisitos
* Java JDK 17 ou superior
* Uma instância do PostgreSQL acessível
* Git
* Maven
---
1-Passos

  
  git clone <URL_DO_REPOSITORIO>
  cd cp5

---

2-Configure application.properties

spring.datasource.url=jdbc:postgresql://localhost:5432/cp5_java

spring.datasource.username=seu_usuario

spring.datasource.password=sua_senha

spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true

spring.jpa.open-in-view=false


---
3-Compile e rode:

  mvn clean install
  mvn spring-boot:run

---
4-Acesse

  Front-end: http://localhost:8080/login.html

Endpoints REST: http://localhost:8080/autenticacao/login





