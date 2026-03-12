package cypher.security.model;

public class Venda {

    private Long id;
    private Double preco;
    private String dataVenda;
    private String nivelRisco;

    private Produto produto;
    private Vendedor vendedor;

    public Venda() {}

    public Venda(Long id, Double preco, String dataVenda,
                 String nivelRisco, Produto produto, Vendedor vendedor) {
        this.id = id;
        this.preco = preco;
        this.dataVenda = dataVenda;
        this.nivelRisco = nivelRisco;
        this.produto = produto;
        this.vendedor = vendedor;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }

    public String getDataVenda() { return dataVenda; }
    public void setDataVenda(String dataVenda) { this.dataVenda = dataVenda; }

    public String getNivelRisco() { return nivelRisco; }
    public void setNivelRisco(String nivelRisco) { this.nivelRisco = nivelRisco; }

    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }

    public Vendedor getVendedor() { return vendedor; }
    public void setVendedor(Vendedor vendedor) { this.vendedor = vendedor; }

    @Override
    public String toString() {
        return "Venda{id=" + id + ", preco=" + preco +
                ", nivelRisco='" + nivelRisco + "'" +
                ", produto=" + produto +
                ", vendedor=" + vendedor + "}";
    }
}