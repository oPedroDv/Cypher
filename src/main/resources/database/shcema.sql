CREATE TABLE IF NOT EXISTS vendas (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    produto TEXT,
    nome_vendedor TEXT,
    preco REAL,
    preco_mercado REAL,
    categoria TEXT,
    data_venda DATETIME,
    quantidade_vendas_vendedor INTEGER,
    nivel_risco TEXT
);