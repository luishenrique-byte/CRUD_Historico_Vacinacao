package Repositories.estado;

import Models.Estado;
import conectaDB.ConectaPostgres;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstadoRepository implements IEstadoRepository{

    private Connection _context;
    private Statement stmt;

    public EstadoRepository(Connection _context, Statement stmt) {
        this._context = _context;
        this.stmt = stmt;
    }

    @Override
    public Boolean createEstado(String nome) {
        String query = "INSERT INTO estado(nome) VALUES (?)";

        try{

            PreparedStatement ps = _context.prepareStatement(query);

            ps.setString(1,nome);

            Boolean linhasCriada = ps.execute();
            return linhasCriada;

        } catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Estado> getAllEstados() {
        String query = "SELECT * FROM estado";

        List<Estado> estadoList = new ArrayList<>();
        try {

            ResultSet result = stmt.executeQuery(query);
            while (result.next()){

                long id = result.getInt("id_estado");
                String nome = result.getString("nome");

                estadoList.add(new Estado(id,nome));
            }

            return estadoList;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Estado getEstadoById(long id) {
        String query = "SELECT id_estado, nome FROM estado WHERE id_estado = " + id;

        String nome;
        try{
            ResultSet result = stmt.executeQuery(query);

            if (result.next()) {
                nome = result.getString("nome");
                return new Estado(id, nome);
            }

            return null;

        }catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Integer updateEstado(long id, String nome) {

        String query = "UPDATE estado SET nome = ? WHERE id_estado = ?";

        try{

            PreparedStatement ps = _context.prepareStatement(query);
            ps.setString(1, nome);
            ps.setLong(2, id);

            Integer linhas = ps.executeUpdate();

            return linhas;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}