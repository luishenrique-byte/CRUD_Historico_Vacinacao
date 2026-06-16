package Models;

public class UnidadeAtendimento {

    public Long id;
    public String nome;
    public String rua;
    public Integer numero;
    public String cep;
    public Long id_municipio;
    public String nome_municipio;

    public UnidadeAtendimento(Long id, String nome, String rua, Integer numero, String cep, Long id_municipio, String nome_municipio) {
        this.id = id;
        this.nome = nome;
        this.rua = rua;
        this.numero = numero;
        this.cep = cep;
        this.id_municipio = id_municipio;
        this.nome_municipio = nome_municipio;
    }
}
