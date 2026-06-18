package UI;
import Models.ENUM.TipoProfissional;
import Services.estado.IEstadoService;
import Services.fabricante.IFabricanteService;
import Services.municipio.IMunicipioService;
import Services.paciente.IPacienteService;
import Services.profissional.IProfissionalService;
import Services.registroVacinacao.IRegistroService;
import Services.unidadeAtendimento.IUnidadeService;
import Services.vacina.IVacinaService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

public class MenuUI {

    private final Scanner sc = new Scanner(System.in);

    private final IEstadoService estadoService;
    private final IMunicipioService municipioService;
    private final IFabricanteService fabricanteService;
    private final IVacinaService vacinaService;
    private final IPacienteService pacienteService;
    private final IProfissionalService profissionalService;
    private final IUnidadeService unidadeService;
    private final IRegistroService registroService;

    public MenuUI(IEstadoService estadoService,
                  IMunicipioService municipioService,
                  IFabricanteService fabricanteService,
                  IVacinaService vacinaService,
                  IPacienteService pacienteService,
                  IProfissionalService profissionalService,
                  IUnidadeService unidadeService,
                  IRegistroService registroService) {
        this.estadoService = estadoService;
        this.municipioService = municipioService;
        this.fabricanteService = fabricanteService;
        this.vacinaService = vacinaService;
        this.pacienteService = pacienteService;
        this.profissionalService = profissionalService;
        this.unidadeService = unidadeService;
        this.registroService = registroService;
    }

    // ===========================================================
    //  MENU PRINCIPAL
    // ===========================================================
    public void iniciar() {
        int opcao;
        do {
            System.out.println("\n=====================================================");
            System.out.println("   SISTEMA DE HISTÓRICO DE VACINAS - MENU PRINCIPAL");
            System.out.println("=====================================================");
            System.out.println("1 - Estado");
            System.out.println("2 - Município");
            System.out.println("3 - Fabricante");
            System.out.println("4 - Vacina");
            System.out.println("5 - Paciente");
            System.out.println("6 - Profissional");
            System.out.println("7 - Unidade de Atendimento");
            System.out.println("8 - Registro de Vacinação");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = lerInt();

            switch (opcao) {
                case 1 -> menuEstado();
                case 2 -> menuMunicipio();
                case 3 -> menuFabricante();
                case 4 -> menuVacina();
                case 5 -> menuPaciente();
                case 6 -> menuProfissional();
                case 7 -> menuUnidade();
                case 8 -> menuRegistro();
                case 0 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 0);
    }

    // ===========================================================
    //  MENU ESTADO
    // ===========================================================
    private void menuEstado() {
        int opcao;
        do {
            System.out.println("\n--- MENU ESTADO ---");
            System.out.println("1 - Cadastrar Estado");
            System.out.println("2 - Listar todos os Estados");
            System.out.println("3 - Buscar Estado por ID");
            System.out.println("4 - Modificar nome do Estado");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = lerInt();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome do Estado: ");
                    String nome = sc.nextLine();
                    estadoService.adicionarEstado(nome);
                }
                case 2 -> estadoService.listarTodosEstados();
                case 3 -> {
                    System.out.print("ID do Estado: ");
                    long id = lerLong();
                    estadoService.mostrarEstadoPorId(id);
                }
                case 4 -> {
                    System.out.print("ID do Estado: ");
                    long id = lerLong();
                    System.out.print("Novo nome: ");
                    String nome = sc.nextLine();
                    estadoService.modificarNomeEstado(id, nome);
                }
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    // ===========================================================
    //  MENU MUNICÍPIO
    // ===========================================================
    private void menuMunicipio() {
        int opcao;
        do {
            System.out.println("\n--- MENU MUNICÍPIO ---");
            System.out.println("1 - Cadastrar Município");
            System.out.println("2 - Listar todos os Municípios");
            System.out.println("3 - Buscar Município por ID");
            System.out.println("4 - Modificar nome do Município");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = lerInt();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome do Município: ");
                    String nome = sc.nextLine();
                    System.out.print("ID do Estado: ");
                    long idEstado = lerLong();
                    municipioService.adicionarMunicipio(nome, idEstado);
                }
                case 2 -> municipioService.listaTodosMunicipios();
                case 3 -> {
                    System.out.print("ID do Município: ");
                    long id = lerLong();
                    municipioService.mostrarMunicipioPorId(id);
                }
                case 4 -> {
                    System.out.print("ID do Município: ");
                    long id = lerLong();
                    System.out.print("Novo nome: ");
                    String nome = sc.nextLine();
                    municipioService.modicarNomeMunicipio(id, nome);
                }
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    // ===========================================================
    //  MENU FABRICANTE
    // ===========================================================
    private void menuFabricante() {
        int opcao;
        do {
            System.out.println("\n--- MENU FABRICANTE ---");
            System.out.println("1 - Cadastrar Fabricante");
            System.out.println("2 - Listar todos os Fabricantes");
            System.out.println("3 - Buscar Fabricante por ID");
            System.out.println("4 - Modificar nome do Fabricante");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = lerInt();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome do Fabricante: ");
                    String nome = sc.nextLine();
                    fabricanteService.adicionarFabricante(nome);
                }
                case 2 -> fabricanteService.listarTodosFabricante();
                case 3 -> {
                    System.out.print("ID do Fabricante: ");
                    long id = lerLong();
                    fabricanteService.mostrarFabricantePorId(id);
                }
                case 4 -> {
                    System.out.print("ID do Fabricante: ");
                    long id = lerLong();
                    System.out.print("Novo nome: ");
                    String nome = sc.nextLine();
                    fabricanteService.modificarNomeFabricante(id, nome);
                }
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    // ===========================================================
    //  MENU VACINA
    // ===========================================================
    private void menuVacina() {
        int opcao;
        do {
            System.out.println("\n--- MENU VACINA ---");
            System.out.println("1 - Cadastrar Vacina");
            System.out.println("2 - Listar todas as Vacinas");
            System.out.println("3 - Buscar Vacina por ID");
            System.out.println("4 - Modificar nome da Vacina");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = lerInt();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome da Vacina: ");
                    String nome = sc.nextLine();
                    System.out.print("ID do Fabricante: ");
                    long idFabricante = lerLong();
                    System.out.print("Intervalo entre doses em dias (ENTER para dose única): ");
                    String intervaloStr = sc.nextLine();
                    Integer intervalo = intervaloStr.isBlank() ? null : Integer.parseInt(intervaloStr);
                    vacinaService.adicionarVacina(nome, idFabricante, intervalo);
                }
                case 2 -> vacinaService.listaTodosVacinas();
                case 3 -> {
                    System.out.print("ID da Vacina: ");
                    long id = lerLong();
                    vacinaService.mostrarVacinaPorId(id);
                }
                case 4 -> {
                    System.out.print("ID da Vacina: ");
                    long id = lerLong();
                    System.out.print("Novo nome: ");
                    String nome = sc.nextLine();
                    vacinaService.modicarNomeVacina(id, nome);
                }
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    // ===========================================================
    //  MENU PACIENTE
    // ===========================================================
    private void menuPaciente() {
        int opcao;
        do {
            System.out.println("\n--- MENU PACIENTE ---");
            System.out.println("1 - Cadastrar Paciente");
            System.out.println("2 - Listar todos os Pacientes");
            System.out.println("3 - Buscar Paciente por ID");
            System.out.println("4 - Modificar nome do Paciente");
            System.out.println("5 - Modificar data de nascimento");
            System.out.println("6 - Modificar CPF");
            System.out.println("7 - Modificar endereço");
            System.out.println("8 - Inativar Paciente (Procedure)");
            System.out.println("9 - Ativar Paciente (Procedure)");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = lerInt();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Data de nascimento (AAAA-MM-DD): ");
                    LocalDate dataNasc = LocalDate.parse(sc.nextLine());
                    System.out.print("CPF: ");
                    String cpf = sc.nextLine();
                    System.out.print("Rua: ");
                    String rua = sc.nextLine();
                    System.out.print("Número: ");
                    Integer numero = lerInt();
                    System.out.print("CEP: ");
                    String cep = sc.nextLine();
                    System.out.print("ID do Município: ");
                    Long idMunicipio = lerLong();
                    pacienteService.cadastrarPaciente(nome, dataNasc, cpf, rua, numero, cep, idMunicipio);
                }
                case 2 -> pacienteService.listarTodosPaciente();
                case 3 -> {
                    System.out.print("ID do Paciente: ");
                    long id = lerLong();
                    pacienteService.mostrarPacientePorId(id);
                }
                case 4 -> {
                    System.out.print("ID do Paciente: ");
                    long id = lerLong();
                    System.out.print("Novo nome: ");
                    String nome = sc.nextLine();
                    pacienteService.modificarNome(id, nome);
                }
                case 5 -> {
                    System.out.print("ID do Paciente: ");
                    long id = lerLong();
                    System.out.print("Nova data de nascimento (AAAA-MM-DD): ");
                    LocalDate novaData = LocalDate.parse(sc.nextLine());
                    pacienteService.modificarDataNascimento(id, novaData);
                }
                case 6 -> {
                    System.out.print("ID do Paciente: ");
                    long id = lerLong();
                    System.out.print("Novo CPF: ");
                    String cpf = sc.nextLine();
                    pacienteService.modificarCpf(id, cpf);
                }
                case 7 -> {
                    System.out.print("ID do Paciente: ");
                    long id = lerLong();
                    System.out.print("Nova rua: ");
                    String rua = sc.nextLine();
                    System.out.print("Novo número: ");
                    Integer numero = lerInt();
                    System.out.print("Novo CEP: ");
                    String cep = sc.nextLine();
                    System.out.print("Novo ID do Município: ");
                    Long idMunicipio = lerLong();
                    pacienteService.modificarEndereco(id, rua, numero, cep, idMunicipio);
                }
                case 8 -> {
                    System.out.print("ID do Paciente: ");
                    long id = lerLong();
                    pacienteService.inativarPaciente(id);
                }
                case 9 -> {
                    System.out.print("ID do Paciente: ");
                    long id = lerLong();
                    pacienteService.ativarPaciente(id);
                }
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    // ===========================================================
    //  MENU PROFISSIONAL
    // ===========================================================
    private void menuProfissional() {
        int opcao;
        do {
            System.out.println("\n--- MENU PROFISSIONAL ---");
            System.out.println("1 - Cadastrar Profissional");
            System.out.println("2 - Listar todos os Profissionais");
            System.out.println("3 - Buscar Profissional por ID");
            System.out.println("4 - Modificar nome do Profissional");
            System.out.println("5 - Modificar cargo do Profissional");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = lerInt();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Documento (CPF se CLT, CNPJ se PJ): ");
                    String documento = sc.nextLine();
                    System.out.print("Cargo: ");
                    String cargo = sc.nextLine();
                    System.out.print("Tipo (1-CLT / 2-PJ): ");
                    int tipoOpcao = lerInt();
                    TipoProfissional tipo = (tipoOpcao == 1) ? TipoProfissional.CLT : TipoProfissional.PJ;
                    System.out.print("ID da Unidade de Atendimento: ");
                    Long idUnidade = lerLong();
                    profissionalService.cadastrarProfissional(nome, documento, cargo, tipo, idUnidade);
                }
                case 2 -> profissionalService.listarTodosProfissionais();
                case 3 -> {
                    System.out.print("ID do Profissional: ");
                    long id = lerLong();
                    profissionalService.mostrarProfissionalPorId(id);
                }
                case 4 -> {
                    System.out.print("ID do Profissional: ");
                    long id = lerLong();
                    System.out.print("Novo nome: ");
                    String nome = sc.nextLine();
                    profissionalService.modificarNomeProfissional(id, nome);
                }
                case 5 -> {
                    System.out.print("ID do Profissional: ");
                    long id = lerLong();
                    System.out.print("Novo cargo: ");
                    String cargo = sc.nextLine();
                    profissionalService.modificarCargoProfissional(id, cargo);
                }
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    // ===========================================================
    //  MENU UNIDADE DE ATENDIMENTO
    // ===========================================================
    private void menuUnidade() {
        int opcao;
        do {
            System.out.println("\n--- MENU UNIDADE DE ATENDIMENTO ---");
            System.out.println("1 - Cadastrar Unidade de Atendimento");
            System.out.println("2 - Listar todas as Unidades");
            System.out.println("3 - Buscar Unidade por ID");
            System.out.println("4 - Modificar nome da Unidade");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = lerInt();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome da Unidade: ");
                    String nome = sc.nextLine();
                    System.out.print("Rua: ");
                    String rua = sc.nextLine();
                    System.out.print("Número: ");
                    Integer numero = lerInt();
                    System.out.print("CEP: ");
                    String cep = sc.nextLine();
                    System.out.print("ID do Município: ");
                    Long idMunicipio = lerLong();
                    unidadeService.cadastrarUnidadeAtendimento(nome, rua, numero, cep, idMunicipio);
                }
                case 2 -> unidadeService.listarTodasUnidades();
                case 3 -> {
                    System.out.print("ID da Unidade: ");
                    long id = lerLong();
                    unidadeService.mostrarUnidadePorId(id);
                }
                case 4 -> {
                    System.out.print("ID da Unidade: ");
                    long id = lerLong();
                    System.out.print("Novo nome: ");
                    String nome = sc.nextLine();
                    unidadeService.modificaNomeUnidade(id, nome);
                }
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    // ===========================================================
    //  MENU REGISTRO DE VACINAÇÃO
    // ===========================================================
    private void menuRegistro() {
        int opcao;
        do {
            System.out.println("\n--- MENU REGISTRO DE VACINAÇÃO ---");
            System.out.println("1 - Registrar nova vacinação (via Procedure)");
            System.out.println("2 - Listar todos os Registros");
            System.out.println("3 - Buscar Registro por ID");
            System.out.println("4 - Modificar lote de um Registro");
            System.out.println("5 - Mostrar quantidade de doses de um Paciente (Function)");
            System.out.println("6 - Mostrar quantidade de doses por Vacina de um Paciente (Function)");
            System.out.println("7 - Mostrar próxima dose de um Paciente (Function)");
            System.out.println("8 - Listar todos os Registros ativos (View)");
            System.out.println("9 - Listar todos Atendimento por Profissional (View)");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = lerInt();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Lote: ");
                    String lote = sc.nextLine();
                    System.out.print("Data de fabricação (AAAA-MM-DD): ");
                    Date dataFabricacao = Date.valueOf(sc.nextLine());
                    System.out.print("Validade (AAAA-MM-DD): ");
                    Date validade = Date.valueOf(sc.nextLine());
                    System.out.print("ID do Paciente: ");
                    Long idPaciente = lerLong();
                    System.out.print("ID da Unidade de Atendimento: ");
                    Long idUnidade = lerLong();
                    System.out.print("ID do Profissional: ");
                    Long idProfissional = lerLong();
                    System.out.print("ID da Vacina: ");
                    Long idVacina = lerLong();
                    registroService.novoRegistro(lote, dataFabricacao, validade, idPaciente, idUnidade, idProfissional, idVacina);
                }
                case 2 -> registroService.listarTodosRegistros();
                case 3 -> {
                    System.out.print("ID do Registro: ");
                    long id = lerLong();
                    registroService.mostrarRegistroPorId(id);
                }
                case 4 -> {
                    System.out.print("ID do Registro: ");
                    long id = lerLong();
                    System.out.print("Novo lote: ");
                    String lote = sc.nextLine();
                    registroService.modificarLote(id, lote);
                }
                case 5 -> {
                    System.out.print("ID do Paciente: ");
                    long idPaciente = lerLong();
                    registroService.mostrarQtdeDoses(idPaciente);
                }
                case 6 -> {
                    System.out.print("ID do Paciente: ");
                    long idPaciente = lerLong();
                    registroService.mostrarQtdeDosesXVacina(idPaciente);
                }
                case 7 -> {
                    System.out.print("ID do Paciente: ");
                    long idPaciente = lerLong();
                    registroService.mostrarProximaDose(idPaciente);
                }
                case 8 -> registroService.listarTodosRegistrosAtivos();
                case 9 -> registroService.listarAtendimentosPorProfissional();
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    // ===========================================================
    //  MÉTODOS AUXILIARES DE LEITURA (evitam quebra por input inválido)
    // ===========================================================
    private int lerInt() {
        while (true) {
            try {
                String linha = sc.nextLine();
                return Integer.parseInt(linha.trim());
            } catch (NumberFormatException e) {
                System.out.print("Valor inválido, digite um número inteiro: ");
            }
        }
    }

    private long lerLong() {
        while (true) {
            try {
                String linha = sc.nextLine();
                return Long.parseLong(linha.trim());
            } catch (NumberFormatException e) {
                System.out.print("Valor inválido, digite um número inteiro: ");
            }
        }
    }
}
