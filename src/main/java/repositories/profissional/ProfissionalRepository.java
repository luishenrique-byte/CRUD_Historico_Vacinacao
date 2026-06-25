package repositories.profissional;

import models.ENUM.TipoProfissional;
import models.Profissional;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfissionalRepository implements IProfissionalRepository{

    private final Connection _context;

    public ProfissionalRepository(Connection _context) {
        this._context = _context;
    }

    @Override
    public Integer createProfissional(String nome, String documento, String cargo, TipoProfissional tipoProf, Long id_unid) {
        String query = "INSERT INTO profissional(nome, documento, cargo, tipo, id_unidade)\n" +
                "values (?, ?, ?, ?::tipo_profissional, ?);";

        try {

            PreparedStatement ps = _context.prepareStatement(query);

            ps.setString(1,nome);
            ps.setString(2,documento);
            ps.setString(3,cargo);
            ps.setString(4,tipoProf.name());
            ps.setLong(5, id_unid);

            Integer linhas = ps.executeUpdate();

            return linhas;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Profissional> getAllProfissional() {
        String query = "SELECT id_profissional, \n" +
                "prof.nome, \n" +
                "documento, \n" +
                "cargo, \n" +
                "tipo, \n" +
                "prof.id_unidade, \n" +
                "unid.nome as unidade\n" +
                "\tFROM profissional as prof\n" +
                "\tINNER JOIN unidade_atendimento as unid\n" +
                "\tON prof.id_unidade = unid.id_unidade";

        List<Profissional> profissionalList = new ArrayList<>();
        try {

            Statement stmt = _context.createStatement();

            ResultSet result = stmt.executeQuery(query);

            while (result.next()) {

                Long id = result.getLong("id_profissional");
                String nome = result.getString("nome");
                String documento = result.getString("documento");
                String cargo = result.getString("cargo");
                TipoProfissional tipoProf = TipoProfissional.valueOf(result.getString("tipo"));
                Long id_unidade = result.getLong("id_unidade");
                String nome_unidade = result.getString("unidade");

                profissionalList.add(new Profissional(id, nome, documento, cargo, tipoProf, id_unidade, nome_unidade));
            }

            return profissionalList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Profissional getProfissionalById(long id) {
        String query = "SELECT id_profissional, \n" +
                "prof.nome, \n" +
                "documento, \n" +
                "cargo, \n" +
                "tipo, \n" +
                "prof.id_unidade, \n" +
                "unid.nome as unidade\n" +
                "\tFROM profissional as prof\n" +
                "\tINNER JOIN unidade_atendimento as unid\n" +
                "\tON prof.id_unidade = unid.id_unidade\n" +
                "\tWHERE id_profissional = " + id;

        try {

            Statement stmt = _context.createStatement();

            ResultSet result = stmt.executeQuery(query);

            if (result.next()){

                Long id_profissional = result.getLong("id_profissional");
                String nome = result.getString("nome");
                String documento = result.getString("documento");
                String cargo = result.getString("cargo");
                TipoProfissional tipoProf = TipoProfissional.valueOf(result.getString("tipo"));
                Long id_unidade = result.getLong("id_unidade");
                String nome_unidade = result.getString("unidade");

                return new Profissional(id_profissional, nome, documento, cargo, tipoProf, id_unidade, nome_unidade);

            }

            return null;

        } catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Integer updateProfissional(Profissional prof) {
        String query = "UPDATE profissional\n" +
                "SET\n" +
                "nome = ?, \n" +
                "documento = ?, \n" +
                "cargo = ?, \n" +
                "tipo = ?::tipo_profissional, \n" +
                "id_unidade = ?\n" +
                "WHERE id_profissional = ?";

        try{

            PreparedStatement ps = _context.prepareStatement(query);

            ps.setString(1,prof.nome);
            ps.setString(2,prof.documento);
            ps.setString(3, prof.cargo);
            ps.setString(4,prof.tipoProf.name());
            ps.setLong(5, prof.id_unid);
            ps.setLong(6,prof.id);

            Integer linhas = ps.executeUpdate();
            return linhas;

        } catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}