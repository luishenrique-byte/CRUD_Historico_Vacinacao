package Models.for_view;

import java.sql.Date;

public class AtendimentoPorProfissional {
    public Long id_profissional;
    public String nome_colaborador;
    public Long id_paciente;
    public String nome_paciente;
    public String unidade;
    public Date data_atendimento;

    public AtendimentoPorProfissional(Long id_profissional, String nome_colaborador, Long id_paciente,
                                      String nome_paciente, String unidade, Date data_atendimento) {
        this.id_profissional = id_profissional;
        this.nome_colaborador = nome_colaborador;
        this.id_paciente = id_paciente;
        this.nome_paciente = nome_paciente;
        this.unidade = unidade;
        this.data_atendimento = data_atendimento;
    }
}
