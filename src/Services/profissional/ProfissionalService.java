package Services.profissional;

import Models.ENUM.TipoProfissional;
import Models.Paciente;
import Models.Profissional;
import Repositories.profissional.IProfissionalRepository;
import utils.CnpjUtils;
import utils.CpfUtils;

import java.util.List;

public class ProfissionalService implements IProfissionalService{

    private final IProfissionalRepository _repository;

    public ProfissionalService(IProfissionalRepository _repository) {
        this._repository = _repository;
    }

    @Override
    public void cadastrarProfissional(String nome, String documento, String cargo,
                                      TipoProfissional tipoProf, Long id_unid) {

        if(tipoProf == TipoProfissional.CLT){
            if (!CpfUtils.ehFormatoValido(documento)){
                System.out.println("CPF inválido");
                return;
            }
        } else {
            if (!CnpjUtils.ehFormatoValido(documento)){
                System.out.println("CNPJ inválido");
                return;
            }
        }

        Integer linhaCriada = _repository.createProfissional(nome,
                documento,
                cargo,
                tipoProf,
                id_unid);

        if (linhaCriada != null && linhaCriada > 0) {
            System.out.println("Profissional cadastrado com sucesso.");
        } else {
            System.out.println("Não foi possível cadastrar Profissional.");
        }
    }

    @Override
    public void listarTodosProfissionais() {
        List<Profissional> profissionalList = _repository.getAllProfissional();

        System.out.println("[ id | nome | documento | cargo | tipo | unidade ]");
        for (Profissional p : profissionalList) {
            System.out.println("[ "
                    + p.id + " | "
                    + p.nome + " | "
                    + p.documento + " | "
                    + p.cargo + " | "
                    + p.tipoProf + " | "
                    + p.nome_unid + " ]");
        }
    }

    @Override
    public void mostrarProfissionalPorId(long id) {
        Profissional p = _repository.getProfissionalById(id);

        if (p != null){
            System.out.println("[ id | nome | documento | cargo | tipo | unidade ]");
            System.out.println("[ "
                    + p.id + " | "
                    + p.nome + " | "
                    + p.documento + " | "
                    + p.cargo + " | "
                    + p.tipoProf + " | "
                    + p.nome_unid + " ]");
        }else {
            System.out.println("Não foi possível encontrar o Profissional por esse ID.");
        }
    }

    @Override
    public void modificarNomeProfissional(long id, String novoNome) {
        Profissional p = _repository.getProfissionalById(id);

        if (p == null){
            System.out.println("Profissional não encontrado por esse ID");
            return;
        }

        Integer qtdeLinhas = _repository.updateProfissional(new Profissional(p.id,
                novoNome,
                p.documento,
                p.cargo,
                p.tipoProf,
                p.id_unid,
                p.nome_unid));

        if (qtdeLinhas != null && qtdeLinhas > 0) {
            System.out.println("Operação realizada com sucessor.");
            System.out.println(qtdeLinhas + " linha afetada.");
        } else {
            System.out.println("Nenhuma linha foi afetada. Reveja o ID.");
        }
    }

    @Override
    public void modificarCargoProfissional(long id, String novoCargo) {
        Profissional p = _repository.getProfissionalById(id);

        if (p == null){
            System.out.println("Profissional não encontrado por esse ID");
            return;
        }

        Integer qtdeLinhas = _repository.updateProfissional(new Profissional(p.id,
                p.nome,
                p.documento,
                novoCargo,
                p.tipoProf,
                p.id_unid,
                p.nome_unid));

        if (qtdeLinhas != null && qtdeLinhas > 0) {
            System.out.println("Operação realizada com sucessor.");
            System.out.println(qtdeLinhas + " linha afetada.");
        } else {
            System.out.println("Nenhuma linha foi afetada. Reveja o ID.");
        }
    }
}
