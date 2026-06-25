package repositories.registroVacinacao;

import models.RegistroVacinacao;
import models.for_functions.ProximaDose;
import models.for_functions.QtdeDoses;
import models.for_functions.QtdeDosesXVacina;
import models.for_view.AtendimentoPorProfissional;
import models.for_view.HistoricoVacinacao;

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
    public QtdeDoses callFuncQtdeDoses(long id);
    public List<QtdeDosesXVacina> callFuncQtdeDosesXVacina(long id);
    public List<ProximaDose> callFuncProxDose(long id);
    public List<HistoricoVacinacao> callViewHistorico();
    public List<AtendimentoPorProfissional> callViewAtendXProf();
}
