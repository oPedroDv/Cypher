package cypher.security.repository;

import cypher.security.database.DatabaseConfig;
import cypher.security.model.Categoria;
import cypher.security.model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaRepository {
    public void salvar (Categoria categoria) throws SQLException{
        String sql = "INSERT INTO categorias (nome, descricao) VALUES (?, ?)";

        try(Connection conn = DatabaseConfig. conectar();
        PreparedStatement stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){

            stmt.setString(1, categoria.getNome());
            stmt.setString(2, categoria.getDescricao());
            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if(keys.next()){
                categoria.setId(keys.getLong(1));
            }
        }
    }

    public Categoria buscarPorId(Long id) throws SQLException{
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

    public List<Categoria> listar() throws SQLException {
        List<Categoria> produtos = new ArrayList<>();
        String sql = "SELECT * FROM categorias";

        try (Connection conn = DatabaseConfig.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                produtos.add(mapear(rs));
            }
        }return produtos;
    }

    public void deletar(Long id) throws SQLException {
        String sql = "DELETE FROM categorias WHERE id = ?";

        try(Connection conn = DatabaseConfig.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private  Categoria mapear(ResultSet rs) throws SQLException {
        Categoria categ = new Categoria();
        categ.setId(rs.getLong("id"));
        categ.setNome(rs.getString("nome"));
        categ.setDescricao(rs.getString("descricao"));
        return categ;
    }
}
