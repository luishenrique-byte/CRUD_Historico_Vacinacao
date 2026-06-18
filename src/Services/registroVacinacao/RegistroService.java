package Services.registroVacinacao;

import Models.RegistroVacinacao;

import Repositories.registroVacinacao.IRegistroRepository;

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


}