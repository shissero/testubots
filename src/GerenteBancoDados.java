import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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

    public int executarInsert(String query){

        try {

            Statement statement = obterConexao().createStatement();

            return statement.executeUpdate(query);

        } catch (SQLException | ClassNotFoundException e) {

            e.printStackTrace();

            return -1;
        }
    }
}
