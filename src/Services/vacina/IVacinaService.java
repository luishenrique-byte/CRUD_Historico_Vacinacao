package Services.vacina;

public interface IVacinaService {
    public void adicionarVacina(String nome, long id_fabricante, int intervalo_doses);
    public void listaTodosVacinas();
    public void mostrarVacinaPorId(long id);
    public void modicarNomeVacina(long id, String nome);
//    public void modificarFabricante(long id, long id_estado);
}
