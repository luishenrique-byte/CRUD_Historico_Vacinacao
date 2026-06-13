package Repositories.estado;

import Models.Estado;

import java.sql.Statement;
import java.util.List;

public interface IEstadoRepository {
//    public void createValorPadrao();
    public Integer createEstado(String nome); // Retorna Integer para saber se alguma linha foi criada
    public List<Estado> getAllEstados();
    public Estado getEstadoById(long id);
    public Integer updateEstado(Estado estado);
}
