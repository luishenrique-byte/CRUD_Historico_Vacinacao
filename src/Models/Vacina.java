package Models;

public class Vacina {
    public Long id;
    public String nome;
    public Integer intervalo_doses;
    public Long id_fabricante;
    public String nome_fabricante;

    public Vacina(Long id, String nome, Integer intervalo_doses, Long id_fabricante, String nome_fabricante) {
        this.id = id;
        this.nome = nome;
        this.intervalo_doses = intervalo_doses;
        this.id_fabricante = id_fabricante;
        this.nome_fabricante = nome_fabricante;
    }
}
