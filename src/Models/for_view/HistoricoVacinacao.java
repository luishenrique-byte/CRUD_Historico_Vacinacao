package Models.for_view;

import java.sql.Date;

public class HistoricoVacinacao {
    public String nome_vacina;
    public Date data_vacinacao;
    public Date validade;
    public String lote;
    public String nome_paciente;
    public String nome_colaborador;
    public String unidade;

    public HistoricoVacinacao(String nome_vacina, Date data_vacinacao, Date validade, String lote,
                              String nome_paciente, String nome_colaborador, String unidade) {
        this.nome_vacina = nome_vacina;
        this.data_vacinacao = data_vacinacao;
        this.validade = validade;
        this.lote = lote;
        this.nome_paciente = nome_paciente;
        this.nome_colaborador = nome_colaborador;
        this.unidade = unidade;
    }
}
