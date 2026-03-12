package cypher.security.model;

public class Vendedor {
    private Long id;
    private String nome;
    private Integer idade;
    private String email;
    private String cpf;
    private Integer quantidadeVendas;
    private Departamento departamento;

    public Vendedor() {}

    public Vendedor(Long id, String nome, Integer idade, String cpf, Integer quantidadeVendas, Departamento departamento) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.quantidadeVendas = quantidadeVendas;
        this.departamento = departamento;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public Integer getIdade() {return idade;}
    public void setIdade(Integer idade) {this.idade = idade;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getCpf() {return cpf;}
    public void setCpf(String cpf) {this.cpf = cpf;}

    public Integer getQuantidadeVendas() {return quantidadeVendas;}
    public void setQuantidadeVendas(Integer quantidadeVendas) {this.quantidadeVendas = quantidadeVendas;}

    public Departamento getDepartamento() {return departamento;}
    public void setDepartamento(Departamento departamento) {this.departamento = departamento;}

    @Override
    public String toString() {
        return "Vendedor{id=" + id + ", nome='" + nome + "', departamento=" + departamento + "}";
    }

}
