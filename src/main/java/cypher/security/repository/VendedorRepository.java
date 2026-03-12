package cypher.security.repository;

import cypher.security.database.DatabaseConfig;
import cypher.security.model.Departamento;
import cypher.security.model.Vendedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendedorRepository {

    private final DepartamentoRepository departamentoRepository = new DepartamentoRepository();

    public void salvar(Vendedor vendedor) throws SQLException {
        String sql = """
                INSERT INTO vendedores (nome, idade, email, cpf, quantidade_vendas, departamento_id)]
                VALUES (?, ?, ?, ?, ?, ?)""";

        try(Connection conn = DatabaseConfig.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vendedor.getNome());
            stmt.setInt(2, vendedor.getIdade());
            stmt.setString(3, vendedor.getEmail());
            stmt.setString(4, vendedor.getCpf());
            stmt.setInt(5, vendedor.getQuantidadeVendas());
            stmt.setLong(6, vendedor.getDepartamento().getId());

            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                vendedor.setId(keys.getLong(1));
            }
        }
    }

    public Vendedor buscarPorId(Long id) throws SQLException {
        String sql = "SELECT * FROM vendedores WHERE id = ?";

        try (Connection conn = DatabaseConfig.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapear(rs);
            }

        }
        return null;
    }

    public List<Vendedor> listar() throws SQLException {
        List<Vendedor> vendedores = new ArrayList<>();
        String sql = "SELECT * FROM vendedores";

        try (Connection conn = DatabaseConfig.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                vendedores.add(mapear(rs));
            }
        }
        return vendedores;
    }

    public void deletar(Long id) throws SQLException {
        String sql = "DELETE FROM vendedores WHERE id = ?";

        try(Connection conn = DatabaseConfig.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private Vendedor mapear(ResultSet rs) throws SQLException {
        Vendedor vend = new Vendedor();
        vend.setId(rs.getLong("id"));
        vend.setNome(rs.getString("nome"));
        vend.setIdade(rs.getInt("idade"));
        vend.setEmail(rs.getString("email"));
        vend.setCpf(rs.getString("cpf"));
        vend.setQuantidadeVendas(rs.getInt("quantidade_vendas"));

        Departamento departamento = departamentoRepository.buscarPorId(rs.getLong("departamento_id"));
        vend.setDepartamento(departamento);

        return vend;
    }
}