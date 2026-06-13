package Models;

public class Municipio {
    Long id_municipio;
    String nome;
    Long id_estado;

    public Municipio(Long id_municipio, String nome, Long id_estado) {
        this.id_municipio = id_municipio;
        this.nome = nome;
        this.id_estado = id_estado;
    }
}
