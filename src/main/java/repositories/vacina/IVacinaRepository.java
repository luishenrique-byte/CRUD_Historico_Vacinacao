package repositories.vacina;

import models.Vacina;

import java.util.List;

public interface IVacinaRepository {
    public Integer createVacina(String nome, long id_fabricante, Integer intervalo_doses); //Integer para saber se ocorreu a criação
    public List<Vacina> getAllVacina();
    public Vacina getVacinaById(long id);
    public Integer updateVacina(Vacina vacina);
}
