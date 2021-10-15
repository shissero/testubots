import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Historico {

    private static List<Compra> todasAsCompras = new ArrayList<>();
    private static List<Cliente> todosOsClientes = new ArrayList<>();
    private static List<Item> todosOsItens = new ArrayList<>();


    private Historico(){}

    public static void construir(){

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

    private static void armazenarCompras(){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("res/compras_historico"));

            String linha = bufferedReader.readLine();
            linha = bufferedReader.readLine(); // Lê a chave que inicia a descrição da primeira compra

            do{

                Compra compra = new Compra();
                Item item = new Item();

                linha = bufferedReader.readLine();
                compra.definirCodigo(extrairInformacao(linha, 3));

                linha = bufferedReader.readLine();
                compra.definirData(extrairInformacao(linha, 3));

                linha = bufferedReader.readLine();
                compra.definirCliente(encontrarClientePorCPF(extrairInformacao(linha, 3)));

                linha = bufferedReader.readLine(); // Lê a linha introdutória da lista de itens
                linha = bufferedReader.readLine(); // Lê a chave que inicia a descrição do primeiro item

                do{

                    linha = bufferedReader.readLine();

                    if(linha.contains("codigo")){
                        item.definirCodigo(extrairInformacao(linha, 3));
                        linha = bufferedReader.readLine();
                    }

                    item.definirProduto(extrairInformacao(linha, 3));

                    linha = bufferedReader.readLine();
                    item.definirVariedade(extrairInformacao(linha, 3));

                    linha = bufferedReader.readLine();
                    item.definirPais(extrairInformacao(linha, 3));

                    linha = bufferedReader.readLine();
                    item.definirCategoria(extrairInformacao(linha, 3));

                    linha = bufferedReader.readLine();
                    item.definirSafra(Integer.parseInt(extrairInformacao(linha, 3)));

                    linha = bufferedReader.readLine();
                    item.definirPreco(Float.parseFloat(extrairInformacao(linha, 2).substring(2)));

                    compra.adicionarItem(item);

                    linha = bufferedReader.readLine(); // Lê a chave que encerra a descrição de um item
                    linha = bufferedReader.readLine(); // Lê a chave que inicia a descrição do próximo item ou o colchete que finaliza a lista de itens da compra atual
                }while(!linha.contains("]"));

                linha = bufferedReader.readLine();
                compra.definirValorTotal(Float.parseFloat(extrairInformacao(linha, 2).substring(2)));

                todasAsCompras.add(compra);

                for(Cliente a : todosOsClientes) if(compra.obterCliente().obterID() == (a.obterID())) a.adicionarCompra(compra);

                linha = bufferedReader.readLine(); // Lê a chave que finaliza a descrição de uma compra
                linha = bufferedReader.readLine(); // Lê a chave que finaliza inicia a descrição da próxima compra, ou que finaliza o histórico
            }while(!linha.contains("]"));


        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void armazenarClientes(){

        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader("res/clientes"));
            String linha;

            // Aqui descarta-se o colchete e chave iniciais
            bufferedReader.readLine();
            bufferedReader.readLine();

            do{
                Cliente cliente = new Cliente();

                linha = bufferedReader.readLine();
                linha = extrairInformacao(linha, 2);
                cliente.definirID(Integer.parseInt(linha.substring(2, linha.length() - 1)));

                linha = bufferedReader.readLine();
                cliente.definirNome(extrairInformacao(linha, 3));

                linha = bufferedReader.readLine();
                cliente.definirCPF(extrairInformacao(linha, 3));

                todosOsClientes.add(cliente);

                bufferedReader.readLine(); // Descarta-se a chave que finaliza a descrição do cliente

                linha = bufferedReader.readLine();
            }while(!linha.contains("]"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void armazenarItens(){

        for(Compra a : todasAsCompras){
            for(Item b : a.obterItens()){
                 if(!Conjunto.pertence(b, todosOsItens)) todosOsItens.add(b);
            }
        }
    }

    private static String extrairInformacao(String string, int posicao){

        String[] particao = string.split("\"");

        return particao[posicao];
    }

    private static void calcularTotais(){
        for(Compra a : todasAsCompras){
            for(Cliente b : todosOsClientes){
                if(a.obterCliente().obterCPF().equals(b.obterCPF())){
                    b.incrementarValorTotal(a.obterValorTotal());
                    break;
                }
            }
        }
    }

    public static List<Cliente> obterClientesPorTotal(){
        Ordenacao.ordenarPorTotal(0, todosOsClientes.size() - 1);

        return todosOsClientes;
    }

    static class Ordenacao{
        static void ordenarPorTotal(int inicio, int fim) {



            if(inicio < fim) {
                int indiceDoPivo;
                indiceDoPivo = particionar(inicio, fim);

                ordenarPorTotal(inicio, indiceDoPivo - 1);
                ordenarPorTotal(indiceDoPivo + 1, fim);
            }
        }

        static int particionar(int inicio, int fim){

            float pivot = todosOsClientes.get(fim).obterTotalCompras();
            int i = inicio - 1;

            for(int j = inicio; j <= fim - 1; j++){
                if(todosOsClientes.get(j).obterTotalCompras() >= pivot ){
                    i++;

                    Collections.swap(todosOsClientes, i, j);
                }
            }

            Collections.swap(todosOsClientes, i+1, fim);

            return i+1;
        }
    }

    public static List<Compra> obterMaiorCompraDoAno(int ano){


        List<Compra> maiorCompra = new ArrayList<>();

        for(Compra a : todasAsCompras){

            if(a.obterData().getYear() == ano){
                if(maiorCompra.isEmpty()){
                    maiorCompra.add(a);
                }
                else if (a.obterValorTotal() > maiorCompra.get(0).obterValorTotal()){
                    maiorCompra.clear();
                    maiorCompra.add(a);
                }
                else if(a.obterValorTotal() == maiorCompra.get(0).obterValorTotal()){
                    maiorCompra.add(a);
                }
            }
        }

        return maiorCompra;
    }

    public static List<Cliente> obterClientesMaisFieis(){
        List<Cliente> clientesFieis = new ArrayList<>();

        for(Cliente a : todosOsClientes){
            if(clientesFieis.isEmpty()) clientesFieis.add(a);
            else{
                for(Cliente b : clientesFieis){
                    if(b.obterHistorico().size() < a.obterHistorico().size()){
                        clientesFieis.add(clientesFieis.indexOf(b), a);
                        break;
                    }
                    else if(clientesFieis.indexOf(b) == clientesFieis.size() - 1){
                        clientesFieis.add(a);
                        break;
                    }
                }

                if(clientesFieis.size() > 3) clientesFieis.remove(clientesFieis.size() - 1);
            }
        }

        return clientesFieis;
    }

    public static Cliente encontrarClientePorCPF(String cpf){

        String str = Cliente.validarCPF(cpf);


        for(Cliente a : todosOsClientes) if(a.obterCPF().equals(str)) return a;

        System.out.println("CPF " + str + " não encontrado");
        return null;
    }

    static void finalizarClientes(){

        for(Cliente a : todosOsClientes){

            for(Compra b : a.obterHistorico()){

                for(Item c : b.obterItens()){
                    if(!Conjunto.pertence(c, a.obterVinhosComprados())) a.adicionarVinhoComprado(c);
                }
            }

            for(Item d : todosOsItens) if(!Conjunto.pertence(d, a.obterVinhosComprados())) a.adicionarVinhoNaoComprado(d);
        }
    }

    static void finalizarItens(){

        for(Item a : todosOsItens){

            for(Cliente b : todosOsClientes) {
                if(Conjunto.pertence(a, b.obterVinhosComprados())) a.adicionarComprador(b);
                else a.adicionarNaoComprador(b);
            }

        }
    }

    public static List<Item> obterTodosOsItens(){ return todosOsItens; }
}
