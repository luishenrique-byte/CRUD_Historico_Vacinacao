package Repositories.estado;

import Models.Estado;

import java.sql.Statement;
import java.util.List;

public interface IEstadoRepository {
//    public void createValorPadrao();
    public Boolean createEstado(String nome); // Retorna Boolean para saber se alguma linha foi criada
    public List<Estado> getAllEstados();
    public Estado getEstadoById(long id);
    public Integer updateEstado(long id, String nome);
}
