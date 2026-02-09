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
public class IRepositorio { // TODO: testar as funções de busca de cliente


    List<Vinho> todosVinhos = new ArrayList<>();

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

        todosOsClientes.add(cliente);
    }

    public void adicionarVinho(Vinho vinho){

        todosVinhos.add(vinho);
    }

    public Optional<Cliente> buscarClientePorCPF(String cpf) {

        Optional<Cliente> resultado = todosOsClientes.stream().filter(el -> el.obterCPF().equals(cpf)).findFirst();

        if (resultado.isPresent()) {

            Cliente cliente = resultado.get();

            return Optional.of(new Cliente(cliente));
        }

        return Optional.empty();
    }

    public Optional<Cliente> buscarClientePorId(UUID id) {

        Optional<Cliente> resultado = todosOsClientes.stream().filter(el -> el.obterID().equals(id)).findFirst();

        if (resultado.isPresent()) {

            Cliente cliente = resultado.get();

            return Optional.of(new Cliente(cliente));
        }

        return Optional.empty();
    }

    public List<Cliente> obterTodosClientes() {

        return todosOsClientes.stream().map(Cliente::new).collect(Collectors.toList());
    }

    public Optional<Vinho> buscarVinhoPorCodigo(UUID id) {

        return todosVinhos.stream().filter(el -> el.obterCodigo().equals(id)).findFirst();
    }

    void atualizar(Cliente cli_novo) {

        boolean resultado = todosOsClientes.removeIf(el -> el.obterID().equals(cli_novo.obterID()));

        if(resultado) todosOsClientes.add(cli_novo);
    }

    void atualizar(Vinho vinho_novo){

        boolean resultado = todosVinhos.removeIf(el -> el.obterCodigo().equals(vinho_novo.obterCodigo()));

        if(resultado) todosVinhos.add(vinho_novo);
    }

    void removerCliente(UUID id) {

        todosOsClientes.removeIf(el -> el.obterID().equals(id));
    }

    void removerVinho(UUID id){

        todosVinhos.removeIf(el -> el.obterCodigo().equals(id));
    }

    // Definindo campos
    private List<Cliente> todosOsClientes = new ArrayList<>();
}
