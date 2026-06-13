package Services.municipio;

public interface IMunicipioService {
    public void adicionarMunicipio(String nome, long id_estado);
    public void listaTodosMunicipios();
    public void mostrarMunicipioPorId(long id);
    public void modicarNomeMunicipio(long id, String nome);
//    public void modificarEstado(long id, long id_estado);
}
