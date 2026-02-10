import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class IDAOCliente {

    String tabelaClientes = "clientes";

    /**
     * Monta a string SQL para salvar os dados
     *
     * @param clientes: dados a serem salvos
     * @return string SQL com uma query de inserção
     */
    private String querySalvar(List<Cliente> clientes){

        return "INSERT INTO " + tabelaClientes + " " + AdaptadorQuery.clienteCampos()
                + " VALUES " + AdaptadorQuery.listaClientes(clientes);
    }

    /**
     * Salva os dados no banco de dados
     *
     * @param clientes: dados a serem salvos
     * @return número de objetos inseridos no banco de dados
     */
    public int salvarTudo(List<Cliente> clientes){

        try {
            Connection conexao = (new GerenteBancoDados()).obterConexao();

            Statement statement = conexao.createStatement();

            // Limpe a tabela antiga e adicione os dados novos
            statement.executeUpdate("TRUNCATE TABLE " + tabelaClientes);

            return statement.executeUpdate(querySalvar(clientes));

        } catch (SQLException | ClassNotFoundException e) {

            e.printStackTrace();

            return -1;
        }
    }
}
