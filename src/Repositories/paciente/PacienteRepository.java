package Repositories.paciente;

import Models.Paciente;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PacienteRepository implements IPacienteRepository{

    private final Connection _context;

    public PacienteRepository(Connection _context) {
        this._context = _context;
    }


    @Override
    public Integer createPaciente(String nome, LocalDate data_nascimento, String cpf, String rua, Integer numero, String cep, Long id_municipio) {
        String query = "insert into paciente(nome, \n" +
                "data_nascimento, \n" +
                "cpf, \n" +
                "rua, \n" +
                "numero, \n" +
                "cep, \n" +
                "id_municipio) \n" +
                "values (?, ?, ?, ?, ?, ?, ?)";

        try {

            PreparedStatement ps = _context.prepareStatement(query);

            ps.setString(1,nome); //Nome do Paciente
            ps.setDate(2,Date.valueOf(data_nascimento)); //Data Nascimento Paciente
            ps.setString(3,cpf);// CPF do Paciente
            ps.setString(4,rua);//Rua moradia paciente
            ps.setInt(5,numero);//numero moradia paciente
            ps.setString(6,cep);
            ps.setLong(7,id_municipio);

            Integer linhaCriada = ps.executeUpdate();
            return linhaCriada;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Paciente> getAllPaciente() {
        String query = "SELECT id_paciente, \n" +
                "paciente.nome, \n" +
                "data_nascimento, \n" +
                "cpf, \n" +
                "rua, \n" +
                "numero, \n" +
                "cep, \n" +
                "paciente.id_municipio,\n" +
                "municipio.nome AS municipio,\n" +
                "ativo\n" +
                "FROM paciente\n" +
                "INNER JOIN municipio\n" +
                "ON municipio.id_municipio = paciente.id_municipio\n" +
                "ORDER BY id_paciente ASC";

        List<Paciente> pacienteList = new ArrayList<>();

        try{

            Statement stmt = _context.createStatement();

            ResultSet result = stmt.executeQuery(query);

            while(result.next()){

                Long id_paciente = result.getLong("id_paciente");
                String nome = result.getString("nome"); //Nome do Paciente
                LocalDate data_nascimento = result.getDate("data_nascimento").toLocalDate(); //Data Nascimento Paciente
                String cpf = result.getString("cpf");// CPF do Paciente
                String rua = result.getString("rua");//Rua moradia paciente
                Integer numero = result.getInt("numero");//numero moradia paciente
                String cep = result.getString("cep");
                Long id_munipio = result.getLong("id_municipio");
                String nome_municipio = result.getString("municipio");
                Boolean ativo = result.getBoolean("ativo");

                pacienteList.add(new Paciente(id_paciente, nome,data_nascimento,cpf,rua,numero,cep,id_munipio,nome_municipio,ativo));

            }

            return pacienteList;

        } catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Paciente getPacienteById(long id) {
        String query = "SELECT id_paciente, \n" +
                "paciente.nome, \n" +
                "data_nascimento, \n" +
                "cpf, \n" +
                "rua, \n" +
                "numero, \n" +
                "cep, \n" +
                "paciente.id_municipio,\n" +
                "municipio.nome AS municipio,\n" +
                "ativo\n" +
                "FROM paciente\n" +
                "INNER JOIN municipio\n" +
                "ON municipio.id_municipio = paciente.id_municipio\n" +
                "WHERE id_paciente =" + id;

        try {

            Statement stmt = _context.createStatement();

            ResultSet result = stmt.executeQuery(query);

            if (result.next()){
                Long id_paciente = result.getLong("id_paciente");
                String nome = result.getString("nome"); //Nome do Paciente
                LocalDate data_nascimento = result.getDate("data_nascimento").toLocalDate(); //Data Nascimento Paciente
                String cpf = result.getString("cpf");// CPF do Paciente
                String rua = result.getString("rua");//Rua moradia paciente
                Integer numero = result.getInt("numero");//numero moradia paciente
                String cep = result.getString("cep");
                Long id_munipio = result.getLong("id_municipio");
                String nome_municipio = result.getString("municipio");
                Boolean ativo = result.getBoolean("ativo");

                return new Paciente(id_paciente, nome,data_nascimento,cpf,rua,numero,cep,id_munipio,nome_municipio,ativo);
            }

            return null;
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Integer updatePaciente(Paciente pac) {
        String query = "UPDATE paciente\n" +
                "SET\n" +
                "nome = ?,\n" +
                "data_nascimento = ?,\n" +
                "cpf = ?,\n" +
                "rua = ?,\n" +
                "numero = ?,\n" +
                "cep = ?,\n" +
                "id_municipio = ?,\n" +
                "ativo = ?\n" +
                "WHERE id_paciente = ?";

        try{

            PreparedStatement ps = _context.prepareStatement(query);

            ps.setString(1,pac.nome); //Nome do Paciente
            ps.setDate(2,Date.valueOf(pac.data_nascimento)); //Data Nascimento Paciente
            ps.setString(3,pac.cpf);// CPF do Paciente
            ps.setString(4,pac.rua);//Rua moradia paciente
            ps.setInt(5,pac.numero);//numero moradia paciente
            ps.setString(6,pac.cep);//cep moradia paciente
            ps.setLong(7,pac.id_municipio);//FK_municipio
            ps.setBoolean(8, pac.ativo);
            ps.setLong(9,pac.id);//ID do paciente

            Integer linhas = ps.executeUpdate();
            return linhas;

        } catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void callProcInativar(long id) {
        String query = "CALL proc_inativar_pac(?)";

        try {

            CallableStatement cs = _context.prepareCall(query);

            cs.setLong(1, id);

            cs.execute();

            System.out.println("Inativação registrada via procedure com sucesso.");

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void callProcAtivar(long id) {
        String query = "CALL proc_ativar_pac(?)";

        try {

            CallableStatement cs = _context.prepareCall(query);

            cs.setLong(1, id);

            cs.execute();

            System.out.println("Ativação registrada via procedure com sucesso.");

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
