# 💉 Sistema de Histórico de Vacinação — Refatoração com Maven

Refatoração do projeto original para uma estrutura **Maven**, adotando convenções modernas de projeto Java e gerenciamento automatizado de dependências via `pom.xml`.

> 📌 Esta é a branch `refactor/maven-structure`.
> Para a implementação original com JDBC puro, acesse a branch [`master`](../../tree/master).

---

## 🔄 O que mudou em relação à branch master

| | `master` | `refactor/maven-structure` |
|---|---|---|
| Gerenciamento de dependências | `.jar` manual na pasta `lib/` | Maven via `pom.xml` |
| Estrutura de pastas | `src/` plano | `src/main/java/` (padrão Maven) |
| Nomenclatura de pacotes | `PascalCase` (`Models`, `Repositories`) | `camelCase` (`models`, `repositories`) |
| Ponto de entrada | `src/Main.java` | `src/main/java/Main.java` |
| Configuração de conexão | `conectaDB/` | `config/` |
| Build | Manual (IntelliJ) | Maven (`mvn package`) |

---

## 🛠️ Tecnologias Utilizadas

- Java 14+
- **Maven** (gerenciamento de dependências e build)
- JDBC (Java Database Connectivity)
- PostgreSQL
- Driver PostgreSQL JDBC `42.7.11` (via Maven)
- IntelliJ IDEA

---

## 📁 Estrutura do Projeto

```
historico-vacinacao/
├── pom.xml                                    # Gerenciamento de dependências Maven
├── docs/
│   └── Documentação Sistema Histórico de Vacinas.pdf
└── src/
    ├── main/
    │   └── java/
    │       ├── Main.java                      # Ponto de entrada da aplicação
    │       ├── config/
    │       │   └── ConectaPostgres.java       # Gerencia conexão com o banco
    │       ├── models/
    │       │   ├── ENUM/
    │       │   │   └── TipoProfissional.java  # Enum CLT / PJ
    │       │   ├── Estado.java
    │       │   ├── Municipio.java
    │       │   ├── Fabricante.java
    │       │   ├── Vacina.java
    │       │   ├── UnidadeAtendimento.java
    │       │   ├── Profissional.java
    │       │   ├── Paciente.java
    │       │   ├── RegistroVacinacao.java
    │       │   ├── for_functions/             # Models de retorno das Functions SQL
    │       │   │   ├── QtdeDoses.java
    │       │   │   ├── QtdeDosesXVacina.java
    │       │   │   └── ProximaDose.java
    │       │   └── for_view/                  # Models de retorno das Views SQL
    │       │       ├── HistoricoVacinacao.java
    │       │       └── AtendimentoPorProfissional.java
    │       ├── repositories/                  # Acesso ao banco (JDBC)
    │       │   ├── estado/
    │       │   ├── municipio/
    │       │   ├── fabricante/
    │       │   ├── vacina/
    │       │   ├── unidadeAtendimento/
    │       │   ├── profissional/
    │       │   ├── paciente/
    │       │   └── registroVacinacao/
    │       ├── services/                      # Regras de negócio e validações
    │       │   ├── estado/
    │       │   ├── municipio/
    │       │   ├── fabricante/
    │       │   ├── vacina/
    │       │   ├── unidadeAtendimento/
    │       │   ├── profissional/
    │       │   ├── paciente/
    │       │   └── registroVacinacao/
    │       ├── ui/
    │       │   └── MenuUI.java                # Menu interativo no console
    │       └── utils/
    │           ├── CpfUtils.java              # Validação de CPF
    │           ├── CnpjUtils.java             # Validação de CNPJ
    │           └── CepUtils.java              # Validação de CEP
    └── test/
        └── java/
            └── com/luishenrique/cap/
                └── AppTest.java
```

---

## 📦 Dependências (pom.xml)

```xml
<dependencies>
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <version>42.7.11</version>
    </dependency>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>3.8.1</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

Não é necessário baixar nenhum `.jar` manualmente — o Maven gerencia tudo automaticamente.

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
- Maven instalado (`mvn -version` para verificar)
- PostgreSQL instalado e rodando
- IntelliJ IDEA (ou outra IDE com suporte a Maven)

### Passos

1. Clone o repositório e acesse a branch:
```bash
git clone https://github.com/luishenrique-byte/CRUD_Historico_Vacinacao.git
cd CRUD_Historico_Vacinacao
git checkout refactor/maven-structure
```

2. Instale as dependências Maven:
```bash
mvn dependency:resolve
```

3. No PostgreSQL, crie o banco de dados:
```sql
CREATE DATABASE dbHistorico_Vacina;
```

4. Execute o script SQL completo disponível em `docs/` para criar as tabelas, inserir os dados e criar os objetos do banco.

5. Configure a conexão em `src/main/java/config/ConectaPostgres.java`:
```java
String url = "jdbc:postgresql://localhost:5432/dbHistorico_Vacina";
String user = "postgres";
String password = "sua_senha";
```

6. Execute via Maven:
```bash
mvn compile exec:java -Dexec.mainClass="Main"
```
Ou rode `Main.java` diretamente pelo IntelliJ.

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
- **Config**: configuração e gerenciamento da conexão com o banco
- **Utils**: validações reutilizáveis (CPF, CNPJ, CEP)

---

## 🚀 Próximos passos

Este projeto será evoluído para uma implementação com **Spring Boot + Spring Data JPA**, substituindo o JDBC puro por um ORM e adicionando uma API REST. Acompanhe o repositório para novidades!

---

## 📄 Documentação

A documentação completa do sistema está disponível na pasta `docs/`, cobrindo modelagem de dados, scripts DDL/DML, explicação da integração Java/JDBC e recursos avançados (Views, Procedures e Functions).
