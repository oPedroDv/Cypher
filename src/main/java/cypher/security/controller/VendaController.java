package cypher.security.controller;

import cypher.security.dto.VendaDTO;
import cypher.security.fraud.MotorFraude;
import cypher.security.fraud.ResultadoAnalise;
import cypher.security.model.Venda;
import cypher.security.repository.FraudeRepository;
import cypher.security.repository.VendaRepository;

import java.sql.SQLException;
import java.util.Scanner;

public class VendaController {

    private final VendaRepository vendaRepository = new VendaRepository();
    private final FraudeRepository fraudeRepository = new FraudeRepository();
    private final MotorFraude motorFraude = new MotorFraude();
    private final Scanner scanner = new Scanner(System.in);

    public void processarVenda(Venda venda) throws SQLException {
        vendaRepository.salvar(venda); // salva primeiro, gera o id
        ResultadoAnalise resultado = motorFraude.avaliar(venda);
        VendaDTO dto = VendaDTO.fromResultado(resultado);

        switch (dto.getNivelRisco()) {
            case "BAIXO" -> {
                System.out.println("Venda Realizada com sucesso!");
                System.out.println("ID da compra: " + dto.getVendaId());
                System.out.println("Produto: " + dto.getNomeProduto());
                System.out.printf("Valor total: R$ %.2f%n", dto.getPreco());
            }
            case "MEDIO" -> {
                System.out.println("Atenção! Há a possibilidade de ser uma venda fraudulenta.");
                System.out.println("ID: " + dto.getVendaId());
                System.out.println("Produto: " + dto.getNomeProduto());
                System.out.printf("Valor total: R$ %.2f%n", dto.getPreco());
                System.out.print("Deseja continuar por sua conta e risco? (S/N): ");
                String resposta = scanner.nextLine().trim().toUpperCase();
                if (resposta.equals("S")) {
                    System.out.println("Venda registrada sob sua responsabilidade.");
                } else {
                    vendaRepository.deletar(venda.getId());
                    System.out.println("Venda cancelada.");
                }
            }
            case "ALTO" -> {
                vendaRepository.deletar(venda.getId());
                fraudeRepository.salvar(venda);
                System.out.println("ATENÇÃO! VENDA BLOQUEADA POR FRAUDULÊNCIA.");
                System.out.println("Nossa equipe entrará em contato em breve para o ressarcimento de ambas as partes.");
                System.out.println("Lamentamos profundamente o ocorrido e trabalharemos para que as devidas medidgetas administrativas sejam tomadas.");
            }
        }
    }
}