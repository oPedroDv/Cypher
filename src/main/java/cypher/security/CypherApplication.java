package cypher.security;

import cypher.security.model.Venda;
import cypher.security.repository.VendaRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class CypherApplication {

	public static void main(String[] args) {
		SpringApplication.run(CypherApplication.class, args);

		Venda venda = new Venda();
		venda.setProduto("Notebook");
		venda.setNomeVendedor("Carlos");
		venda.setPreco(1500.0);
		venda.setPrecoMercado(2200.0);
		venda.setCategoria("Eletrônicos");
		venda.setDataVenda("2026-03-11");
		venda.setQuantidadeVendasVendedor(30);
		venda.setNivelRisco("BAIXO");

		VendaRepository repo = new VendaRepository();
		try {
			repo.salvar(venda);
			System.out.println("Venda salva com sucesso!");
		} catch (SQLException e) {
			System.out.println("Erro ao salvar: " + e.getMessage());
		}
	}
}
