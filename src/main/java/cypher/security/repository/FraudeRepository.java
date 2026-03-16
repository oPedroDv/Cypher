package cypher.security.repository;

import cypher.security.config.DatabaseConfig;
import cypher.security.model.Venda;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FraudeRepository {

    public void salvar(Venda venda) throws SQLException {
        String sql = """
                INSERT INTO fraude (venda_id, hora_deteccao, vendedor_id)
                VALUES (?, ?, ?)
                """;

        try (Connection conn = DatabaseConfig.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String agora = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            stmt.setLong(1, venda.getId());
            stmt.setString(2, agora);
            stmt.setLong(3, venda.getVendedor().getId());

            stmt.executeUpdate();
        }
    }
}