package Services.unidadeAtendimento;

import Models.UnidadeAtendimento;
import Repositories.unidadeAtendimento.IUnidadeRepository;
import utils.CepUtils;

import java.util.List;

public class UnidadeService implements IUnidadeService{

    private final IUnidadeRepository _repository;

    public UnidadeService(IUnidadeRepository _repository) {
        this._repository = _repository;
    }

    @Override
    public void cadastrarUnidadeAtendimento(String nome, String rua, Integer numero, String cep, Long id_municipio) {

        if(!CepUtils.ehCepValido(cep)){
            System.out.println("CEP inválido");
            return;
        }

        Integer linhaCriada = _repository.createUnidadeAtendimento(nome,rua,numero,cep,id_municipio);

        if (linhaCriada != null && linhaCriada > 0) {
            System.out.println("Unidade de Atendimento cadastrada com sucesso.");
        } else {
            System.out.println("Não foi possível cadastrar Unidade de Atendimento.");
        }

    }

    @Override
    public void listarTodasUnidades() {
        List<UnidadeAtendimento> unidadeAtendimentoList = _repository.getAllUnidadeAtendimento();

        System.out.println("[ id | nome | rua | numero | cep | id_municipio | nome_municipio ]");
        for (UnidadeAtendimento u : unidadeAtendimentoList) {
            System.out.println("[ "
                    + u.id + " | "
                    + u.nome + " | "
                    + u.rua + " | "
                    + u.numero + " | "
                    + u.cep + " | "
                    + u.nome_municipio + " ]");
        }
    }

    @Override
    public void mostrarUnidadePorId(long id) {
        UnidadeAtendimento u = _repository.getUnidadeAtendimentoById(id);

        if (u != null) {
            System.out.println("[ id | nome | data_nascimento | cpf | rua | numero | cep | id_municipio | nome_municipio ]");
            System.out.println("[ "
                    + u.id + " | "
                    + u.nome + " | "
                    + u.rua + " | "
                    + u.numero + " | "
                    + u.cep + " | "
                    + u.id_municipio + " | "
                    + u.nome_municipio + " ]");
        } else {
            System.out.println("Não foi possível encontrar a Unidade por esse ID.");
        }
    }

    @Override
    public void modificaNomeUnidade(long id, String novoNome) {
        UnidadeAtendimento u = _repository.getUnidadeAtendimentoById(id);

        if (u == null) {
            System.out.println("Unidade de Atendimento não encontrada com esse ID.");
            return;
        }

        Integer qtdeLinhas = _repository.updateUnidadeAtendimento(new UnidadeAtendimento(u.id,
                novoNome,
                u.rua,
                u.numero,
                u.cep,
                u.id_municipio,
                u.nome_municipio));

        if (qtdeLinhas != null && qtdeLinhas > 0) {
            System.out.println("Operação realizada com sucessor.");
            System.out.println(qtdeLinhas + " linha afetada.");
        } else {
            System.out.println("Nenhuma linha foi afetada. Reveja o ID.");
        }
    }
}