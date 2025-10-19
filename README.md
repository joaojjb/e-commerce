# Sistema de Autenticação JWT

Sistema simples de autenticação com JWT usando Spring Security.

## 🚀 Como Executar

### Pré-requisitos

- Java 17+
- Maven 3.6+
- Docker Desktop

### 1. Subir o banco de dados MySQL

```bash
docker compose up -d
```

### 2. Verificar se o MySQL está rodando

```bash
docker compose ps
```

### 3. Importar estrutura do banco

```bash
docker exec -i produtos-mysql mysql -uroot -proot < dump.sql
```

### 4. Executar a aplicação

```bash
mvn spring-boot:run
```

### 5. Criar primeiro usuário via API

```bash
curl -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username": "admin", "password": "123456", "role": "ADMIN"}'
```

### 6. Parar o banco de dados

```bash
docker compose down
```

### 7. Limpar tudo (incluindo dados)

```bash
docker compose down -v
```

---

## 💾 Gerenciamento de Banco de Dados

### Gerar dump do banco

```bash
docker exec produtos-mysql mysqldump -uroot -proot produtos_db > dump.sql
```

### Importar dump do banco

```bash
docker exec -i produtos-mysql mysql -uroot -proot produtos_db < dump.sql
```

### Acessar MySQL diretamente

```bash
docker exec -it produtos-mysql mysql -uroot -proot produtos_db
```

📖 Para mais detalhes sobre gerenciamento do banco, consulte [DATABASE.md](DATABASE.md)

---

## 🔧 Configuração

- **Banco**: MySQL 8.4.0 (Docker na porta 3307)
- **Database**: produtos_db
- **User**: root
- **Password**: root
- **JWT Secret**: Configurado no application.properties
- **JWT Expiração**: 24 horas

## 📡 Endpoints

### Autenticação

- `POST /auth/login` - Fazer login
- `POST /auth/register` - Registrar novo usuário

### Produtos

- `GET /produtos` - Listar todos os produtos (todos os usuários)
- `GET /produtos/{id}` - Buscar produto por ID (todos os usuários)
- `GET /produtos/categoria/{categoria}` - Buscar produtos por categoria (todos os usuários)
- `GET /produtos/buscar?nome={nome}` - Buscar produtos por nome (todos os usuários)
- `POST /produtos` - Criar novo produto (apenas ADMIN)
- `PUT /produtos/{id}` - Atualizar produto (apenas ADMIN)
- `DELETE /produtos/{id}` - Deletar produto (apenas ADMIN)

## 🧪 Como Testar

### 1. Registrar um usuário

```bash
curl -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin",
    "password": "123456",
    "role": "ADMIN"
  }'
```

### 2. Fazer Login

```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin",
    "password": "123456"
  }'
```

**Resposta:**

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "username": "admin",
  "role": "ADMIN"
}
```

### 3. Testar Endpoint Público

```bash
curl http://localhost:8080/test/public
```

### 4. Criar um produto (ADMIN)

```bash
curl -X POST http://localhost:8080/produtos \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer SEU_TOKEN_ADMIN" \
  -d '{
    "nome": "Notebook Dell",
    "descricao": "Notebook Dell Inspiron 15, i5, 8GB RAM",
    "preco": 2999.90,
    "categoria": "ELETRONICOS",
    "quantidadeEstoque": 10
  }'
```

**Categorias disponíveis:** `ELETRONICOS`, `ROUPAS`, `ALIMENTOS`

### 5. Listar todos os produtos

```bash
curl http://localhost:8080/produtos \
  -H "Authorization: Bearer SEU_TOKEN"
```

### 6. Buscar produto por ID

```bash
curl http://localhost:8080/produtos/{id} \
  -H "Authorization: Bearer SEU_TOKEN"
```

### 7. Atualizar produto (ADMIN)

```bash
curl -X PUT http://localhost:8080/produtos/{id} \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer SEU_TOKEN_ADMIN" \
  -d '{
    "nome": "Notebook Dell Atualizado",
    "descricao": "Notebook Dell Inspiron 15, i7, 16GB RAM",
    "preco": 3499.90,
    "categoria": "ELETRONICOS",
    "quantidadeEstoque": 5
  }'
```

### 8. Deletar produto (ADMIN)

```bash
curl -X DELETE http://localhost:8080/produtos/{id} \
  -H "Authorization: Bearer SEU_TOKEN_ADMIN"
```

## 🔒 Como Funciona

1. **Login**: Usuário envia username/password
2. **Autenticação**: Spring Security verifica as credenciais
3. **Token**: JwtService gera um token JWT
4. **Proteção**: Filtro JWT intercepta requisições e valida o token
5. **Acesso**: Se token válido, permite acesso aos endpoints protegidos
6. **Auditoria**: Sistema registra automaticamente o usuário completo que criou/atualizou cada produto (relacionamento @ManyToOne)

## 👥 Usuários de Teste

Use o endpoint `/auth/register` para criar usuários. Exemplo:

```bash
# Criar usuário ADMIN
curl -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username": "admin", "password": "123456", "role": "ADMIN"}'

# Criar usuário USER
curl -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username": "user", "password": "123456", "role": "USER"}'
```

## 📁 Estrutura do Projeto

```
src/main/java/produtos/
├── config/
│   └── SecurityConfig.java                  # Configuração do Spring Security
├── controller/
│   ├── auth/
│   │   └── AuthController.java              # Endpoints de autenticação
│   └── produtos/
│       └── ProdutosController.java          # Endpoints de produtos
├── dto/
│   └── auth/
│       ├── LoginRequest.java                # DTO para login
│       ├── LoginResponse.java               # DTO para resposta de login
│       └── RegisterRequest.java             # DTO para registro
├── entity/
│   ├── User.java                            # Entidade User
│   └── Produtos.java                        # Entidade Produtos
├── enums/
│   └── Role.java                            # Enum de roles (ADMIN, USER)
├── repository/
│   ├── UserRepository.java                  # Repository para User
│   └── ProdutosRepository.java              # Repository para Produtos
├── security/
│   └── JwtAuthenticationFilter.java         # Filtro JWT
└── service/
    ├── auth/
    │   ├── AuthService.java                 # Interface do serviço de autenticação
    │   └── AuthServiceImpl.java             # Implementação do serviço de autenticação
    ├── CustomUserDetailsService.java        # UserDetailsService personalizado
    └── JwtService.java                      # Serviço JWT
```

### 🎯 Padrões Aplicados

- **Organização por Domínio**: Controllers, Services e DTOs organizados por contexto de negócio
- **Interface + Implementação**: Services seguem o padrão `Service` (interface) + `ServiceImpl` (implementação)
- **Convenção Java**: Segue as melhores práticas da comunidade Java/Spring
- **Separação de Responsabilidades**: Cada pacote tem sua responsabilidade clara
