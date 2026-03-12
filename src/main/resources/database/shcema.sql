CREATE TABLE IF NOT EXISTS  categorias ( -- Eletronicos, Informatica, Periféricos
    id INTEGER PRIMARY KEY,
    nome VARCHAR(100) NOT NULL, -- Tratado com TEXT, OBRIGATÓRIO
    descricao VARCHAR(255) -- opcional
);

CREATE TABLE IF NOT EXISTS produtos (
    id INTEGER PRIMARY KEY,
    nome VARCHAR(100),
    tipo VARCHAR(100),
    modelo VARCHAR(100),
    cor VARCHAR(50),
    marca VARCHAR(100),
    preco_mercado DECIMAL(10, 2) NOT NULL,
    categoria_id INTEGER NOT NULL,
    FOREIGN KEY (categoria_id) REFERENCES categorias(id) -- Garante que a categoria_id referencie os id's da tabela categoria (Chave estrangeira)
);

CREATE TABLE IF NOT EXISTS departamentos (
    id INTEGER PRIMARY KEY,
    nome VARCHAR(200) NOT NULL, -- Informatica, por exemplo
    andar VARCHAR(50) -- Andar do prédio
);

CREATE TABLE IF NOT EXISTS vendedores (
    id INTEGER PRIMARY KEY,
    nome VARCHAR(100),
    idade INT,
    email VARCHAR(50),
    cpf VARCHAR(20) UNIQUE, -- Unique garante que cada cpf seja unico
    quantidade_vendas INT DEFAULT 0,
    departamento_id INT NOT NULL,
    FOREIGN KEY (departamento_id) REFERENCES departamentos(id)
    
);

CREATE TABLE IF NOT EXISTS vendas (
    id INTEGER PRIMARY KEY,
    preco DECIMAL(15, 2) NOT NULL,
    data_venda VARCHAR(20) NOT NULL,
    nivel_risco VARCHAR(10),  -- "Baoxo" | "Médio" | "Alto"
    produto_id INTEGER NOT NULL,
    vendedor_id INTEGER NOT NULL,
    FOREIGN KEY (produto_id) REFERENCES produtos(id),
    FOREIGN KEY (vendedor_id) REFERENCES vendedores(id)
);