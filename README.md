# 💻 Avante Sistema - API de Gerenciamento de Produtos

Este projeto é uma API RESTful completa desenvolvida para gerenciar produtos e categorias, atendendo aos requisitos de um sistema de inventário básico com operações CRUD (Create, Read, Update, Delete).


## 📄 Descrição do Projeto e Tecnologias Utilizadas

| Categoria | Tecnologia | Observação |
| :--- | :--- | :--- |
| Linguagem | **Java** | 21 (JDK utilizado na compilação) |
| Framework | **Spring Boot** | 3.x (API REST) |
| Persistência | **Hibernate / Spring Data JPA** | Utilizado para mapeamento relacional (ORM). |
| Banco de Dados | **PostgreSQL** | Utilizado como banco de dados em produção e local. |
| Orquestração Local | **Docker Compose** | Gerencia os contêineres da API, PostgreSQL e PgAdmin. |
| Documentação | **SpringDoc OpenAPI** | Gera a documentação interativa (Swagger UI). |

---

## ⚙️ Instruções de Instalação e Execução Local

Para executar a aplicação localmente, você deve utilizar o Docker Compose para configurar o ambiente de banco de dados.

### **Pré-requisitos**

* Docker e Docker Compose V2.
* Git.
* Uma IDE (IntelliJ IDEA ou VS Code).

### **Passos para Execução**

1.  **Clone o Repositório:**
    ```bash
    git clone https://github.com/claraa908/avante_sistema.git
    cd avante_sistema
    ```

2.  **Iniciar a Aplicação e a Infraestrutura (Docker Compose):**
    Execute o comando na pasta raiz. Ele irá construir a imagem Docker da API e iniciar os serviços (PostgreSQL e PgAdmin).

    ```bash
    docker-compose up -d
    ```

3.  Aguarde a confirmação de que todos os containers estão funcionando.

    *A API estará disponível em:* `http://localhost:8080`

4.  **Acessar o PgAdmin (Opcional):**
    * **URL:** `http://localhost:5050`
    * **Usuário:** `me@example.com`
    * **Senha:** `1234567` (Conforme definido no `docker-compose.yml`)

---

## 🔑 Variáveis de Ambiente Necessárias (Produção/Render)

As credenciais do banco de dados de produção são injetadas no Serviço Web do Render. Estas variáveis são necessárias para a conexão remota com o PostgreSQL.

| Variável | Descrição | Exemplo de Uso |
| :--- | :--- | :--- |
| `SPRING_DATASOURCE_URL` | URL de Conexão Interna do PostgreSQL (Render). | `jdbc:postgresql://dpg-d469h0je5dus73ckboe0-a:5432/avante_db_5xak` |
| `SPRING_DATASOURCE_USERNAME` | Usuário do Banco de Dados (ex: `postgres`). | `admin` |
| `SPRING_DATASOURCE_PASSWORD` | Senha do Banco de Dados. | `OhmiPYeMWqd3kglf4sShGRMI3Xrind9Q` |
| `SERVER_PORT` | Porta em que o serviço será executado. | `8080` |

---

## 📖 Documentação da API (Swagger UI)

A documentação interativa (Swagger UI), que detalha schemas, validações e status HTTP, está disponível no seguinte endereço:

* **Produção:** `https://avante-api.onrender.com/swagger-ui/index.html`

---

## ⚡ Exemplos de Requisições (CRUD)

### 1. Criar Categoria (`POST /api/categorias`)
```http
// REQUISIÇÃO
POST /api/categorias
{
  "nome": "Ficção Científica",
  "descricao": "Livros de fantasia espacial e futurismo."
}

// RESPOSTA (Status 201 Created)
{
  "id": 1,
  "nome": "Ficção Científica",
  "descricao": "Livros de fantasia espacial e futurismo."
}
```

### 2. Adicionar Produto (`POST /api/produtos`)
```http
// REQUISIÇÃO
POST /api/produtos
{
  "nome": "Duna",
  "descricao": "O clássico da Ficção Científica por Frank Herbert.",
  "preco": 65.50,
  "categoriaID": 1
}

// RESPOSTA (Status 201 Created)
{
  "id": 10,
  "nome": "Duna",
  "descricao": "O clássico da Ficção Científica por Frank Herbert.",
  "preco": 65.50,
  "categoria": {
    "id": 1,
    "nome": "Ficção Científica"
  }
}
```

### 3. Atualizar Produto (`PUT /api/produtos/{id}`)

```http
// REQUISIÇÃO
PUT /api/produtos/10
{
  "nome": "Duna: O Messias",
  "descricao": "Continuação da saga de Muad'Dib.",
  "preco": 68.90,
  "categoriaID": 1
}

// RESPOSTA (Status 200 OK)
{
  "id": 10,
  "nome": "Duna: O Messias",
  "descricao": "Continuação da saga de Muad'Dib.",
  "preco": 68.90,
  "categoria": {
    "id": 1,
    "nome": "Ficção Científica"
  }
}
```

### 4. Buscar Produto/Produtos com Filtro (`GET /api/produtos?categoriaID=X`)

```http
// REQUISIÇÃO
GET /api/produtos?categoriaID=1

// RESPOSTA (Status 200 OK)
[
  {
    "id": 10,
    "nome": "Duna: O Messias",
    "descricao": "Continuação da saga de Muad'Dib.",
    "preco": 68.90,
    "categoria": {
      "id": 1,
      "nome": "Ficção Científica"
    }
  }
]
```

### 5. Deletar Produto (`DELETE /api/produtos/{id}`)

```http
// REQUISIÇÃO
DELETE /api/produtos/10

// RESPOSTA (Status 204 No Content)
(Corpo vazio)

