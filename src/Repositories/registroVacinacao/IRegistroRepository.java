package Repositories.registroVacinacao;

import Models.RegistroVacinacao;


import java.sql.Date;
import java.util.List;

public interface IRegistroRepository {
    public Integer createRegistro(Date data_vacinacao, String lote, Date data_fabricacao,
                                  Date validade, Long id_pac, Long id_unid, Long id_pro, Long id_vac);
    public void callProcNovaVacinacao(String lote, Date data_fabricacao, Date validade,
                                      Long id_pac, Long id_unid, Long id_pro, Long id_vac); //Procedure
    public List<RegistroVacinacao> getAllRegistro();
    public RegistroVacinacao getRegistroById(long id);
    public Integer updateRegistro(RegistroVacinacao rv);
}
