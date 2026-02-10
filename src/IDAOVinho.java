import java.util.List;

public class IDAOVinho {

    String tabelaClientes = "vinhos";

    /**
     * Monta a string SQL para salvar os dados
     *
     * @param vinhos: dados a serem salvos
     * @return string SQL com uma query de inserção
     */
    private String querySalvar(List<Vinho> vinhos){

        return "INSERT INTO " + tabelaClientes + " " + AdaptadorQuery.vinhoCampos()
                + " VALUES " + AdaptadorQuery.listaVinhos(vinhos);
    }

    /**
     * Salva os dados no banco de dados
     *
     * @param vinhos: dados a serem salvos
     * @return número de objetos inseridos no banco de dados
     */
    public int salvarTudo(List<Vinho> vinhos){

        GerenteBancoDados gerente = new GerenteBancoDados();

        // Limpe a tabela antiga e adicione os dados novos

        gerente.executarInsert("TRUNCATE TABLE " + tabelaClientes);

        return gerente.executarInsert(querySalvar(vinhos));

    }
}
