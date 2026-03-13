package cypher.security.fraud;

import cypher.security.model.Venda;

public interface RegraFraude {
    boolean validar (Venda venda);
    String getDescricao();
}