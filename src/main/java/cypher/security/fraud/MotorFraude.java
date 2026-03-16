package cypher.security.fraud;

import cypher.security.model.Venda;

import java.util.ArrayList;
import java.util.List;

public class MotorFraude {
    private List<RegraFraude> regras;
    
    public MotorFraude() {
        this.regras = new ArrayList<>();
        
        regras.add(new RegraPrecoSuspeito());
        regras.add(new RegraVendedorSuspeito());
        regras.add(new RegraCadastramentoRapido());

    }

    public ResultadoAnalise avaliar(Venda venda) {
        List<String> motivos = new ArrayList<>();
        int score = 0;

        for (RegraFraude regra : regras) {
            if (regra.validar(venda)) {
                motivos.add(regra.getDescricao());
                score++;
            }
        }
        String nivelRisco = score == 0 ? "BAIXO" : score == 1 ? "MEDIO" : "ALTO";
        return new ResultadoAnalise(venda, nivelRisco, score, motivos);
    }
}
