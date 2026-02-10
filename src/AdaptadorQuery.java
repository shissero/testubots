import java.util.ArrayList;
import java.util.List;

/**
 * Esta classe é responsável por converter classes em strings que possam ser lidas
 * pelo MySQL
 */
public class AdaptadorQuery {

    public static String clienteCampos(){

        return "(id, nome, cpf)";
    }

    public static String cliente(Cliente cliente){

        return "(\"" + cliente.obterID() + "\", \"" + cliente.obterNome() + "\", \"" + cliente.obterCPF() + "\")";
    }

    public static String listaClientes(List<Cliente> clientes){

        List<String> strings = new ArrayList<>();

        for (Cliente cliente : clientes) strings.add(AdaptadorQuery.cliente(cliente));

        return String.join(", ", strings);
    }

    public static String vinhoCampos(){

        return "(codigo, produto, variedade, pais, categoria, safra, preco)";
    }

    public static String vinho(Vinho vinho){

        return "(\"" + vinho.obterCodigo() + "\", \"" + vinho.obterProduto() + "\", \"" + vinho.obterVariedade()
                + "\", \"" + vinho.obterPais() + "\", \"" + vinho.obterCategoria() + "\", \"" + vinho.obterSafra()
                + "\", \"" + vinho.obterPreco() + "\")";
    }

    public static String listaVinhos(List<Vinho> vinhos){

        List<String> strings = new ArrayList<>();

        for (Vinho vinho : vinhos) strings.add(AdaptadorQuery.vinho(vinho));

        return String.join(", ", strings);
    }
}
