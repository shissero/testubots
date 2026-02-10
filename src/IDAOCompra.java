import java.util.List;

public class IDAOCompra {

    String tabelaCompras = "compras";

    /**
     * Monta a string SQL para salvar os dados de membros simples
     *
     * @param compras: dados a serem salvos
     * @return string SQL com uma query de inserção
     */
    private String querySalvarSimples(List<Compra> compras){

        return "INSERT INTO " + tabelaCompras + " " + AdaptadorQuery.compraCampos()
                + " VALUES " + AdaptadorQuery.listaCompras(compras);
    }

    /**
     * Salva os dados de membros de tipos primitivos
     * @param compras
     * @return
     */
    public int salvarTudoSimples(List<Compra> compras){

        GerenteBancoDados gerente = new GerenteBancoDados();

        // Limpe a tabela antiga e adicione os dados novos
        gerente.executarInsert("TRUNCATE TABLE " + tabelaCompras);

        return gerente.executarInsert(querySalvarSimples(compras));

    }

    /**
     * Monta a string SQL para salvar os dados de itens
     *
     * @param itens: dados a serem salvos
     * @param tabela: tabela em que salvar os dados
     * @return string SQL com uma query de inserção
     */
    private String querySalvarItens(List<Vinho> itens, String tabela){

        return "INSERT INTO " + tabela + " " + AdaptadorQuery.itemCampos()
                + " VALUES " + AdaptadorQuery.listaItem(itens);
    }

    public int salvarItens(Compra compra){

        String tabela = "itens" + compra.obterCodigo().toString().replaceAll("[-]", "");

        GerenteBancoDados gerente = new GerenteBancoDados();

        if(gerente.tabelaExiste(tabela)){
            gerente.executarInsert("TRUNCATE TABLE " + tabela);
        }
        else{

            gerente.criarTabela(tabela + " (codigo VARCHAR(36) NOT NULL)");
        }

        gerente.executarInsert(querySalvarItens(compra.obterVinhos(), tabela));

        return 0;
    }

    /**
     * Salva os dados no banco de dados
     *
     * @param compras: dados a serem salvos
     * @return número de objetos inseridos no banco de dados
     */
    public int salvarTudo(List<Compra> compras){

        int linhas = salvarTudoSimples(compras);

        for(Compra compra : compras){

            salvarItens(compra);
        }

        return 0;
    }
}
