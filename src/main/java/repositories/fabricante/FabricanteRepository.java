package repositories.fabricante;

import models.Fabricante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FabricanteRepository implements IFabricanteRepository{

    private final Connection _context;

    public FabricanteRepository(Connection _context) {
        this._context = _context;
    }

    @Override
    public Integer createFabricante(String nome) {
        String query = "INSERT INTO fabricante(nome) VALUES (?)";

        try{

            PreparedStatement ps = _context.prepareStatement(query);
            ps.setString(1,nome);

            Integer linha = ps.executeUpdate();

            return linha;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Fabricante> getAllFabricante() {
        String query = "SELECT * FROM fabricante";

        List<Fabricante> fabricanteList = new ArrayList<>();
        try{

            Statement stmt = _context.createStatement();

            ResultSet result = stmt.executeQuery(query);

            while (result.next()) {

                long id = result.getLong("id_fabricante");
                String nome = result.getString("nome");

                fabricanteList.add(new Fabricante(id, nome));
            }

            return fabricanteList;

        } catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Fabricante getFabricanteById(long id) {
        String query = "SELECT * FROM fabricante WHERE id_fabricante ="+id;

        try{

            Statement stmt = _context.createStatement();

            ResultSet result = stmt.executeQuery(query);

            if (result.next()){
                String nome = result.getString("nome");
                return new Fabricante(id,nome);
            }

            return null;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Integer updateFabricante(Fabricante fabricante) {
        String query = "UPDATE fabricante SET nome = ? WHERE id_fabricante = ?";

        try{

            PreparedStatement ps = _context.prepareStatement(query);
            ps.setString(1, fabricante.nome);
            ps.setLong(2, fabricante.id);

            Integer linhas = ps.executeUpdate();

            return linhas;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}