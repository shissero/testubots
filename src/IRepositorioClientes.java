import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Esta classe representa a interface para os repositórios da classe Cliente.
 * Os métodos estão sendo implementados nela somente para facilitar a refatoração mais tarde.
 */
public class IRepositorioClientes {

    /**
     * Esta função carrega na memória todos os clientes.
     */
     // Esta implementação é provisória, pretendo integrar com o MySQL assim que instalar mais armazenamento interno na minha máquina
    public void carregarTodosClientes() {

        String arquivo_clientes = "res/clientes";

        try {

            BufferedReader reader = new BufferedReader(new FileReader(arquivo_clientes));

            String clientes_json = reader.lines().collect(Collectors.joining("\n"));

            reader.close();

            Type listType = new TypeToken<ArrayList<Cliente>>(){}.getType();
            todosOsClientes = new Gson().fromJson(clientes_json, listType);
        }
        catch (FileNotFoundException e) {

            System.out.println("Arquivo de clientes não encontrado");

            e.printStackTrace();

            System.exit(1);
        }
        catch (IOException e){}
    }

    public void adicionar(Cliente cliente) {

        todosOsClientes.add(cliente);
    }

    public Optional<Cliente> buscarPorCPF(String cpf) {

        return todosOsClientes.stream().filter(x -> x.obterCPF().equals(cpf)).findFirst();
    }

    public List<Cliente> obterTodosClientes(){

        return todosOsClientes;
    }

    void atualizar(Cliente cli_novo) {
/*
        Optional<Cliente> cliente = buscarPorId(cli_novo.obterID());

        cliente.ifPresent(cli -> cli = cli_novo);*/
    }

    void remover(Cliente cliente) {
/*
        todosOsClientes.remove(cliente);*/
    }

    // Definindo campos
    private List<Cliente> todosOsClientes = new ArrayList<>();
}
