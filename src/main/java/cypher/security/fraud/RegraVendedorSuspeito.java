package cypher.security.fraud;

import cypher.security.model.Venda;
import cypher.security.model.Vendedor;

public class RegraVendedorSuspeito implements RegraFraude {

    @Override
    public boolean validar(Venda venda) {
        Vendedor vendedor = venda.getVendedor();
        double valorVenda = venda.getPreco();
        if (vendedor.getQuantidadeVendas() <5 && valorVenda > 2000.0) {
            return true; // -> vendedor suspeito
        }
        return false;
    }

    @Override
    public String getDescricao() {
        return "Preço fora da faixa do mercado";
    }
}
