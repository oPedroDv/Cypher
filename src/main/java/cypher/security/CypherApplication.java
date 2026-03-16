package cypher.security;

import cypher.security.controller.VendaController;
import cypher.security.model.Produto;
import cypher.security.model.Venda;
import cypher.security.model.Vendedor;
import cypher.security.repository.ProdutoRepository;
import cypher.security.repository.VendedorRepository;

public class CypherApplication {
    public static void main(String[] args) throws Exception {
        ProdutoRepository produtoRepository = new ProdutoRepository();
        VendedorRepository vendedorRepository = new VendedorRepository();
        VendaController controller = new VendaController();

        Produto produto = produtoRepository.buscarPorId(1L);
        Vendedor vendedor = vendedorRepository.buscarPorId(1L);

        Venda venda = new Venda();
        venda.setProduto(produto);
        venda.setVendedor(vendedor);
        venda.setPreco(3500.0);
        venda.setDataVenda("2026-03-13 10:00:00");

        controller.processarVenda(venda);
    }
}