package cypher.security.model;

public class Venda {
    private Long id;
    private String produto;
    private Double valor;
    private Integer quantidade;
    private String nomeVendedor;
    private double preco;
    private double precoMercado;
    private String categoria;
    private String dataVenda;
    private int quantidadeVendasVendedor;
    private String nivelRisco;

    public Venda() {}

    public Venda(Long id, String produto, Double valor, Integer quantidade, String nomeVendedor, Double preco, Double precoMercado, String categoria, String dataVenda, Integer quantidadeVendasVendedor, String nivelRisco ) {
        this.id = id;
        this.produto = produto;
        this.valor = valor;
        this.quantidade = quantidade;
        this.nomeVendedor = nomeVendedor;
        this.preco = preco;
        this.precoMercado = precoMercado;
        this.categoria = categoria;
        this.dataVenda = dataVenda;
        this.quantidadeVendasVendedor = quantidadeVendasVendedor;
        this.nivelRisco = nivelRisco;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getProduto() {return produto;}
    public void setProduto(String produto) {this.produto = produto;}

    public Double getValor() {return valor;}
    public void setValor(Double valor) {this.valor = valor;}

    public Integer getQuantidade() {return quantidade;}
    public void setQuantidade(Integer quantidade) {this.quantidade = quantidade;}

    public String getNomeVendedor() {return nomeVendedor;}
    public void setNomeVendedor(String nomeVendedor) {this.nomeVendedor = nomeVendedor;}

    public Double getPreco() {return preco;}
    public void setPreco(Double preco) {this.preco = preco;}

    public Double getPrecoMercado() {return precoMercado;}
    public void setPrecoMercado(Double precoMercado) {this.precoMercado = precoMercado;}

    public String getCategoria() {return categoria;}
    public void setCategoria(String categoria) {this.categoria = categoria;}

    public String getDataVenda() {return dataVenda;}
    public void setDataVenda(String dataVenda) {this.dataVenda = dataVenda;}

    public Integer getQuantidadeVendasVendedor() {return quantidadeVendasVendedor;}
    public void setQuantidadeVendasVendedor(Integer quantidadeVendasVendedor) {this.quantidadeVendasVendedor = quantidadeVendasVendedor;}

    public String getNivelRisco() {return nivelRisco;}
    public void setNivelRisco(String nivelRisco) {this.nivelRisco = nivelRisco;}

}
