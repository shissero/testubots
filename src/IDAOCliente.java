import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

        GerenteBancoDados gerente = new GerenteBancoDados();

        // Limpe a tabela antiga e adicione os dados novos

        gerente.executarInsert("TRUNCATE TABLE " + tabelaClientes);

        return gerente.executarInsert(querySalvar(clientes));

    }

    public List<Cliente> lerTudo(){

        List<Cliente> resultado = new ArrayList<>();

        GerenteBancoDados gerente = new GerenteBancoDados();

        ResultSet resultSet = gerente.executarSelect("SELECT * FROM " + tabelaClientes);

        try {
            while(resultSet.next()){

                Cliente novo = new Cliente();

                novo.definirID(UUID.fromString(resultSet.getString("id")));
                novo.definirNome(resultSet.getString("nome"));
                novo.definirCPF(resultSet.getString("cpf"));

                resultado.add(novo);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }

        return resultado;
    }
}
