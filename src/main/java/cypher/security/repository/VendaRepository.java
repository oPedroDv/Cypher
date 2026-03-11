package cypher.security.repository;

import cypher.security.database.DatabaseConfig;
import cypher.security.model.Venda;

import java.sql.Connection; // -> conexão com o banco
import java.sql.PreparedStatement; //-> executa o SQL
import java.sql.ResultSet;
import java.sql.SQLException; //-> erro do Banco
import java.util.ArrayList;
import java.util.List;

public class VendaRepository {
    public void salvar(Venda venda) throws SQLException { // -> insere venda no banco

        String sql = """
                INSERT INTO vendas
                (produto, nome_vendedor, preco, preco_mercado, categoria, data_venda, quantidade_vendas_vendedor, nivel_risco)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = DatabaseConfig.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, venda.getProduto());
            stmt.setString(2, venda.getNomeVendedor());
            stmt.setDouble(3, venda.getPreco());
            stmt.setDouble(4, venda.getPrecoMercado());
            stmt.setString(5, venda.getCategoria());
            stmt.setString(6, venda.getDataVenda());
            stmt.setInt(7, venda.getQuantidadeVendasVendedor());
            stmt.setString(8, venda.getNivelRisco());

            stmt.executeUpdate();
        }
    }

    public List<Venda> listar() throws SQLException { // -> retorna todas as vendas

        List<Venda> vendas = new ArrayList<>();

        String sql = "SELECT * FROM vendas";
        try (Connection conn = DatabaseConfig.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Venda venda = new Venda();

                venda.setProduto(rs.getString("produto"));
                venda.setId(rs.getLong("id"));
                venda.setNomeVendedor(rs.getString("nome_vendedor"));
                venda.setPreco(rs.getDouble("preco"));
                venda.setPrecoMercado(rs.getDouble("preco_mercado"));
                venda.setCategoria(rs.getString("categoria"));
                venda.setQuantidadeVendasVendedor(rs.getInt("quantidade_vendas_vendedor"));
                venda.setNivelRisco(rs.getString("nivel_risco"));

                vendas.add(venda);
            }
        }
        return vendas;
    }

    public void deletar(Long id)  throws SQLException { // -> remove venda

        String sql = "DELETE FROM vendas WHERE id = ?";

        try (Connection conn = DatabaseConfig.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    public Venda buscarPorId(Long id) throws SQLException { // -> busca uma venda específica
        String sql = "SELECT * FROM vendas WHERE id = ?";

        try (Connection conn = DatabaseConfig.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Venda venda = new Venda();

                venda.setProduto(rs.getString("produto"));
                venda.setId(rs.getLong("id"));
                venda.setNomeVendedor(rs.getString("nome_vendedor"));
                venda.setPreco(rs.getDouble("preco"));
                venda.setPrecoMercado(rs.getDouble("preco_mercado"));
                venda.setCategoria(rs.getString("categoria"));
                venda.setQuantidadeVendasVendedor(rs.getInt("quantidade_vendas_vendedor"));
                venda.setNivelRisco(rs.getString("nivel_risco"));

                return venda;
            }

        } return null;
    }
}
