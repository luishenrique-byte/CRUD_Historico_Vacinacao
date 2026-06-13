package Models;

public class Municipio {
    public Long id_municipio;
    public String nome;
    public Long id_estado;
    public String nome_estado;

    public Municipio(Long id_municipio, String nome, Long id_estado, String nome_estado) {
        this.id_municipio = id_municipio;
        this.nome = nome;
        this.id_estado = id_estado;
        this.nome_estado = nome_estado;
    }
}
