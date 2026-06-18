package Models.for_functions;

import java.sql.Date;

public class ProximaDose {
    public String nome_paciente;
    public String vacina;
    public Date data_vacinacao_atual;
    public Date data_prox_dose;

    public ProximaDose(String nome_paciente, String vacina, Date data_vacinacao_atual, Date data_prox_dose) {
        this.nome_paciente = nome_paciente;
        this.vacina = vacina;
        this.data_vacinacao_atual = data_vacinacao_atual;
        this.data_prox_dose = data_prox_dose;
    }
}
