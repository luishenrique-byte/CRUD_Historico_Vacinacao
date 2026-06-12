package Repositories.estado;

import Models.Estado;
import conectaDB.ConectaPostgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EstadoRepository implements IEstadoRepository{

    public Connection _context;

    public EstadoRepository(Connection con){
        _context = con;
    }

    @Override
    public void inserirEstado(String nome) {
        String query = "INSERT INTO estado(nome) VALUES (?)";

        try{

            PreparedStatement ps = _context.prepareStatement(query);

            ps.setString(1,nome);

            ps.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void listarTodosEstados() {

    }

    @Override
    public void getEstadoPorId(long id) {

    }

    @Override
    public void updateEstado(Estado estado) {

    }

    @Override
    public void deleteEstado(long id) {

    }
}
