package Services.paciente;

import Models.Paciente;
import Repositories.paciente.IPacienteRepository;
import utils.CepUtils;
import utils.CpfUtils;

import java.time.LocalDate;
import java.util.List;

public class PacienteService implements IPacienteService {

    private IPacienteRepository _repository;

    private static final String CPF_REGEX = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$|^\\d{11}$";

    public PacienteService(IPacienteRepository _repository) {
        this._repository = _repository;
    }


    @Override
    public void cadastrarPaciente(String nome, LocalDate data_nascimento, String cpf, String rua, Integer numero, String cep, Long id_municipio) {

        if (!CpfUtils.ehFormatoValido(cpf)){
            System.out.println("CPF inválido.");
            return;
        }

        if (!CepUtils.ehCepValido(cep)){
            System.out.println("CEP inválido.");
            return;
        }

        Integer linhaCriada = _repository.createPaciente(nome, data_nascimento, cpf, rua, numero, cep, id_municipio);

        if (linhaCriada != null && linhaCriada > 0) {
            System.out.println("Paciente cadastrado com sucesso.");
        } else {
            System.out.println("Não foi possível cadastrar Paciente.");
        }
    }

    @Override
    public void listarTodosPaciente() {
        List<Paciente> pacienteList = _repository.getAllPaciente();

        System.out.println("[ id | nome | data_nascimento | cpf | rua | numero | cep | id_municipio | nome_municipio ]");
        for (Paciente p : pacienteList) {
            System.out.println("[ "
                    + p.id + " | "
                    + p.nome + " | "
                    + p.data_nascimento + " | "
                    + p.cpf + " | "
                    + p.rua + " | "
                    + p.numero + " | "
                    + p.cep + " | "
                    + p.id_municipio + " | "
                    + p.nome_municipio + " ]");
        }
    }

    @Override
    public void mostrarPacientePorId(long id) {
        Paciente p = _repository.getPacienteById(id);

        if (p != null) {
            System.out.println("[ id | nome | data_nascimento | cpf | rua | numero | cep | id_municipio | nome_municipio ]");
            System.out.println("[ "
                    + p.id + " | "
                    + p.nome + " | "
                    + p.data_nascimento + " | "
                    + p.cpf + " | "
                    + p.rua + " | "
                    + p.numero + " | "
                    + p.cep + " | "
                    + p.id_municipio + " | "
                    + p.nome_municipio + " ]");
        } else {
            System.out.println("Não foi possível encontrar o Paciente por esse ID.");
        }
    }

    @Override
    public void modificarNome(long id, String novoNome) {
        Paciente pac = _repository.getPacienteById(id);

        if (pac == null) {
            System.out.println("Paciente não encontrao para esse ID.");
            return;
        }

        Integer qtdeLinhas = _repository.updatePaciente(new Paciente(pac.id,
                novoNome,
                pac.data_nascimento,
                pac.cpf,
                pac.rua,
                pac.numero,
                pac.cep,
                pac.id_municipio,
                pac.nome_municipio));

        if (qtdeLinhas != null && qtdeLinhas > 0) {
            System.out.println("Operação realizada com sucessor.");
            System.out.println(qtdeLinhas + " linha afetada.");
        } else {
            System.out.println("Nenhuma linha foi afetada. Reveja o ID.");
        }
    }

    @Override
    public void modificarDataNascimento(long id, LocalDate novaData) {
        Paciente pac = _repository.getPacienteById(id);

        if (pac == null) {
            System.out.println("Paciente não encontradO para esse ID.");
            return;
        }

        Integer qtdeLinhas = _repository.updatePaciente(new Paciente(pac.id,
                pac.nome,
                novaData,
                pac.cpf,
                pac.rua,
                pac.numero,
                pac.cep,
                pac.id_municipio,
                pac.nome_municipio));

        if (qtdeLinhas != null && qtdeLinhas > 0) {
            System.out.println("Operação realizada com sucessor.");
            System.out.println(qtdeLinhas + " linha afetada.");
        } else {
            System.out.println("Nenhuma linha foi afetada. Reveja o ID.");
        }
    }

    @Override
    public void modificarCpf(long id, String novoCpf) {

        if (!CpfUtils.ehFormatoValido(novoCpf)){
            System.out.println("CPF inválido.");
            return;
        }

        Paciente pac = _repository.getPacienteById(id);

        if (pac == null) {
            System.out.println("Paciente não encontrada para esse ID.");
            return;
        }

        Integer qtdeLinhas = _repository.updatePaciente(new Paciente(pac.id,
                pac.nome,
                pac.data_nascimento,
                novoCpf,
                pac.rua,
                pac.numero,
                pac.cep,
                pac.id_municipio,
                pac.nome_municipio));

        if (qtdeLinhas != null && qtdeLinhas > 0) {
            System.out.println("Operação realizada com sucessor.");
            System.out.println(qtdeLinhas + " linha afetada.");
        } else {
            System.out.println("Nenhuma linha foi afetada. Reveja o ID.");
        }
    }

    @Override
    public void modificarEndereco(long id, String rua, Integer numero, String cep, Long id_municipio) {
        Paciente pac = _repository.getPacienteById(id);

        if (pac == null) {
            System.out.println("Paciente não encontrada para esse ID.");
            return;
        }

        Integer qtdeLinhas = _repository.updatePaciente(new Paciente(pac.id,
                pac.nome,
                pac.data_nascimento,
                pac.cpf,
                rua,
                numero,
                cep,
                id_municipio));

        if (qtdeLinhas != null && qtdeLinhas > 0) {
            System.out.println("Operação realizada com sucessor.");
            System.out.println(qtdeLinhas + " linha afetada.");
        } else {
            System.out.println("Nenhuma linha foi afetada. Reveja o ID.");
        }
    }
}