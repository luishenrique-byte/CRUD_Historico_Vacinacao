import Repositories.estado.EstadoRepository;
import Repositories.fabricante.FabricanteRepository;
import Repositories.fabricante.IFabricanteRepository;
import Repositories.municipio.MunicipioRepository;
import Services.estado.EstadoService;
import Services.fabricante.FabricanteService;
import Services.fabricante.IFabricanteService;
import Services.municipio.MunicipioService;
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


//        ===============================
//        EstadoRepository repository = new EstadoRepository(banco.getCon());
//        EstadoService service = new EstadoService(repository);
//        service.modificarNomeEstado(1, "Bahia");

//        ========================================
        MunicipioRepository repository = new MunicipioRepository(banco.getCon());
        MunicipioService service = new MunicipioService(repository);
//        service.listaTodosMunicipios();
        service.mostrarMunicipioPorId(100);
//        service.modicarNomeMunicipio(12,"melhor teste que tem");

//        ===========================
//        IFabricanteRepository repo = new FabricanteRepository(banco.getCon());
//        IFabricanteService service = new FabricanteService(repo);
//        service.adicionarFabricante("TESTE 14/06");
//        service.listarTodosFabricante();
//        service.mostrarFabricantePorId(5);
//        service.modificarNomeFabricante(4,"Melhor TESTE 14/06");

        //==================================

    }
}
