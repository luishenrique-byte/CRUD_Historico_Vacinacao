# 💉 Sistema de Histórico de Vacinação — CRUD com JDBC

Sistema de gerenciamento do histórico de vacinação de pacientes, desenvolvido em **Java puro com JDBC**, integrando um banco de dados relacional **PostgreSQL**. O projeto foi desenvolvido como trabalho final da disciplina de Banco de Dados, aplicando conceitos de modelagem, SQL e manipulação de dados via Java.

> 📌 Esta é a branch `master` — implementação original com JDBC puro e gerenciamento manual de dependências.
> Para a versão refatorada com estrutura Maven, acesse a branch [`refactor/maven-structure`](../../tree/refactor/maven-structure).

---

## 🛠️ Tecnologias Utilizadas

- Java 14+
- JDBC (Java Database Connectivity)
- PostgreSQL
- Driver PostgreSQL JDBC `42.7.11` (via `.jar` na pasta `lib/`)
- IntelliJ IDEA

---

## 📁 Estrutura do Projeto

```
src/
├── Main.java
├── conectaDB/
│   ├── ConectaPostgres.java       # Gerencia conexão com o banco
│   └── testeConexaoBD.java        # Teste de conexão
├── Models/
│   ├── ENUM/
│   │   └── TipoProfissional.java  # Enum CLT / PJ
│   ├── Estado.java
│   ├── Municipio.java
│   ├── Fabricante.java
│   ├── Vacina.java
│   ├── UnidadeAtendimento.java
│   ├── Profissional.java
│   ├── Paciente.java
│   ├── RegistroVacinacao.java
│   ├── for_functions/             # Models de retorno das Functions SQL
│   │   ├── QtdeDoses.java
│   │   ├── QtdeDosesXVacina.java
│   │   └── ProximaDose.java
│   └── for_view/                  # Models de retorno das Views SQL
│       ├── HistoricoVacinacao.java
│       └── AtendimentoPorProfissional.java
├── Repositories/                  # Acesso ao banco (JDBC)
│   ├── estado/
│   ├── municipio/
│   ├── fabricante/
│   ├── vacina/
│   ├── unidadeAtendimento/
│   ├── profissional/
│   ├── paciente/
│   └── registroVacinacao/
├── Services/                      # Regras de negócio e validações
│   ├── estado/
│   ├── municipio/
│   ├── fabricante/
│   ├── vacina/
│   ├── unidadeAtendimento/
│   ├── profissional/
│   ├── paciente/
│   └── registroVacinacao/
├── UI/
│   └── MenuUI.java                # Menu interativo no console
└── utils/
    ├── CpfUtils.java              # Validação de CPF
    ├── CnpjUtils.java             # Validação de CNPJ
    └── CepUtils.java              # Validação de CEP
lib/
└── postgresql-42.7.11.jar         # Driver JDBC (manual)
docs/
└── Documentação Sistema Histórico de Vacinas.docx
```

---

## 🗄️ Banco de Dados

O banco de dados é composto por **8 entidades**:

| Entidade | Descrição |
|---|---|
| `estado` | Unidades federativas |
| `municipio` | Municípios vinculados a um estado |
| `fabricante` | Fabricantes de vacinas (Pfizer, Butantan, etc.) |
| `vacina` | Imunizantes cadastrados com intervalo entre doses |
| `unidade_atendimento` | UBS e postos de saúde |
| `profissional` | Profissionais de saúde (CLT ou PJ) |
| `paciente` | Pacientes vacinados |
| `registro_vacinacao` | Cada evento de aplicação de vacina |

### Objetos SQL implementados

- **2 Views**: `vw_registro_vacinacao`, `vw_atendimentos_por_profissional`
- **4 Procedures**: `proc_nova_vacinacao`, `proc_inativar_pac`, `proc_ativar_pac`, `proc_descolar_prof`
- **4 Functions**: `func_qtde_doses`, `func_qtde_doses_x_vacina`, `func_prox_dose`, `func_idade_pac`

---

## ⚙️ Como executar

### Pré-requisitos

- Java 14+
- PostgreSQL instalado e rodando
- IntelliJ IDEA (ou outra IDE Java)

### Passos

1. Clone o repositório e acesse a branch `master`:
```bash
git clone https://github.com/luishenrique-byte/CRUD_Historico_Vacinacao.git
cd CRUD_Historico_Vacinacao
```

2. No PostgreSQL, crie o banco de dados:
```sql
CREATE DATABASE dbHistorico_Vacina;
```

3. Execute o script SQL completo disponível em `docs/` para criar as tabelas, inserir os dados e criar os objetos do banco.

4. Abra o projeto no IntelliJ, adicione o `lib/postgresql-42.7.11.jar` ao classpath do projeto (**File → Project Structure → Libraries → +**).

5. Configure a conexão em `conectaDB/ConectaPostgres.java`:
```java
String url = "jdbc:postgresql://localhost:5432/dbHistorico_Vacina";
String user = "postgres";
String password = "sua_senha";
```

6. Execute `Main.java`.

---

## ✅ Funcionalidades

- CRUD completo para todas as 8 entidades via menu interativo no console
- Validação de CPF (CLT) e CNPJ (PJ) no cadastro de profissionais
- Inativação de pacientes em vez de exclusão (preservação do histórico)
- Cálculo automático da próxima dose via SQL Function
- Consulta de histórico de vacinação via Views
- Chamada de Procedures e Functions diretamente via JDBC (`CallableStatement`)
- Menu interativo com submenus por entidade

---

## 📐 Arquitetura

O projeto segue o padrão **Repository/Service**:

- **Repository**: única responsabilidade de comunicação com o banco (JDBC)
- **Service**: regras de negócio, validações e formatação de saída
- **UI**: captura de input do usuário e exibição de resultados
- **Utils**: validações reutilizáveis (CPF, CNPJ, CEP)

---

## 📄 Documentação

A documentação completa do sistema está disponível na pasta `docs/`, cobrindo modelagem de dados, scripts DDL/DML, explicação da integração Java/JDBC e recursos avançados (Views, Procedures e Functions).
