package cypher.security.model;

public class Departamento {

    private Long id;
    private String nome;
    private String andar;

    public Departamento() {}

    public Departamento(Long id, String nome, String andar) {
        this.id = id;
        this.nome = nome;
        this.andar = andar;
    }

    public Long getId() {return this.id;}
    public void setId(Long id) {this.id = id;}

    public String getNome() {return this.nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getAndar() {return this.andar;}
    public void setAndar(String andar) {this.andar = andar;}

    @Override
    public String toString() {
        return "Departamento{id=" + id + ", nome='" + nome + "'}";
    }
}
