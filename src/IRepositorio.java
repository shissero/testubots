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

    // Definindo campos
    private List<Cliente> todosOsClientes = new ArrayList<>();
    List<Vinho> todosVinhos = new ArrayList<>();
    List<Compra> todasAsCompras = new ArrayList<>();

    // Definindo as operações CRUD
    public void adicionarCliente(Cliente cliente) {

        todosOsClientes.add(cliente);
    }

    public void adicionarVinho(Vinho vinho){

        todosVinhos.add(vinho);
    }

    public void adicionarCompra(Compra compra){

        todasAsCompras.add(compra);
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

    public Optional<Compra> buscarCompraPorCodigo(UUID id){

        return todasAsCompras.stream().filter(el -> el.obterCodigo().equals(id)).findFirst();
    }

    void atualizar(Cliente cli_novo) {

        boolean resultado = todosOsClientes.removeIf(el -> el.obterID().equals(cli_novo.obterID()));

        if(resultado) todosOsClientes.add(cli_novo);
    }

    void atualizar(Vinho vinho_novo){

        boolean resultado = todosVinhos.removeIf(el -> el.obterCodigo().equals(vinho_novo.obterCodigo()));

        if(resultado) todosVinhos.add(vinho_novo);
    }

    void atualizar(Compra compra_nova){

        boolean resultado = todasAsCompras.removeIf(el -> el.obterCodigo().equals(compra_nova.obterCodigo()));

        if(resultado) todasAsCompras.add(compra_nova);
    }

    void removerCliente(UUID id) {

        todosOsClientes.removeIf(el -> el.obterID().equals(id));
    }

    void removerVinho(UUID id){

        todosVinhos.removeIf(el -> el.obterCodigo().equals(id));
    }

    void removerCompra(UUID id){

        todasAsCompras.removeIf(el -> el.obterCodigo().equals(id));
    }


    /**
     * Esta função carrega dados de clientes a partir de um arquivo JSON
     *
     * @param arquivoClientes: caminho para o arquivo com os dados dos clientes
     */
    // TODO: Corrigir esta função para verificar se os usuários já existem no banco de dados e se há conflito entre os dados JSON e os dados do BD
    public void carregarClientesJSON(String arquivoClientes) {

        List<Cliente> clientesJSON = null;

        try {

            String clientes_json = Utils.lerArquivo(arquivoClientes);

            Type listType = new TypeToken<ArrayList<Cliente>>(){}.getType();
            clientesJSON = new Gson().fromJson(clientes_json, listType);
        }
        catch (FileNotFoundException e) {

            System.out.println("Arquivo de clientes não encontrado");

            e.printStackTrace();

            System.exit(1);
        }

        if(clientesJSON != null) todosOsClientes.addAll(clientesJSON);
    }
}
