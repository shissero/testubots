import java.sql.*;

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

    public boolean tabelaExiste(String tabela){

        ResultSet resultSet = null;
        try {
            resultSet = obterConexao().getMetaData().getTables(null, null, tabela, null);

            return resultSet.isBeforeFirst();

        } catch (SQLException | ClassNotFoundException e) {

            e.printStackTrace();

            return false;
        }
    }

    public void criarTabela(String query){

        try {

            Statement statement = obterConexao().createStatement();

            statement.executeUpdate("CREATE TABLE " + query);

        } catch (SQLException | ClassNotFoundException e) {

            e.printStackTrace();
        }
    }
}
