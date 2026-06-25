package services.estado;

public interface IEstadoService {
    public void adicionarEstado(String nome);
    public void listarTodosEstados();
    public void mostrarEstadoPorId(long id);
    public void modificarNomeEstado(long id, String nome);
}
