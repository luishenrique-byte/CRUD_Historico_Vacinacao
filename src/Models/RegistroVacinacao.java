package Models;

import java.sql.Date;

public class RegistroVacinacao {
    public Long id_registro;
    public Date data_vacinacao;
    public String lote;
    public Date data_fabricacao;
    public Date validade;
    public Long id_pac;
    public String nome_pac; // Para Leitura com Join;
    public Long id_unid;
    public String nome_unid; // Para Leitura com Join
    public Long id_pro;
    public String nome_pro; // Para Leitura com Join
    public Long id_vac;
    public String nome_vac; // Para Leitura com Join

    public RegistroVacinacao(Long id_registro, Date data_vacinacao, String lote,
                             Date data_fabricacao, Date validade, Long id_pac,
                             String nome_pac, Long id_unid, String nome_unid,
                             Long id_pro, String nome_pro, Long id_vac, String nome_vac) {
        this.id_registro = id_registro;
        this.data_vacinacao = data_vacinacao;
        this.lote = lote;
        this.data_fabricacao = data_fabricacao;
        this.validade = validade;
        this.id_pac = id_pac;
        this.nome_pac = nome_pac;
        this.id_unid = id_unid;
        this.nome_unid = nome_unid;
        this.id_pro = id_pro;
        this.nome_pro = nome_pro;
        this.id_vac = id_vac;
        this.nome_vac = nome_vac;
    }
}
