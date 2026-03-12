package cypher.security.repository;

import cypher.security.database.DatabaseConfig;
import cypher.security.model.Departamento;
import cypher.security.model.Vendedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartamentoRepository {

    public void salvar (Departamento departamento) throws Exception{
        String sql = "INSERT INTO departamentos (nome, andar) VALUES (?, ?)";

        try (Connection conn = DatabaseConfig.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, departamento.getNome());
            stmt.setString(2, departamento.getAndar());
            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                departamento.setId(keys.getLong(1));
            }
        }
    }

    public Departamento buscarPorId(Long id) throws SQLException{
        String sql = "SELECT * FROM departamentos WHERE id = ?";

        try (Connection conn = DatabaseConfig.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                return mapear(rs);
            }
        } return null;
    }

    public List<Departamento> listar() throws SQLException {
        List<Departamento> departamentos = new ArrayList<>();
        String sql = "SELECT * FROM departamentos";

        try (Connection conn = DatabaseConfig.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                departamentos.add(mapear(rs));
            }
        }return departamentos;
    }

    public void deletar(Long id) throws SQLException {
        String sql = "DELETE FROM departamentos WHERE id = ?";

        try(Connection conn = DatabaseConfig.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private Departamento mapear(ResultSet rs) throws SQLException {
        Departamento depart = new Departamento();
        depart.setId(rs.getLong("id"));
        depart.setNome(rs.getString("nome"));
        depart.setAndar(rs.getString("andar"));
        return depart;
    }


}
