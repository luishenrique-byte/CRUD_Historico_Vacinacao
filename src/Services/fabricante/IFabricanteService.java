package Services.fabricante;

public interface IFabricanteService {
    public void adicionarFabricante(String nome);
    public void listarTodosFabricante();
    public void mostrarFabricantePorId(long id);
    public void modificarNomeFabricante(long id, String nome);
}
