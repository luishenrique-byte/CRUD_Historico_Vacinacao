package repositories.registroVacinacao;

import models.RegistroVacinacao;
import models.for_functions.ProximaDose;
import models.for_functions.QtdeDoses;
import models.for_functions.QtdeDosesXVacina;
import models.for_view.AtendimentoPorProfissional;
import models.for_view.HistoricoVacinacao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistroRepository implements IRegistroRepository{

    private final Connection _context;

    public RegistroRepository(Connection _context) {
        this._context = _context;
    }


    @Override
    public Integer createRegistro(Date data_vacinacao, String lote, Date data_fabricacao, Date validade, Long id_pac, Long id_unid, Long id_pro, Long id_vac) {
        String query = "INSERT INTO registro_vacinacao(data_vacinacao, lote, data_fabricacao, validade, id_paciente, id_unidade, id_profissional, id_vacina) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?)";


        try {

            PreparedStatement ps = _context.prepareStatement(query);

            ps.setDate(1, data_vacinacao);
            ps.setString(2, lote);
            ps.setDate(3, data_fabricacao);
            ps.setDate(4,validade);
            ps.setLong(5, id_pac);
            ps.setLong(6, id_unid);
            ps.setLong(7, id_pro);
            ps.setLong(8, id_vac);

            Integer linha = ps.executeUpdate();

            return linha;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void callProcNovaVacinacao(String lote, Date data_fabricacao, Date validade,
                                      Long id_pac, Long id_unid, Long id_pro, Long id_vac) {
        String query = "CALL proc_nova_vacinacao(?, ?, ?, ?, ?, ?, ?)";

        try {
            CallableStatement cs = _context.prepareCall(query);

            cs.setString(1, lote);
            cs.setDate(2, data_fabricacao);
            cs.setDate(3, validade);
            cs.setLong(4, id_pac);
            cs.setLong(5, id_unid);
            cs.setLong(6, id_pro);
            cs.setLong(7, id_vac);

            cs.execute(); // procedures usam execute(), não executeUpdate()

            System.out.println("Vacinação registrada via procedure com sucesso.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<RegistroVacinacao> getAllRegistro() {
        String query = "SELECT id_registro,\n" +
                "\tdata_vacinacao,\n" +
                "\tlote,\n" +
                "\tdata_fabricacao,\n" +
                "\tvalidade,\n" +
                "\tpac.id_paciente,\n" +
                "\tpac.nome AS paciente,\n" +
                "\tunid.id_unidade,\n" +
                "\tunid.nome AS unidade,\n" +
                "\tpro.id_profissional,\n" +
                "\tpro.nome AS profissional,\n" +
                "\tvac.id_vacina,\n" +
                "\tvac.nome AS vacina\n" +
                "FROM registro_vacinacao rv\n" +
                "\tINNER JOIN paciente AS pac\n" +
                "\tON pac.id_paciente = rv.id_paciente\n" +
                "\tINNER JOIN unidade_atendimento AS unid\n" +
                "\tON unid.id_unidade = rv.id_unidade\n" +
                "\tINNER JOIN profissional AS pro\n" +
                "\tON pro.id_profissional = rv.id_profissional\n" +
                "\tINNER JOIN vacina AS vac\n" +
                "\tON vac.id_vacina = rv.id_vacina";

        List<RegistroVacinacao> registroVacinacaoList = new ArrayList<>();

        try {

            Statement stmt = _context.createStatement();

            ResultSet result = stmt.executeQuery(query);

            while (result.next()){

                Long id_registro = result.getLong("id_registro");
                Date data_vacinacao = result.getDate("data_vacinacao");
                String lote = result.getString("lote");
                Date data_fabricacao = result.getDate("data_fabricacao");
                Date validade = result.getDate("validade");
                Long id_pac = result.getLong("id_paciente");
                String nome_pac = result.getString("paciente");
                Long id_unid = result.getLong("id_unidade");
                String nome_unid = result.getString("unidade");
                Long id_pro = result.getLong("id_profissional");
                String nome_pro = result.getString("profissional");
                Long id_vac = result.getLong("id_vacina");
                String nome_vac = result.getString("vacina");

                registroVacinacaoList.add(new RegistroVacinacao(id_registro, data_vacinacao, lote,
                        data_fabricacao, validade, id_pac, nome_pac, id_unid, nome_unid,
                        id_pro, nome_pro, id_vac, nome_vac));
            }

            return registroVacinacaoList;

        } catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public RegistroVacinacao getRegistroById(long id) {
        String query = "SELECT id_registro,\n" +
                "\tdata_vacinacao,\n" +
                "\tlote,\n" +
                "\tdata_fabricacao,\n" +
                "\tvalidade,\n" +
                "\tpac.id_paciente,\n" +
                "\tpac.nome AS paciente,\n" +
                "\tunid.id_unidade,\n" +
                "\tunid.nome AS unidade,\n" +
                "\tpro.id_profissional,\n" +
                "\tpro.nome AS profissional,\n" +
                "\tvac.id_vacina,\n" +
                "\tvac.nome AS vacina\n" +
                "FROM registro_vacinacao rv\n" +
                "\tINNER JOIN paciente AS pac\n" +
                "\tON pac.id_paciente = rv.id_paciente\n" +
                "\tINNER JOIN unidade_atendimento AS unid\n" +
                "\tON unid.id_unidade = rv.id_unidade\n" +
                "\tINNER JOIN profissional AS pro\n" +
                "\tON pro.id_profissional = rv.id_profissional\n" +
                "\tINNER JOIN vacina AS vac\n" +
                "\tON vac.id_vacina = rv.id_vacina\n" +
                "WHERE id_registro = ?";

        try {

            PreparedStatement ps = _context.prepareStatement(query); // <- prepareStatement em vez de createStatement
            ps.setLong(1, id); // <- preenche o "?" com o valor de id

            ResultSet result = ps.executeQuery();

            if (result.next()){

                Long id_registro = result.getLong("id_registro");
                Date data_vacinacao = result.getDate("data_vacinacao");
                String lote = result.getString("lote");
                Date data_fabricacao = result.getDate("data_fabricacao");
                Date validade = result.getDate("validade");
                Long id_pac = result.getLong("id_paciente");
                String nome_pac = result.getString("paciente");
                Long id_unid = result.getLong("id_unidade");
                String nome_unid = result.getString("unidade");
                Long id_pro = result.getLong("id_profissional");
                String nome_pro = result.getString("profissional");
                Long id_vac = result.getLong("id_vacina");
                String nome_vac = result.getString("vacina");

                return new RegistroVacinacao(id_registro, data_vacinacao, lote,
                        data_fabricacao, validade, id_pac, nome_pac, id_unid, nome_unid,
                        id_pro, nome_pro, id_vac, nome_vac);
            }

            return null;

        } catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Integer updateRegistro(RegistroVacinacao rv) {
        String query = "UPDATE registro_vacinacao\n" +
                "SET data_vacinacao = ?,\n" +
                "\tlote = ?,\n" +
                "\tdata_fabricacao = ?,\n" +
                "\tvalidade = ?,\n" +
                "\tid_paciente = ?,\n" +
                "\tid_unidade = ?,\n" +
                "\tid_profissional = ?,\n" +
                "\tid_vacina = ?\n" +
                "WHERE id_registro = ?";

        try {

            PreparedStatement ps = _context.prepareStatement(query);

            ps.setDate(1, rv.data_vacinacao);
            ps.setString(2, rv.lote);
            ps.setDate(3, rv.data_fabricacao);
            ps.setDate(4, rv.validade);
            ps.setLong(5, rv.id_pac);
            ps.setLong(6, rv.id_unid);
            ps.setLong(7, rv.id_pro);
            ps.setLong(8, rv.id_vac);
            ps.setLong(9, rv.id_registro);

            Integer linhas = ps.executeUpdate();
            return linhas;

        } catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public QtdeDoses callFuncQtdeDoses(long id) {
        String query = "SELECT * FROM func_qtde_doses(?)";

        try {

            PreparedStatement ps = _context.prepareStatement(query);

            ps.setLong(1,id);

            ResultSet result = ps.executeQuery();

            if (result.next()){
                String nome_paciente = result.getString("nome_paciente");
                long qtde_doses = result.getLong("qtde_doses");

                return new QtdeDoses(nome_paciente,qtde_doses);
            }

            return null;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<QtdeDosesXVacina> callFuncQtdeDosesXVacina(long id) {
        String query = "SELECT * FROM func_qtde_doses_x_vacina(?)";

        List<QtdeDosesXVacina> qtdeDosesList = new ArrayList<>();

        try {

            PreparedStatement ps = _context.prepareStatement(query);

            ps.setLong(1,id);

            ResultSet result = ps.executeQuery();

            while (result.next()){
                String vacina = result.getString("vacina");
                long qtde_doses = result.getLong("qtde_doses");

                qtdeDosesList.add(new QtdeDosesXVacina(vacina,qtde_doses));
            }

            return qtdeDosesList;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<ProximaDose> callFuncProxDose(long id) {
        String query = "SELECT * FROM func_prox_dose(?);";

        List<ProximaDose> proximaDoseList = new ArrayList<>();

        try {

            PreparedStatement ps = _context.prepareStatement(query);

            ps.setLong(1,id);

            ResultSet result = ps.executeQuery();

            while (result.next()){
                String nome_paciente = result.getString("nome_paciente");
                String vacina = result.getString("vacina");
                Date data_vacinacao_atual = result.getDate("data_vacinacao_atual");
                Date data_prox_dose = result.getDate("data_prox_dose");

                proximaDoseList.add(new ProximaDose(nome_paciente,vacina,data_vacinacao_atual,data_prox_dose));
            }

            return proximaDoseList;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<HistoricoVacinacao> callViewHistorico() {
        String query = "SELECT * FROM vw_registro_vacinacao";

        List<HistoricoVacinacao> historicoList = new ArrayList<>();

        try {

            Statement stmt = _context.createStatement();

            ResultSet result = stmt.executeQuery(query);

            while (result.next()){
                String nome_vacina = result.getString("nome_vacina");
                Date data_vacinacao = result.getDate("data_vacinacao");
                Date validade = result.getDate("validade");
                String lote = result.getString("lote");
                String nome_paciente = result.getString("nome_paciente");
                String nome_colaborador = result.getString("nome_colaborador");
                String unidade = result.getString("unidade");

                historicoList.add(new HistoricoVacinacao(nome_vacina, data_vacinacao, validade, lote, nome_paciente, nome_colaborador, unidade));
            }

            return historicoList;

        } catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<AtendimentoPorProfissional> callViewAtendXProf() {
        String query = "SELECT * FROM vw_atendimentos_por_profissional";

        List<AtendimentoPorProfissional> atendimentoList = new ArrayList<>();

        try {

            Statement stmt = _context.createStatement();

            ResultSet result = stmt.executeQuery(query);

            while (result.next()){

                Long id_profissional = result.getLong("id_profissional");
                String nome_colaborador = result.getString("nome_colaborador");
                Long id_paciente = result.getLong("id_paciente");
                String nome_paciente = result.getString("nome_paciente");
                String unidade = result.getString("unidade");
                Date data_atendimento = result.getDate("data_atendimento");

                atendimentoList.add(new AtendimentoPorProfissional(id_profissional, nome_colaborador, id_paciente, nome_paciente, unidade, data_atendimento));
            }

            return atendimentoList;

        } catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }

    }
}