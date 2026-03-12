package cypher.security.model;

public class Produto {

    private Long id;
    private String nome;
    private String modelo;
    private String cor;
    private String fabricante;
    private Double precoMercado;
    private Categoria categoria;

    public Produto() {}

    public Produto(Long id, String nome, String modelo, String cor, String fabricante, Double precoMercado, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.modelo = modelo;
        this.cor = cor;
        this.fabricante = fabricante;
        this.precoMercado = precoMercado;
        this.categoria = categoria;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getModelo() {return modelo;}
    public void setModelo(String modelo) {this.modelo = modelo;}

    public String getCor() {return cor;}
    public void setCor(String cor) {this.cor = cor;}

    public String getFabricante() {return fabricante;}
    public void setFabricante(String fabricante) {this.fabricante = fabricante;}

    public Double getPrecoMercado() {return precoMercado;}
    public void setPrecoMercado(Double preco) {this.precoMercado = preco;}

    public Categoria getCategoria() {return categoria;}
    public void setCategoria(Categoria categoria) {this.categoria = categoria;}

    @Override
    public String toString() {
        return "Produto{'id=" + id + ", nome" + nome + ", modelo=" + modelo + "'}"; // Só para uma formatação mais bonita
    }
}

