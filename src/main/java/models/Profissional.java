package models;

import models.ENUM.TipoProfissional;

public class Profissional {
    public Long id;
    public String nome;
    public String documento;
    public String cargo;
    public TipoProfissional tipoProf;
    public Long id_unid;
    public String nome_unid;

    public Profissional(Long id, String nome, String documento, String cargo, TipoProfissional tipoProf, Long id_unid, String nome_unid) {
        this.id = id;
        this.nome = nome;
        this.documento = documento;
        this.cargo = cargo;
        this.tipoProf = tipoProf;
        this.id_unid = id_unid;
        this.nome_unid = nome_unid;
    }
}
