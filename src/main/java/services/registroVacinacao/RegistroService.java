package services.registroVacinacao;

import models.RegistroVacinacao;
import models.for_functions.ProximaDose;
import models.for_functions.QtdeDoses;
import models.for_functions.QtdeDosesXVacina;
import models.for_view.AtendimentoPorProfissional;
import models.for_view.HistoricoVacinacao;
import repositories.registroVacinacao.IRegistroRepository;

import java.sql.Date;
import java.util.List;

public class RegistroService implements IRegistroService{

    private final IRegistroRepository _repository;

    public RegistroService(IRegistroRepository _repository) {
        this._repository = _repository;
    }

    @Override
    public void novoRegistro(String lote, Date dataFabricacao, Date validade, Long id_pac, Long id_unid, Long id_pro, Long id_vac) {
        _repository.callProcNovaVacinacao(lote, dataFabricacao, validade, id_pac, id_unid, id_pro, id_vac);
    }

    @Override
    public void listarTodosRegistros() {
        List<RegistroVacinacao> registroList = _repository.getAllRegistro();

        System.out.println("[ id | data | lote | data_fabricação | validade | paciente | unidade | profissional | vacina ]");
        for (RegistroVacinacao rv : registroList) {
            System.out.println("[ "
                    + rv.id_registro + " | "
                    + rv.data_vacinacao + " | "
                    + rv.lote + " | "
                    + rv.data_fabricacao + " | "
                    + rv.validade + " | "
                    + rv.nome_pac + " | "
                    + rv.nome_unid + " | "
                    + rv.nome_pro + " | "
                    + rv.nome_vac + " ]");
        }
    }

    @Override
    public void mostrarRegistroPorId(long id) {
        RegistroVacinacao rv = _repository.getRegistroById(id);

        if (rv != null){
            System.out.println("[ id | data | lote | data_fabricação | validade | paciente | unidade | profissional | vacina ]");
            System.out.println("[ "
                    + rv.id_registro + " | "
                    + rv.data_vacinacao + " | "
                    + rv.lote + " | "
                    + rv.data_fabricacao + " | "
                    + rv.validade + " | "
                    + rv.nome_pac + " | "
                    + rv.nome_unid + " | "
                    + rv.nome_pro + " | "
                    + rv.nome_vac + " ]");
        }else {
            System.out.println("Não foi possível encontrar o Registro de Vacinação por esse ID.");
        }
    }

    @Override
    public void modificarLote(long id, String lote) {
        RegistroVacinacao rv = _repository.getRegistroById(id);

        if (rv == null){
            System.out.println("Não foi possível encontrar o Registro de Vacinação por esse ID.");
        }

        Integer qtdeLinhas = _repository.updateRegistro(new RegistroVacinacao(rv.id_registro,
                rv.data_vacinacao,
                lote,
                rv.data_fabricacao,
                rv.validade,
                rv.id_pac,
                rv.nome_pac,
                rv.id_unid,
                rv.nome_unid,
                rv.id_pro,
                rv.nome_pro,
                rv.id_vac,
                rv.nome_vac));

        if (qtdeLinhas != null && qtdeLinhas > 0) {
            System.out.println("Operação realizada com sucessor.");
            System.out.println(qtdeLinhas + " linha afetada.");
        } else {
            System.out.println("Nenhuma linha foi afetada. Reveja o ID.");
        }
    }

    @Override
    public void mostrarQtdeDoses(long id_pac) {
        QtdeDoses qtdeDoses = _repository.callFuncQtdeDoses(id_pac);

        if (qtdeDoses != null){
            System.out.println("[ nome_paciente | qtde_doses]");
            System.out.println("[ "+qtdeDoses.nome_paciente + " | " + qtdeDoses.qtde_doses + " ]");
        } else {
            System.out.println("Função não retornou nada.");
        }
    }

    @Override
    public void mostrarQtdeDosesXVacina(long id_pac) {
        List<QtdeDosesXVacina> dxv = _repository.callFuncQtdeDosesXVacina(id_pac);

        if (dxv == null){
            System.out.println("Função sem retorno. Verifique o ID.");
        }

            System.out.println("[ vacina | qtde_doses]");
        for (QtdeDosesXVacina q : dxv){
            System.out.println("[ "+q.vacina + " | " + q.qtde_doses + " ]");
        }
    }

    @Override
    public void mostrarProximaDose(long id_pac) {
        List<ProximaDose> proximaDoseList = _repository.callFuncProxDose(id_pac);

        if (proximaDoseList == null){
            System.out.println("Função sem retorno. Verifique o ID.");
        }

            System.out.println("[ nome_paciente | vacina | data_vacinacao_atual | data_prox_dose]");
        for (ProximaDose p : proximaDoseList){
            System.out.println("[ "+p.nome_paciente + " | "+p.vacina + " | " + p.data_vacinacao_atual + " | " +p.data_prox_dose +" ]");
        }
    }

    @Override
    public void listarTodosRegistrosAtivos() {
        List<HistoricoVacinacao> histVacList = _repository.callViewHistorico();

        System.out.println("[ vacina | data_vacinacao | validade | lote | paciente | profissional | unidade ]");
        for (HistoricoVacinacao hv : histVacList) {
            System.out.println("[ "
                    + hv.nome_vacina + " | "
                    + hv.data_vacinacao + " | "
                    + hv.validade + " | "
                    + hv.lote + " | "
                    + hv.nome_paciente + " | "
                    + hv.nome_colaborador + " | "
                    + hv.unidade + " ]");
        }
    }

    @Override
    public void listarAtendimentosPorProfissional() {
        List<AtendimentoPorProfissional> atendimentoList = _repository.callViewAtendXProf();

        System.out.println("[ id_profissional | profissional | id_paciente | paciente | unidade | data_atendimento");
        for (AtendimentoPorProfissional a : atendimentoList) {
            System.out.println("[ "
                    + a.id_profissional + " | "
                    + a.nome_colaborador + " | "
                    + a.id_paciente + " | "
                    + a.nome_paciente + " | "
                    + a.unidade + " | "
                    + a.data_atendimento + " ]");
        }
    }
}