package services.unidadeAtendimento;

public interface IUnidadeService {
    public void cadastrarUnidadeAtendimento(String nome, String rua, Integer numero, String cep, Long id_municipio);
    public void listarTodasUnidades();
    public void mostrarUnidadePorId(long id);
    public void modificaNomeUnidade(long id, String novoNome);
}
