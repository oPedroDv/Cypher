package cypher.security;

import cypher.security.config.DatabaseConfig;
import cypher.security.model.*;
import cypher.security.repository.*;

import java.time.LocalDateTime;

public class Seed {
    public static void main(String[] args) throws Exception {
        DatabaseConfig.inicializar();
        DepartamentoRepository departamentoRepo = new DepartamentoRepository();
        CategoriaRepository categoriaRepo = new CategoriaRepository();
        ProdutoRepository produtoRepo = new ProdutoRepository();
        VendedorRepository vendedorRepo = new VendedorRepository();

        Departamento depart = new Departamento();
        depart.setNome("Informatica");
        depart.setAndar("2º andar");
        departamentoRepo.salvar(depart);

        Categoria categoria = new Categoria();
        categoria.setNome("Eletrônicos");
        categoria.setDescricao("Produtos eletrônicos em geral");
        categoriaRepo.salvar(categoria);

        Produto produto = new Produto();
        produto.setDataCadastro(LocalDateTime.now().minusHours(10));
        produto.setNome("Notebook Dell");
        produto.setModelo("Inspiron 15");
        produto.setCor("Preto");
        produto.setFabricante("Dell");
        produto.setPrecoMercado(3500.0);
        produto.setCategoria(categoria);
        produtoRepo.salvar(produto);

        Vendedor vendedor = new Vendedor();
        vendedor.setNome("João Silva");
        vendedor.setIdade(28);
        vendedor.setEmail("joao@cypher.com");
        vendedor.setCpf("123.456.789-00");
        vendedor.setQuantidadeVendas(12);
        vendedor.setDepartamento(depart);
        vendedorRepo.salvar(vendedor);

        System.out.println("Banco populado com sucesso!");
    }
}