import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GerenteBancoDados {

    private Connection conexao;

    // Definindo getters
    public Connection obterConexao() throws SQLException, ClassNotFoundException {

        if(conexao == null) this.conectar();

        return conexao;
    }

    public void conectar() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

        conexao = DriverManager.getConnection(DadosBancoDados.URL, DadosBancoDados.USER, DadosBancoDados.PASSWORD);
    }
}
