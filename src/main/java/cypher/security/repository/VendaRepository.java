package cypher.security.repository;

import cypher.security.database.DatabaseConfig;
import cypher.security.model.Venda;
import cypher.security.model.Produto;
import cypher.security.model.Vendedor;

import java.sql.*; // -> permite o java conectar e ler as infos do db
import java.util.ArrayList;
import java.util.List;

public class VendaRepository {
    private final ProdutoRepository produtoRepository = new ProdutoRepository(); // chama os outros repositórios para montar os objetos (aqui o claude salvou o pai)
    private final VendedorRepository vendedorRepository = new VendedorRepository();

    public void salvar (Venda venda) throws SQLException {
        String sql = """
                INSERT INTO vendas (preco, data_venda, nivel_risco, produto_id, vendedor_id)
                VALUES (?, ?, ?, ?, ?)
                """;

        try(Connection conn = DatabaseConfig.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, venda.getPreco());
            stmt.setString(2, venda.getDataVenda());
            stmt.setString(3, venda.getNivelRisco());
            stmt.setLong(4, venda.getProduto().getId());
            stmt.setLong(5, venda.getVendedor().getId());

            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys(); // cria uma tabela temporária com os id's criados
            if (keys.next()) {
                venda.setId(keys.getLong(1)); //verifica se o id foi criado e retorna ao objeto
            }
        }

    }

    public Venda buscarPorId(long id) throws SQLException {
        String sql = "SELECT * FROM vendas WHERE id = ?;";

        try(Connection conn = DatabaseConfig.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if  (rs.next()) {
                return mapear(rs);
            }

        } return null;

    }

    public List<Venda> listar() throws SQLException {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM vendas";

        try(Connection conn = DatabaseConfig.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                vendas.add(mapear(rs));
            }
        } return vendas;
    }


    public List<Venda> buscarPorVendedor(long vendedorId) throws SQLException {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM vendas WHERE vendedor_id = ?";

        try(Connection conn = DatabaseConfig.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, vendedorId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                vendas.add(mapear(rs));
            }
        } return vendas;
    }

    public List<Venda> buscarPorNivelrisco (String nivelRisco) throws SQLException {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM vendas WHERE nivel_risco = ?";

        try(Connection conn = DatabaseConfig.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nivelRisco);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                vendas.add(mapear(rs));
            }
        } return vendas;
    }

    public void deletar(long id) throws SQLException {
        String sql = "DELETE FROM vendas WHERE id = ?;";
        try(Connection conn = DatabaseConfig.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }



    private Venda mapear(ResultSet rs) throws SQLException {
        Venda venda = new Venda();
        venda.setId(rs.getLong("id"));
        venda.setPreco(rs.getDouble("preco"));
        venda.setDataVenda(rs.getString("data_venda"));
        venda.setNivelRisco(rs.getString("nivel_risco"));

        Produto produto = produtoRepository.buscarPorId(rs.getLong("produto_id"));
        venda.setProduto(produto);
        Vendedor vendedor = vendedorRepository.buscarPorId(rs.getLong("vendedor_id"));
        venda.setVendedor(vendedor);

        return venda;
    }
}
