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
public class IRepositorio {

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

    public void adicionarCliente(Cliente cliente) {

        cliente.definirID(UUID.randomUUID());
        todosOsClientes.add(cliente);
    }

    public Optional<Cliente> buscarPorCPF(String cpf) {

        Optional<Cliente> resultado = todosOsClientes.stream().filter(x -> x.obterCPF().equals(cpf)).findFirst();

        if (resultado.isPresent()) {

            Cliente cliente = resultado.get();

            return Optional.of(new Cliente(cliente));
        }

        return Optional.empty();
    }

    public Optional<Cliente> buscarPorId(UUID id) {

        Optional<Cliente> resultado = todosOsClientes.stream().filter(el -> el.obterID() == id).findFirst();

        if (resultado.isPresent()) {

            Cliente cliente = resultado.get();

            return Optional.of(new Cliente(cliente));
        }

        return Optional.empty();
    }

    public List<Cliente> obterTodosClientes() {

        return todosOsClientes.stream().map(Cliente::new).collect(Collectors.toList());
    }

    void atualizar(Cliente cli_novo) {

        todosOsClientes.removeIf(el -> el.obterID() == cli_novo.obterID());

        todosOsClientes.add(cli_novo);
    }

    void remover(UUID id) {

        todosOsClientes.removeIf(el -> el.obterID() == id);
    }

    // Definindo campos
    private List<Cliente> todosOsClientes = new ArrayList<>();
}
