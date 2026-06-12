package conectaDB;

import java.sql.*;
import javax.swing.JOptionPane;

public class ConectaPostgres {


    private Connection con = null;
    public Statement stmt;
    public ResultSet rs;
    private String endereco;
    private String usuario;
    private String senha;

    public void Conectar(String strEnd, String strUsuario, String strSenha) {

        /** Recebendo o endereco,usuario e senha do usuario e repassando para a variavel global */
        endereco = strEnd;
        usuario = strUsuario;
        senha = strSenha;
        System.out.println("Tentando realizar conexão só apresentando...");
        JOptionPane.showMessageDialog(null, "Tentando realizar conexão só apresentando...");
        try {
            /** Pasando o nome do Driver do PostgreSQL */
            Class.forName("org.postgresql.Driver");

            /** Obtendo a conexao com o banco de dados*/
            con = DriverManager.getConnection(endereco, usuario, strSenha);

            /** Criando o Statement */
            stmt = con.createStatement();
            JOptionPane.showMessageDialog(null, "Banco conectado com sucesso");
            /** Retorna um erro caso nao encontre o driver, ou alguma informacao sobre o mesmo
             * esteja errada */
        } catch (ClassNotFoundException cnfe) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar o driver viu Bradesco");
            cnfe.printStackTrace();

            /** Retorna um erro caso exista erro de query SQL */
        } catch (SQLException sqlex) {
            JOptionPane.showMessageDialog(null, "erro na query");
            sqlex.printStackTrace();

        }
    }

    /** Esse metodo quando invocado, realiza a desconexao com o banco */
    public void Desconectar() {

        try {
            con.close();

            /** Retorna um erro caso nao consiga desconectar */
        } catch (SQLException onConClose) {
            JOptionPane.showMessageDialog(null, "Erro ao desconectar o banco");
            onConClose.printStackTrace();
        }
    }

    public Connection getCon() {
        return con;
    }

}
