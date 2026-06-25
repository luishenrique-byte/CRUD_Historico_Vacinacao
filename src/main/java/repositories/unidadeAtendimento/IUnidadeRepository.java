package repositories.unidadeAtendimento;

import models.UnidadeAtendimento;

import java.util.List;

public interface IUnidadeRepository {
    public Integer createUnidadeAtendimento(String nome,
                                            String rua,
                                            Integer numero,
                                            String cep,
                                            Long id_municipio); //Integer para saber se ocorreo a criação
    public List<UnidadeAtendimento> getAllUnidadeAtendimento();
    public UnidadeAtendimento getUnidadeAtendimentoById(long id);
    public Integer updateUnidadeAtendimento(UnidadeAtendimento unidAtend);
}
