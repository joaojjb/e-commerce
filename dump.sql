-- ==================================================
-- Dump do Banco de Dados
-- Sistema de Gerenciamento de Pedidos e Produtos
-- ==================================================
-- Autor: João Guilherme
-- Data: 2025-10-18
-- Versão: 1.0
-- ==================================================

-- Criar database
CREATE DATABASE IF NOT EXISTS produtos_db 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

USE produtos_db;

-- ==================================================
-- Tabela de Usuários
-- ==================================================

CREATE TABLE IF NOT EXISTS users (
    id BINARY(16) PRIMARY KEY COMMENT 'UUID do usuário',
    username VARCHAR(255) NOT NULL UNIQUE COMMENT 'Nome de usuário único',
    password VARCHAR(255) NOT NULL COMMENT 'Senha criptografada (BCrypt)',
    role VARCHAR(50) NOT NULL COMMENT 'Role do usuário: ADMIN ou USER',
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Data de criação',
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Data de atualização',
    
    INDEX idx_username (username),
    INDEX idx_role (role)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
COMMENT='Tabela de usuários do sistema';

-- ==================================================
-- Tabela de Produtos
-- ==================================================

CREATE TABLE IF NOT EXISTS produtos (
    id BINARY(16) PRIMARY KEY COMMENT 'UUID do produto',
    nome VARCHAR(255) NOT NULL COMMENT 'Nome do produto',
    descricao TEXT COMMENT 'Descrição detalhada do produto',
    preco DECIMAL(10, 2) NOT NULL COMMENT 'Preço do produto',
    categoria VARCHAR(100) NOT NULL COMMENT 'Categoria do produto',
    quantidade_estoque INT NOT NULL COMMENT 'Quantidade disponível em estoque',
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Data de criação',
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Data de atualização',
    criado_por BINARY(16) COMMENT 'UUID do usuário que criou o registro',
    atualizado_por BINARY(16) COMMENT 'UUID do usuário que atualizou o registro',
    
    INDEX idx_categoria (categoria),
    INDEX idx_nome (nome),

    CONSTRAINT fk_produtos_criado_por FOREIGN KEY (criado_por)
    REFERENCES users(id) ON DELETE SET NULL ON UPDATE CASCADE,
    CONSTRAINT fk_produtos_atualizado_por FOREIGN KEY (atualizado_por)
    REFERENCES users(id) ON DELETE SET NULL ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
COMMENT='Tabela de produtos do e-commerce';

-- ==================================================
-- Tabela de Pedidos
-- ==================================================

CREATE TABLE IF NOT EXISTS pedidos (
    id BINARY(16) PRIMARY KEY COMMENT 'UUID do pedido',
    status_pedido VARCHAR(50) NOT NULL COMMENT 'Status do pedido: PENDENTE, CANCELADO, PAGO',
    tipo_pagamento VARCHAR(50) COMMENT 'Tipo de pagamento: PIX, CREDITO, DEBITO, BOLETO',
    valor_total DECIMAL(10, 2) COMMENT 'Valor total do pedido',
    motivo_cancelamento TEXT COMMENT 'Motivo do cancelamento do pedido',
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Data de criação',
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Data de atualização',
    criado_por BINARY(16) COMMENT 'UUID do usuário que criou o registro',
    atualizado_por BINARY(16) COMMENT 'UUID do usuário que atualizou o registro',
    
    INDEX idx_status_pedido (status_pedido),
    INDEX idx_tipo_pagamento (tipo_pagamento),
    
    CONSTRAINT fk_pedidos_criado_por FOREIGN KEY (criado_por)
    REFERENCES users(id) ON DELETE SET NULL ON UPDATE CASCADE,
    CONSTRAINT fk_pedidos_atualizado_por FOREIGN KEY (atualizado_por)
    REFERENCES users(id) ON DELETE SET NULL ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
COMMENT='Tabela de pedidos do e-commerce';

-- ==================================================
-- Tabela de Produtos dos Pedidos (Relacionamento)
-- ==================================================

CREATE TABLE IF NOT EXISTS produtos_pedidos (
    id BINARY(16) PRIMARY KEY COMMENT 'UUID do item do pedido',
    pedido_id BINARY(16) NOT NULL COMMENT 'UUID do pedido',
    produto_id BINARY(16) NOT NULL COMMENT 'UUID do produto',
    quantidade INT NOT NULL COMMENT 'Quantidade do produto no pedido',
    preco_unitario DECIMAL(10, 2) NOT NULL COMMENT 'Preço unitário no momento da compra',
    preco_total DECIMAL(10, 2) NOT NULL COMMENT 'Preço total (quantidade * preço unitário)',
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Data de criação',
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Data de atualização',
    criado_por BINARY(16) COMMENT 'UUID do usuário que criou o registro',
    atualizado_por BINARY(16) COMMENT 'UUID do usuário que atualizou o registro',
    
    INDEX idx_pedido_id (pedido_id),
    INDEX idx_produto_id (produto_id),
    
    CONSTRAINT fk_produtos_pedidos_pedido FOREIGN KEY (pedido_id)
    REFERENCES pedidos(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_produtos_pedidos_produto FOREIGN KEY (produto_id)
    REFERENCES produtos(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT fk_produtos_pedidos_criado_por FOREIGN KEY (criado_por)
    REFERENCES users(id) ON DELETE SET NULL ON UPDATE CASCADE,
    CONSTRAINT fk_produtos_pedidos_atualizado_por FOREIGN KEY (atualizado_por)
    REFERENCES users(id) ON DELETE SET NULL ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
COMMENT='Tabela de relacionamento entre pedidos e produtos';