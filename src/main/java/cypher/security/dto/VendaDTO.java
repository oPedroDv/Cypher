package cypher.security.dto;

import java.time.LocalDate;

public class VendaDTO {
    private Long produtoId;
    private Long vendedorId;
    private Double preco;
    private String dataVenda;

    public VendaDTO() {}

    public VendaDTO(Long produtoId, Long vendedorId, Double preco) {
        this.produtoId = produtoId;
        this.vendedorId = vendedorId;
        this.preco = preco;
        this.dataVenda = LocalDate.now().toString();
    }

    public Long getProdutoId() {return this.produtoId;}
    public void setProdutoId(Long produtoId) {this.produtoId = produtoId;}

    public Long getVendedorId() {return this.vendedorId;}
    public void setVendedorId(Long vendedorId) {this.vendedorId = vendedorId;}

    public Double getPreco() {return this.preco;}
    public void setPreco(Double preco) {this.preco = preco;}

    public String getDataVenda() {return this.dataVenda;}
    public void setDataVenda(String dataVenda) {this.dataVenda = dataVenda;}

}
