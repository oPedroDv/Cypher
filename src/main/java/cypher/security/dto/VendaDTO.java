package cypher.security.dto;

import cypher.security.fraud.ResultadoAnalise;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class VendaDTO {
    private Long produtoId;
    private Long vendedorId;
    private Double preco;
    private String dataVenda;

    private Long vendaId;
    private String nomeProduto;
    private String nivelRisco;
    private List<String> motivos;

    public VendaDTO() {}

    public VendaDTO(Long produtoId, Long vendedorId, Double preco, String dataVenda) {
        this.produtoId = produtoId;
        this.vendedorId = vendedorId;
        this.preco = preco;
        this.dataVenda = LocalDateTime.now().toString();
    }

    public static VendaDTO fromResultado(ResultadoAnalise resultado) {
        VendaDTO dto = new VendaDTO();

        dto.vendaId = resultado.getVenda().getId();
        dto.nomeProduto = resultado.getVenda().getProduto().getNome();
        dto.preco = resultado.getVenda().getPreco();
        dto.nivelRisco = resultado.getNivelRisco();
        dto.motivos =  resultado.getMotivos();

        return dto;
    }

    public Long getProdutoId() {return this.produtoId;}
    public void setProdutoId(Long produtoId) {this.produtoId = produtoId;}

    public Long getVendedorId() {return this.vendedorId;}
    public void setVendedorId(Long vendedorId) {this.vendedorId = vendedorId;}

    public Double getPreco() {return this.preco;}
    public void setPreco(Double preco) {this.preco = preco;}

    public String getDataVenda() {return this.dataVenda;}
    public void setDataVenda(String dataVenda) {this.dataVenda = dataVenda;}


    public Long getVendaId() {return vendaId;}
    public String getNomeProduto() {return nomeProduto;}
    public String getNivelRisco() {return nivelRisco;}
    public List<String> getMotivos() {return motivos;}
}
