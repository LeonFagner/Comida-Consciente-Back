# 🍽️ Comida Consciente - Backend

Este é o repositório backend do projeto **Comida Consciente**, uma aplicação web desenvolvida com o objetivo de conectar estabelecimentos doadores de alimentos, ONGs e pessoas físicas, promovendo o combate ao desperdício de alimentos.

O backend foi construído em **Java** com **Spring Boot**, seguindo a arquitetura REST e boas práticas de organização por camadas (Controller, Service, Repository, DTOs e Entities). A persistência de dados é feita com **MySQL**, utilizando **JPA** para o mapeamento objeto-relacional.

---

## 🔧 Tecnologias Utilizadas

- Java 
- Spring Boot 
- Spring Data JPA
- MySQL
- Hibernate
- Lombok
- Postman (testes de API)
- Git

---

## 🧩 Funcionalidades Implementadas

### 🔐 Autenticação e Usuários
- Cadastro e login de usuários
- Endpoints para CRUD de usuários
- Filtragem por tipo de usuário

### 🎁 Doações
- Cadastro de doações
- Consulta de doações por tipo, status e origem
- Associação entre doações e usuários (ONGs e Estabelecimentos)

### 🌟 Avaliações e Pontuação
- Avaliação de doações/usuários
- Sistema de pontuação para gamificação social
- Ranking de estabelecimentos

---

## 🗂️ Estrutura do Projeto

```bash
src/
├── controller/        # Camada de entrada da API (REST Controllers)
├── service/           # Lógica de negócio
├── repository/        # Acesso ao banco de dados (JPA)
├── dto/               # Objetos de transferência de dados
├── model/             # Entidades JPA com herança
└── config/            # Configurações (segurança, CORS, etc.)
