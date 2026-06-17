package Repositories.profissional;

import Models.ENUM.TipoProfissional;
import Models.Profissional;

import java.util.List;

public interface IProfissionalRepository {
    public Integer createProfissional(String nome,
                                      String documento,
                                      String cargo,
                                      TipoProfissional tipoProf,
                                      Long id_unid);

    public List<Profissional> getAllProfissional();
    public Profissional getProfissionalById(long id);
    public Integer updateProfissional(Profissional prof);
}