package services.paciente;

import java.time.LocalDate;

public interface IPacienteService {
    public void cadastrarPaciente(String nome,
                                  LocalDate data_nascimento,
                                  String cpf,
                                  String rua,
                                  Integer numero,
                                  String cep,
                                  Long id_municipio);

    public void listarTodosPaciente();
    public void mostrarPacientePorId(long id);
    public void modificarNome(long id, String novoNome);
    public void modificarDataNascimento(long id, LocalDate novaData);
    public void modificarCpf(long id, String novoCpf);
    public void modificarEndereco(long id,
                                  String rua,
                                  Integer numero,
                                  String cep,
                                  Long id_municipio);
    public void inativarPaciente(long id);
    public void ativarPaciente(long id);
}
