package cypher.security.fraud;

import cypher.security.model.Venda;

import java.util.List;

public class ResultadoAnalise {
    private Venda venda;
    private String nivelRisco;
    private int scoreTotal;
    private List<String> motivos;

    public ResultadoAnalise() {}

    public ResultadoAnalise(Venda venda, String nivelRisco, int scoreTotal, List<String> motivos) {
        this.venda = venda;
        this.nivelRisco = nivelRisco;
        this.scoreTotal = scoreTotal;
        this.motivos = motivos;
    }

    public Venda getVenda() {return this.venda;}

    public String getNivelRisco() {return this.nivelRisco;}

    public int getScoreTotal() {return this.scoreTotal;}

    public List<String> getMotivos() {return this.motivos;}
}
