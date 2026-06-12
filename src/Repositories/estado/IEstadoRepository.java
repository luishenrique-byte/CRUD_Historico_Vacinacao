package Repositories.estado;

import Models.Estado;

public interface IEstadoRepository {
    //public void inserirValorPadrao();
    public void inserirEstado(String nome);
    public void listarTodosEstados();
    public void getEstadoPorId(long id);
    public void updateEstado(Estado estado);
    public void deleteEstado(long id);
}
