import Repositories.estado.EstadoRepository;
import conectaDB.ConectaPostgres;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {

    public static void main(String[] args) {

        String driver = "org.postgresql.Driver";
        String user   = "postgres";
        String senha = "12345";
        String url = "jdbc:postgresql://localhost:5432/dbHistorico_Vacina";

        try {

            Connection con = null;
            Class.forName(driver);
            con = (Connection) DriverManager.getConnection(url, user, senha);
            System.out.println("Conexão realizada com sucesso.");

        }catch(Exception e)
        {
            System.err.print(e.getMessage());
        }

        ConectaPostgres banco = new ConectaPostgres();

        String password = "12345";

        banco.Conectar(url, user, password);
    }
}
