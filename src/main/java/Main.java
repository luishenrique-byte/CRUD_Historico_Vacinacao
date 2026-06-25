import repositories.estado.EstadoRepository;
import repositories.fabricante.FabricanteRepository;
import repositories.municipio.MunicipioRepository;
import repositories.paciente.PacienteRepository;
import repositories.profissional.ProfissionalRepository;
import repositories.registroVacinacao.RegistroRepository;
import repositories.unidadeAtendimento.UnidadeRepository;
import repositories.vacina.VacinaRepository;
import services.estado.EstadoService;
import services.fabricante.FabricanteService;
import services.municipio.MunicipioService;
import services.paciente.PacienteService;
import services.profissional.ProfissionalService;
import services.registroVacinacao.RegistroService;
import services.unidadeAtendimento.UnidadeService;
import services.vacina.VacinaService;
import ui.MenuUI;
import config.ConectaPostgres;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {

    public static void main(String[] args) {

        String driver = "org.postgresql.Driver";
        String user   = "postgres";
        String senha = "12345";
        String url = "jdbc:postgresql://localhost:5432/dbhistoricovacina";
//        String url = "jdbc:postgresql://localhost:5432/dbHistorico_Vacina";

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

        MenuUI menu = new MenuUI(
                new EstadoService(new EstadoRepository(banco.getCon())),
                new MunicipioService(new MunicipioRepository(banco.getCon())),
                new FabricanteService(new FabricanteRepository(banco.getCon())),
                new VacinaService(new VacinaRepository(banco.getCon())),
                new PacienteService(new PacienteRepository(banco.getCon())),
                new ProfissionalService(new ProfissionalRepository(banco.getCon())),
                new UnidadeService(new UnidadeRepository(banco.getCon())),
                new RegistroService(new RegistroRepository(banco.getCon()))
        );

        menu.iniciar();

        banco.Desconectar();
    }
}
