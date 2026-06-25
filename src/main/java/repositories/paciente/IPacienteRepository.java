package repositories.paciente;

import models.Paciente;

import java.time.LocalDate;
import java.util.List;

public interface IPacienteRepository {
    public Integer createPaciente(String nome,
                                  LocalDate data_nascimento,
                                  String cpf,
                                  String rua,
                                  Integer numero,
                                  String cep,
                                  Long id_municipio); //Integer para saber se ocorreo a criação
    public List<Paciente> getAllPaciente();
    public Paciente getPacienteById(long id);
    public Integer updatePaciente(Paciente paciente);
    public void callProcInativar(long id);
    public void callProcAtivar(long id);
}