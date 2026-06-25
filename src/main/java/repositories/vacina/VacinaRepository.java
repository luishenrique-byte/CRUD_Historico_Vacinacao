package repositories.vacina;

import models.Vacina;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VacinaRepository implements IVacinaRepository{

    private final Connection _context;

    public VacinaRepository(Connection _context) {
        this._context = _context;
    }


    @Override
    public Integer createVacina(String nome, long id_fabricante, Integer intervalo_doses) {
        String query = "INSERT INTO vacina(nome, id_fabricante, intervalo_doses) values (?, ?, ?);";

        try{

            PreparedStatement ps = _context.prepareStatement(query);

            ps.setString(1,nome);
            ps.setLong(2,id_fabricante);
            ps.setInt(3,intervalo_doses);

            Integer linhaCriada = ps.executeUpdate();
            return linhaCriada;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Vacina> getAllVacina() {
        String query = "SELECT id_vacina, vacina.nome AS vacina, intervalo_doses,fabricante.id_fabricante, fabricante.nome AS fabricante\n" +
                "\tFROM vacina\n" +
                "\tINNER JOIN fabricante\n" +
                "\tON fabricante.id_fabricante = vacina.id_fabricante;";

        List<Vacina> vacinaList = new ArrayList<>();

        try{

            Statement stmt = _context.createStatement();

            ResultSet result = stmt.executeQuery(query);

            while (result.next()){

                long id_vacina = result.getLong("id_vacina");
                String nome = result.getString("vacina");
                Integer intervalo_doses = result.getInt("intervalo_doses");
                long id_fabricante = result.getLong("id_fabricante");
                String nome_fabricante = result.getString("fabricante");

                vacinaList.add(new Vacina(id_vacina,nome,intervalo_doses,id_fabricante,nome_fabricante));
            }

            return vacinaList;

        } catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Vacina getVacinaById(long id) {
        String query = "SELECT id_vacina, vacina.nome AS vacina, intervalo_doses,fabricante.id_fabricante, fabricante.nome AS fabricante\n" +
                "\tFROM vacina\n" +
                "\tINNER JOIN fabricante\n" +
                "\tON fabricante.id_fabricante = vacina.id_fabricante\n" +
                "\tWHERE id_vacina = " + id;

        try {

            Statement stmt = _context.createStatement();

            ResultSet result = stmt.executeQuery(query);

            if (result.next()){
                return new Vacina(result.getLong("id_vacina"),
                                result.getString("vacina"),
                                result.getInt("intervalo_doses"),
                                result.getLong("id_fabricante"),
                                result.getString("fabricante"));
            }

            return null;

        } catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Integer updateVacina(Vacina vacina) {
        String query = "UPDATE vacina SET nome = ?, id_fabricante = ?,intervalo_doses = ? WHERE id_vacina = ?";

        try{

            PreparedStatement ps = _context.prepareStatement(query);
            ps.setString(1,vacina.nome);
            ps.setLong(2,vacina.id_fabricante);
            ps.setInt(3,vacina.intervalo_doses);
            ps.setLong(4,vacina.id);

            Integer linhas = ps.executeUpdate();

            return linhas;

        }catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
