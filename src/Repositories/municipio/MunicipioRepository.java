package Repositories.municipio;

import Models.Estado;
import Models.Municipio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MunicipioRepository implements IMunicipioRepository{

    private Connection _context;


    public MunicipioRepository(Connection _context) {
        this._context = _context;
    }

    @Override
    public Integer createMunicipio(String nome, long id_estado) {
        String query = "INSERT INTO municipio(nome,id_estado) VALUES (?,?)";

        try{

            PreparedStatement ps = _context.prepareStatement(query);
            ps.setString(1,nome);
            ps.setLong(2,id_estado);

            Integer linhaCriada = ps.executeUpdate();
            return linhaCriada;

        } catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Municipio> getAllMunicipio() {
        String query = "SELECT id_municipio, municipio.nome, municipio.id_estado, estado.nome as estado \n" +
                "\tFROM municipio\n" +
                "\tINNER JOIN estado\n" +
                "\tON estado.id_estado = municipio.id_estado;";

        List<Municipio> municipioList = new ArrayList<>();
        try {

            Statement stmt = _context.createStatement();

            ResultSet result = stmt.executeQuery(query);
            while (result.next()){

                long id = result.getLong("id_municipio");
                String nome = result.getString("nome");
                long id_estado = result.getLong("id_estado");
                String nome_estado = result.getString("estado");

                municipioList.add(new Municipio(id,nome,id_estado,nome_estado));
            }

            return municipioList;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Municipio getMunicipioById(long id) {

        String query = "SELECT id_municipio, municipio.nome, municipio.id_estado, estado.nome as estado \n" +
                "\tFROM municipio\n" +
                "\tINNER JOIN estado\n" +
                "\tON estado.id_estado = municipio.id_estado\n" +
                "\tWHERE id_municipio = " + id;
//
//        String nome;
//        String nome_estado;

        try{

            Statement stmt = _context.createStatement();

            ResultSet result = stmt.executeQuery(query);

            if (result.next()) {

                String nome = result.getString("nome");
                long id_estado = result.getLong("id_estado");
                String nome_estado = result.getString("estado");

                return new Municipio(id, nome, id_estado,nome_estado);

            }

            return null;

        }catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Integer updateMunicipio(Municipio municipio) {
        String query = "UPDATE municipio SET nome = ?, id_estado = ? WHERE id_municipio = ?";

        try{

            PreparedStatement ps = _context.prepareStatement(query);
            ps.setString(1,municipio.nome);
            ps.setLong(2, municipio.id_estado);
            ps.setLong(3, municipio.id_municipio);

            Integer linhas = ps.executeUpdate();

            return linhas;

        } catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
