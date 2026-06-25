package models;

import java.time.LocalDate;

public class Paciente {
    public Long id;
    public String nome;
    public LocalDate data_nascimento;
    public String cpf;
    public String rua;
    public Integer numero;
    public String cep;
    public Long id_municipio;
    public String nome_municipio; //Atributo fora da tabela, mas utilizando aqui para receber de query em inner join
    public Boolean ativo;

    public Paciente(Long id, String nome, LocalDate data_nascimento, String cpf,
                    String rua, Integer numero, String cep, Long id_municipio, String nome_municipio, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.data_nascimento = data_nascimento;
        this.cpf = cpf;
        this.rua = rua;
        this.numero = numero;
        this.cep = cep;
        this.id_municipio = id_municipio;
        this.nome_municipio = nome_municipio;
        this.ativo = ativo;
    }
}