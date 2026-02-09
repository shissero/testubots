import java.io.*;
import java.util.ArrayList;
import java.util.List;

// TODO: essa classe é um repositório, deve ser refatorada

/**
 * Classe repositório, armazena os dados de todas as classes de dados
 */
public class Historico {

    private static List<Compra> todasAsCompras = new ArrayList<>();
    private static List<Vinho> todosOsVinhos = new ArrayList<>();


    private Historico() {
    } // TODO: essa classe não deveria ser estática

    /**
     * Carrega os dados na memória
     */
    public static void construir() {

        armazenarClientes();
        armazenarCompras();
        calcularTotais();
        armazenarItens();
        finalizarClientes();
        finalizarItens();


    }

    /********************************************************************************
     *********************************************************************************
     ********************************************************************************/

    /**
     * Carrega os dados das compras na memória
     */
    private static void armazenarCompras() {
        /*try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("res/compras_historico"));

            String linha = bufferedReader.readLine();
            linha = bufferedReader.readLine(); // Lê a chave que inicia a descrição da primeira compra

            do {

                Compra compra = new Compra();
                Vinho vinho = new Vinho();

                linha = bufferedReader.readLine();
                compra.definirCodigo(extrairInformacao(linha, 3));

                linha = bufferedReader.readLine();
                compra.definirData(extrairInformacao(linha, 3));

                linha = bufferedReader.readLine();
                compra.definirCliente(encontrarClientePorCPF(extrairInformacao(linha, 3)));

                linha = bufferedReader.readLine(); // Lê a linha introdutória da lista de itens
                linha = bufferedReader.readLine(); // Lê a chave que inicia a descrição do primeiro item

                do {

                    linha = bufferedReader.readLine();

                    if (linha.contains("codigo")) {
                        vinho.definirCodigo(extrairInformacao(linha, 3));
                        linha = bufferedReader.readLine();
                    }

                    vinho.definirProduto(extrairInformacao(linha, 3));

                    linha = bufferedReader.readLine();
                    vinho.definirVariedade(extrairInformacao(linha, 3));

                    linha = bufferedReader.readLine();
                    vinho.definirPais(extrairInformacao(linha, 3));

                    linha = bufferedReader.readLine();
                    vinho.definirCategoria(extrairInformacao(linha, 3));

                    linha = bufferedReader.readLine();
                    vinho.definirSafra(Integer.parseInt(extrairInformacao(linha, 3)));

                    linha = bufferedReader.readLine();
                    vinho.definirPreco(Float.parseFloat(extrairInformacao(linha, 2).substring(2)));

                    compra.adicionarVinho(vinho);

                    linha = bufferedReader.readLine(); // Lê a chave que encerra a descrição de um item
                    linha = bufferedReader.readLine(); // Lê a chave que inicia a descrição do próximo item ou o colchete que finaliza a lista de itens da compra atual
                } while (!linha.contains("]"));

                linha = bufferedReader.readLine();
                compra.definirValorTotal(Float.parseFloat(extrairInformacao(linha, 2).substring(2)));

                todasAsCompras.add(compra);

                //for (Cliente a : todosOsClientes)
                  //  if (compra.obterCliente().obterID() == (a.obterID())) a.adicionarCompra(compra);

                linha = bufferedReader.readLine(); // Lê a chave que finaliza a descrição de uma compra
                linha = bufferedReader.readLine(); // Lê a chave que finaliza inicia a descrição da próxima compra, ou que finaliza o histórico
            } while (!linha.contains("]"));


        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    /**
     * Carrega os dados dos clientes na memória,
     * mas somente id, nome e cpf
     */
    private static void armazenarClientes() {

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("res/clientes"));
            String linha;

            // Aqui descarta-se o colchete e chave iniciais
            bufferedReader.readLine();
            bufferedReader.readLine();

            do {
                Cliente cliente = new Cliente();

                linha = bufferedReader.readLine();
                linha = extrairInformacao(linha, 2);
                //cliente.definirID(Integer.parseInt(linha.substring(2, linha.length() - 1)));

                linha = bufferedReader.readLine();
                cliente.definirNome(extrairInformacao(linha, 3));

                linha = bufferedReader.readLine();
                cliente.definirCPF(extrairInformacao(linha, 3));

                //todosOsClientes.add(cliente);

                bufferedReader.readLine(); // Descarta-se a chave que finaliza a descrição do cliente

                linha = bufferedReader.readLine();
            } while (!linha.contains("]"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carrega os dados dos itens na memória,
     * mas não os campos de compradores e não compradores
     */
    private static void armazenarItens() {

        for (Compra a : todasAsCompras) {
            for (Vinho b : a.obterVinhos()) {
                if (!Conjunto.pertence(b, todosOsVinhos)) todosOsVinhos.add(b);
            }
        }
    }

    private static String extrairInformacao(String string, int posicao) {

        String[] particao = string.split("\"");

        return particao[posicao];
    }

    /**
     * Define quanto cada cliente gastou
     */
    private static void calcularTotais() {
        for (Compra a : todasAsCompras) {
            //for (Cliente b : todosOsClientes) {
              //  if (a.obterCliente().obterCPF().equals(b.obterCPF())) {
                //    b.incrementarValorTotal(a.obterValorTotal());
                  //  break;
                //}
            //}
        }
    }

    public static List<Cliente> obterClientesPorTotal() {
        //Ordenacao.ordenarPorTotal(0, todosOsClientes.size() - 1);

        //return todosOsClientes;
        return null;
    }

    static class Ordenacao {
        static void ordenarPorTotal(int inicio, int fim) {


            if (inicio < fim) {
                int indiceDoPivo;
                indiceDoPivo = particionar(inicio, fim);

                ordenarPorTotal(inicio, indiceDoPivo - 1);
                ordenarPorTotal(indiceDoPivo + 1, fim);
            }
        }

        static int particionar(int inicio, int fim) {

            //float pivot = todosOsClientes.get(fim).obterTotalCompras();
            int i = inicio - 1;

            for (int j = inicio; j <= fim - 1; j++) {
              //  if (todosOsClientes.get(j).obterTotalCompras() >= pivot) {
                    i++;

                //    Collections.swap(todosOsClientes, i, j);
                //}
            }

            //Collections.swap(todosOsClientes, i + 1, fim);

            return i + 1;
        }
    }

    public static List<Compra> obterMaiorCompraDoAno(int ano) {


        List<Compra> maiorCompra = new ArrayList<>();

        for (Compra a : todasAsCompras) {

            if (a.obterData().getYear() == ano) {
                if (maiorCompra.isEmpty()) {
                    maiorCompra.add(a);
                } else if (a.obterValorTotal() > maiorCompra.get(0).obterValorTotal()) {
                    maiorCompra.clear();
                    maiorCompra.add(a);
                } else if (a.obterValorTotal() == maiorCompra.get(0).obterValorTotal()) {
                    maiorCompra.add(a);
                }
            }
        }

        return maiorCompra;
    }

    public static List<Cliente> obterClientesMaisFieis() {
        List<Cliente> clientesFieis = new ArrayList<>();

        /*for (Cliente a : todosOsClientes) {
            if (clientesFieis.isEmpty()) clientesFieis.add(a);
            else {
                for (Cliente b : clientesFieis) {
                    if (b.obterHistorico().size() < a.obterHistorico().size()) {
                        clientesFieis.add(clientesFieis.indexOf(b), a);
                        break;
                    } else if (clientesFieis.indexOf(b) == clientesFieis.size() - 1) {
                        clientesFieis.add(a);
                        break;
                    }
                }

                if (clientesFieis.size() > 3) clientesFieis.remove(clientesFieis.size() - 1);
            }
        }*/

        return clientesFieis;
    }

    public static Cliente encontrarClientePorCPF(String cpf) {

        String str = Cliente.validarCPF(cpf);


        //for (Cliente a : todosOsClientes) if (a.obterCPF().equals(str)) return a;

        System.out.println("CPF " + str + " não encontrado");
        return null;
    }

    /**
     * Finaliza a construção dos dados dos clientes,
     * adicionando as informações de vinhos comprados e não comprados
     */
    static void finalizarClientes() {

        /*for (Cliente a : todosOsClientes) {

            for (Compra b : a.obterHistorico()) {

                for (Item c : b.obterItens()) {
                    if (!Conjunto.pertence(c, a.obterVinhosComprados())) a.adicionarVinhoComprado(c);
                }
            }

            for (Item d : todosOsItens)
                if (!Conjunto.pertence(d, a.obterVinhosComprados())) a.adicionarVinhoNaoComprado(d);
        }*/
    }

    /**
     * Finaliza a construção dos dados dos items,
     * adicionando as informações de compradores e não compradores
     */
    static void finalizarItens() {

        for (Vinho a : todosOsVinhos) {

            /*for (Cliente b : todosOsClientes) {
                if (Conjunto.pertence(a, b.obterVinhosComprados())) a.adicionarComprador(b);
                else a.adicionarNaoComprador(b);
            }*/

        }
    }

    public static List<Vinho> obterTodosOsItens() {
        return todosOsVinhos;
    }
}
