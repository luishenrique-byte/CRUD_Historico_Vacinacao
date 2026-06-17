package Services.profissional;

import Models.ENUM.TipoProfissional;

public interface IProfissionalService {
    public void cadastrarProfissional(String nome,
                                      String documento,
                                      String cargo,
                                      TipoProfissional tipoProf,
                                      Long id_unid);

    public void listarTodosProfissionais();
    public void mostrarProfissionalPorId(long id);
    public void modificarNomeProfissional(long id, String novoNome);
    public void modificarCargoProfissional(long id, String novoCargo);
}
