package cypher.security.fraud;

import cypher.security.model.Venda;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class RegraCadastramentoRapido implements RegraFraude {
    @Override
    public boolean validar(Venda venda) {
        LocalDateTime dataCadastro = venda.getProduto().getDataCadastro();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dataVenda = LocalDateTime.parse(venda.getDataVenda(), formatter);

        if (dataCadastro == null || dataVenda == null) {
            return false;
        }
        long horasDesdeCadastro = ChronoUnit.HOURS.between(dataCadastro, dataVenda); // Uso da API java.time, serve para calcular a diferença exata em horas entre dois pontos

        if (horasDesdeCadastro < 24) {
            return  venda.getPreco() > 1000.0;
        }
        return false;
    }

    @Override
    public String getDescricao() {
        return "Preço fora da faixa do mercado";
    }
}
