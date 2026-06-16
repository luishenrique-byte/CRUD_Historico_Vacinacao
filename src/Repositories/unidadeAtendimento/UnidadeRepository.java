package Repositories.unidadeAtendimento;

import Models.UnidadeAtendimento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UnidadeRepository implements IUnidadeRepository{

    private final Connection _context;

    public UnidadeRepository(Connection _context) {
        this._context = _context;
    }

    @Override
    public Integer createUnidadeAtendimento(String nome, String rua, Integer numero, String cep, Long id_municipio) {
        String query = "insert into unidade_atendimento(nome, \n" +
                "rua, \n" +
                "numero, \n" +
                "cep, \n" +
                "id_municipio) \n" +
                "values (?, ?, ?, ?, ?)";

        try {

            PreparedStatement ps = _context.prepareStatement(query);

            ps.setString(1,nome); //Nome do Paciente
            ps.setString(2,rua);//Rua moradia
            ps.setInt(3,numero);//numero moradia
            ps.setString(4,cep);
            ps.setLong(5,id_municipio);

            Integer linhaCriada = ps.executeUpdate();
            return linhaCriada;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<UnidadeAtendimento> getAllUnidadeAtendimento() {
        String query = "SELECT id_unidade, \n" +
                "unidade_atendimento.nome, \n" +
                "rua, \n" +
                "numero, \n" +
                "cep, \n" +
                "unidade_atendimento.id_municipio,\n" +
                "municipio.nome AS municipio\n" +
                "FROM unidade_atendimento\n" +
                "INNER JOIN municipio\n" +
                "ON municipio.id_municipio = unidade_atendimento.id_municipio\n" +
                "ORDER BY id_unidade ASC";

        List<UnidadeAtendimento> unidadeAtendimentoList = new ArrayList<>();

        try{

            Statement stmt = _context.createStatement();

            ResultSet result = stmt.executeQuery(query);

            while(result.next()){

                Long id_unidade = result.getLong("id_unidade");
                String nome = result.getString("nome"); //Nome do unidade
                String rua = result.getString("rua");//Rua moradia unidade
                Integer numero = result.getInt("numero");//numero moradia unidade
                String cep = result.getString("cep");
                Long id_munipio = result.getLong("id_municipio");
                String nome_municipio = result.getString("municipio");

                unidadeAtendimentoList.add(new UnidadeAtendimento(id_unidade, nome,rua,numero,cep,id_munipio,nome_municipio));

            }

            return unidadeAtendimentoList;

        } catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public UnidadeAtendimento getUnidadeAtendimentoById(long id) {
        String query = "SELECT id_unidade, \n" +
                "unidade_atendimento.nome, \n" +
                "rua, \n" +
                "numero, \n" +
                "cep, \n" +
                "unidade_atendimento.id_municipio,\n" +
                "municipio.nome AS municipio\n" +
                "FROM unidade_atendimento\n" +
                "INNER JOIN municipio\n" +
                "ON municipio.id_municipio = unidade_atendimento.id_municipio\n" +
                "WHERE id_unidade =" + id;

        try{

            Statement stmt = _context.createStatement();

            ResultSet result = stmt.executeQuery(query);

            if(result.next()){

                Long id_unidade = result.getLong("id_unidade");
                String nome = result.getString("nome"); //Nome do unidade
                String rua = result.getString("rua");//Rua moradia unidade
                Integer numero = result.getInt("numero");//numero moradia unidade
                String cep = result.getString("cep");
                Long id_munipio = result.getLong("id_municipio");
                String nome_municipio = result.getString("municipio");

                return new UnidadeAtendimento(id_unidade, nome,rua,numero,cep,id_munipio,nome_municipio);

            }

            return null;

        } catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Integer updateUnidadeAtendimento(UnidadeAtendimento unidAtend) {
        String query = "UPDATE unidade_atendimento\n" +
                "SET\n" +
                "nome = ?,\n" +
                "rua = ?,\n" +
                "numero = ?,\n" +
                "cep = ?,\n" +
                "id_municipio = ?\n" +
                "WHERE id_unidade = ?";

        try{

            PreparedStatement ps = _context.prepareStatement(query);

            ps.setString(1,unidAtend.nome); //Nome do Paciente
            ps.setString(2,unidAtend.rua);//Rua moradia unidAtendiente
            ps.setInt(3,unidAtend.numero);//numero moradia unidAtendiente
            ps.setString(4,unidAtend.cep);//cep moradia unidAtendiente
            ps.setLong(5,unidAtend.id_municipio);//FK_municipio
            ps.setLong(6,unidAtend.id);//ID do unidade

            Integer linhas = ps.executeUpdate();
            return linhas;

        } catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
