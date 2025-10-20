# Sistema de E-commerce

API REST para gerenciamento de produtos, pedidos e relatórios com autenticação JWT.

## Tecnologias

- Java 17
- Spring Boot 3.4
- Spring Security com JWT
- MySQL 8.4
- Docker
- Maven

## Como Rodar

### 1. Subir o banco de dados

```bash
docker compose up -d
```

### 2. Importar os dados iniciais

O dump já contém dados de exemplo (usuários, produtos, pedidos):

```bash
docker exec -i produtos-mysql mysql -uroot -proot < dump.sql
```

### 3. Compilar o projeto (opcional)

Para compilar e gerar o .jar executável:

```bash
mvn clean install
```

### 4. Rodar a aplicação

```bash
mvn spring-boot:run
```

Ou usando o .jar gerado (se rodou o `mvn clean install`):

```bash
java -jar target/produtos-0.0.1-SNAPSHOT.jar
```

A API estará disponível em `http://localhost:8080`

## Endpoints Disponíveis

### Autenticação

- `POST /auth/register` - Criar novo usuário
- `POST /auth/login` - Fazer login e obter token JWT

### Produtos

- `GET /produtos` - Listar todos os produtos
- `GET /produtos/{id}` - Buscar produto por ID
- `GET /produtos/categoria/{categoria}` - Filtrar por categoria
- `GET /produtos/buscar?nome={nome}` - Buscar por nome
- `POST /produtos` - Criar produto (apenas ADMIN)
- `PUT /produtos/{id}` - Atualizar produto (apenas ADMIN)
- `DELETE /produtos/{id}` - Deletar produto (apenas ADMIN)

### Pedidos

- `GET /pedidos/{id}` - Buscar pedido por ID
- `GET /pedidos/meus-pedidos` - Listar pedidos do usuário logado
- `POST /pedidos` - Criar novo pedido
- `PATCH /pedidos/pagar/{id}?tipoPagamento={tipo}` - Processar pagamento

### Relatórios (apenas ADMIN)

- `GET /relatorios/top-usuarios` - Top 5 usuários que mais compraram
- `GET /relatorios/ticket-medio` - Ticket médio por usuário
- `GET /relatorios/faturamento-mensal?ano={ano}&mes={mes}` - Faturamento mensal

## Testando a API

### 1. Criar um usuário administrador

```bash
curl -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin",
    "password": "123456",
    "role": "ADMIN"
  }'
```

### 2. Fazer login

```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin",
    "password": "123456"
  }'
```

Resposta:

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "username": "admin",
  "role": "ADMIN"
}
```

### 3. Usar o token nas requisições

Copie o token recebido e use no header `Authorization: Bearer {seu_token}`:

```bash
curl http://localhost:8080/produtos \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9..."
```

### 4. Criar um produto

```bash
curl -X POST http://localhost:8080/produtos \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer {seu_token}" \
  -d '{
    "nome": "Notebook",
    "descricao": "Notebook Dell Inspiron 15",
    "preco": 2999.90,
    "categoria": "ELETRONICOS",
    "quantidadeEstoque": 10
  }'
```

Categorias disponíveis: `ELETRONICOS`, `ROUPAS`, `ALIMENTOS`

### 5. Criar um pedido

```bash
curl -X POST http://localhost:8080/pedidos \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer {seu_token}" \
  -d '{
    "produtos": [
      {
        "produtoId": "uuid-do-produto",
        "quantidade": 2
      }
    ]
  }'
```

### 6. Pagar o pedido

```bash
curl -X PATCH "http://localhost:8080/pedidos/pagar/{id-do-pedido}?tipoPagamento=CARTAO_CREDITO" \
  -H "Authorization: Bearer {seu_token}"
```

Tipos de pagamento: `CARTAO_CREDITO`, `CARTAO_DEBITO`, `PIX`, `DINHEIRO`

## Observações

- A aplicação cria automaticamente as tabelas no banco através do Hibernate (ddl-auto=update)
- O dump.sql já contém alguns dados de exemplo
- Usuários ADMIN podem gerenciar produtos e acessar relatórios
- Usuários USER podem fazer pedidos e consultar produtos
- O sistema valida estoque automaticamente ao processar pagamentos
- Pedidos podem ser cancelados automaticamente se não houver estoque suficiente

## Comandos Úteis

### Maven

```bash
# Compilar o projeto
mvn clean compile

# Compilar e gerar .jar
mvn clean install

# Limpar build anterior
mvn clean
```

### Docker

```bash
# Parar o banco de dados
docker compose down

# Parar e remover todos os dados
docker compose down -v

# Ver logs do MySQL
docker compose logs -f mysql
```

### MySQL

```bash
# Acessar o MySQL diretamente
docker exec -it produtos-mysql mysql -uroot -proot produtos_db

# Gerar novo dump do banco
docker exec produtos-mysql mysqldump -uroot -proot produtos_db > dump.sql

# Importar dump
docker exec -i produtos-mysql mysql -uroot -proot < dump.sql
```

## Configurações

O arquivo `application.properties` contém as configurações principais:

- Porta: 8080
- Banco de dados: MySQL na porta 3307
- JWT expiration: 24 horas
- Username/password do banco: root/root
