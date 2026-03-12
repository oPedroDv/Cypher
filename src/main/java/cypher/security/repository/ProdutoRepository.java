package cypher.security.repository;

import cypher.security.database.DatabaseConfig;
import cypher.security.model.Categoria;
import cypher.security.model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {
    public final CategoriaRepository categoriaRepository = new CategoriaRepository();

    public void salvar(Produto produto) throws SQLException {
        String sql = """
                INSERT INTO produtos = = (nome, modelo, cor, fabricante, preco_mercado, categoria_id)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try(Connection conn = DatabaseConfig.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getModelo());
            stmt.setString(3, produto.getCor());
            stmt.setString(4, produto.getFabricante());
            stmt.setDouble(5, produto.getPrecoMercado());
            stmt.setLong(6, produto.getCategoria().getId());

            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                produto.setId(keys.getLong(1));
            }
        }
    }

    public Produto buscarPorId(Long id) throws SQLException{
        String sql = "SELECT * FROM produtos WHERE id = ?";

        try (Connection conn = DatabaseConfig.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                return mapear(rs);
            }
        } return null;
    }

    public List<Produto> listar() throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM departamentos";

        try (Connection conn = DatabaseConfig.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                produtos.add(mapear(rs));
            }
        }return produtos;
    }

    public void deletar(Long id) throws SQLException {
        String sql = "DELETE FROM produtos WHERE id = ?";

        try(Connection conn = DatabaseConfig.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private Produto mapear(ResultSet rs) throws SQLException {
        Produto prod = new Produto();
        prod.setId(rs.getLong("id"));
        prod.setNome(rs.getString("nome"));
        prod.setModelo(rs.getString("modelo"));
        prod.setCor(rs.getString("cor"));
        prod.setFabricante(rs.getString("fabricante"));
        prod.setPrecoMercado(rs.getDouble("preco_mercado"));

        Categoria categoria = categoriaRepository.buscarPorId(rs.getLong("categoira_id"));
        prod.setCategoria(categoria);

        return prod;
    }
}

