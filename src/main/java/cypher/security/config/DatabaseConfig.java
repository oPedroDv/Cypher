package cypher.security.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConfig {
    private static final String URL = "jdbc:sqlite:vendas.db";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void inicializar() throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS categorias (
                    id INTEGER PRIMARY KEY,
                    nome VARCHAR(100) NOT NULL,
                    descricao VARCHAR(255)
                );
                CREATE TABLE IF NOT EXISTS produtos (
                    id INTEGER PRIMARY KEY,
                    nome VARCHAR(100),
                    tipo VARCHAR(100),
                    modelo VARCHAR(100),
                    cor VARCHAR(50),
                    fabricante VARCHAR(100),
                    preco_mercado DECIMAL(10, 2) NOT NULL,
                    categoria_id INTEGER NOT NULL,
                    FOREIGN KEY (categoria_id) REFERENCES categorias(id)
                );
                CREATE TABLE IF NOT EXISTS departamentos (
                    id INTEGER PRIMARY KEY,
                    nome VARCHAR(200) NOT NULL,
                    andar VARCHAR(50)
                );
                CREATE TABLE IF NOT EXISTS vendedores (
                    id INTEGER PRIMARY KEY,
                    nome VARCHAR(100),
                    idade INT,
                    email VARCHAR(50),
                    cpf VARCHAR(20) UNIQUE,
                    quantidade_vendas INT DEFAULT 0,
                    departamento_id INT NOT NULL,
                    FOREIGN KEY (departamento_id) REFERENCES departamentos(id)
                );
                CREATE TABLE IF NOT EXISTS vendas (
                    id INTEGER PRIMARY KEY,
                    preco DECIMAL(15, 2) NOT NULL,
                    data_venda DATETIME NOT NULL,
                    nivel_risco VARCHAR(10),
                    produto_id INTEGER NOT NULL,
                    vendedor_id INTEGER NOT NULL,
                    FOREIGN KEY (produto_id) REFERENCES produtos(id),
                    FOREIGN KEY (vendedor_id) REFERENCES vendedores(id)
                );
                CREATE TABLE IF NOT EXISTS fraude (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    venda_id INTEGER NOT NULL,
                    hora_deteccao DATETIME NOT NULL,
                    vendedor_id INTEGER NOT NULL,
                    FOREIGN KEY (venda_id) REFERENCES vendas(id),
                    FOREIGN KEY (vendedor_id) REFERENCES vendedores(id)
                );
                """;

        try (Connection conn = conectar();
             Statement stmt = conn.createStatement()) {
            for (String query : sql.split(";")) {
                if (!query.isBlank()) stmt.execute(query);
            }
        }
    }
}