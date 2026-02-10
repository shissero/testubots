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
}
