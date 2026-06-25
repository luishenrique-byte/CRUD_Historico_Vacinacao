package services.registroVacinacao;

import java.sql.Date;

public interface IRegistroService {
    public void novoRegistro(String lote, Date dataFabricacao, Date validade,
                             Long id_pac, Long id_unid, Long id_pro, Long id_vac); //Chama uma Procedure
    public void listarTodosRegistros();
    public void mostrarRegistroPorId(long id);
    public void modificarLote(long id, String lote);
    public void mostrarQtdeDoses(long id_pac); //Chama uma function
    public void mostrarQtdeDosesXVacina(long id_pac); //Chama uma function
    public void mostrarProximaDose(long id_pac);
    public void listarTodosRegistrosAtivos();
    public void listarAtendimentosPorProfissional();
}
