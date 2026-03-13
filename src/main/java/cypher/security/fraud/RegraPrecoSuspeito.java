package cypher.security.fraud;


import cypher.security.model.Venda;

public class RegraPrecoSuspeito implements RegraFraude {
    @Override
    public boolean validar(Venda venda) {
        double limiteSeguro = venda.getProduto().getPrecoMercado();
        if (venda.getPreco() < limiteSeguro * 0.5 ||  venda.getPreco() > limiteSeguro * 2) {
            return true; // -> retorna que a venda é suspeita
        }
        return false;
    }

    @Override
    public String getDescricao() {
        return "Preço fora da faixa do mercado";
    }
}
