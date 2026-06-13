package Repositories.municipio;

import Models.Estado;
import Models.Municipio;

import java.util.List;

public interface IMunicipioRepository {
    public Integer createMunicipio(String nome, long id_estado); //Integer para saber se ocorreo a criação
    public List<Municipio> getAllMunicipio();
    public Municipio getMunicipioById(long id);
    public Integer updateMunicipio(Municipio municipio);
}
