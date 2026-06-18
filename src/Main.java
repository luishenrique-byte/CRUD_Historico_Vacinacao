
import Repositories.estado.EstadoRepository;
import Repositories.fabricante.FabricanteRepository;
import Repositories.municipio.MunicipioRepository;
import Repositories.paciente.PacienteRepository;
import Repositories.profissional.ProfissionalRepository;
import Repositories.registroVacinacao.RegistroRepository;
import Repositories.unidadeAtendimento.UnidadeRepository;
import Repositories.vacina.VacinaRepository;
import Services.estado.EstadoService;
import Services.fabricante.FabricanteService;
import Services.municipio.MunicipioService;
import Services.paciente.PacienteService;
import Services.profissional.ProfissionalService;
import Services.registroVacinacao.RegistroService;
import Services.unidadeAtendimento.UnidadeService;
import Services.vacina.VacinaService;
import UI.MenuUI;
import conectaDB.ConectaPostgres;

import java.sql.*;

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
