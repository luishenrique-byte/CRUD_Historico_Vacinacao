package Repositories.estado;

import Models.Estado;
import conectaDB.ConectaPostgres;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstadoRepository implements IEstadoRepository{

    private final Connection _context;

    public EstadoRepository(Connection _context) {
        this._context = _context;
    }

    @Override
    public Integer createEstado(String nome) {
        String query = "INSERT INTO estado(nome) VALUES (?)";

        try{

            PreparedStatement ps = _context.prepareStatement(query);

            ps.setString(1,nome);

            Integer linhasCriada = ps.executeUpdate();
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

            Statement stmt = _context.createStatement();

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

            Statement stmt = _context.createStatement();

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
    public Integer updateEstado(Estado estado) {

        String query = "UPDATE estado SET nome = ? WHERE id_estado = ?";

        try{

            PreparedStatement ps = _context.prepareStatement(query);
            ps.setString(1, estado.nome);
            ps.setLong(2, estado.id);

            Integer linhas = ps.executeUpdate();

            return linhas;

        } catch (SQLException e) {
            e.getMessage();
            return null;
        }
    }
}