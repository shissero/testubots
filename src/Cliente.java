import java.util.ArrayList;
import java.util.List;

import static java.lang.System.*;

/**
 * Classe de dados que representa os clientes
 */
public class Cliente {

    // Definindo campos
    private int id;
    private String nome;
    private String cpf;
    private List<Compra> compras = new ArrayList<>();
    private float totalCompras = 0.0f; // TODO: essa variável é realmente necessária? Alterar o getter dela para iterar pela variável compras não é melhor?
    private List<Item> vinhosComprados = new ArrayList<>();
    private List<Item> vinhosNaoComprados = new ArrayList<>();


    // Definindo setters
    public void definirID(int id) {
        this.id = id;
    }

    public void definirNome(String nome) {
        this.nome = nome;
    }

    public void definirCPF(String cpf) {

        this.cpf = validarCPF(cpf);
    }

    public void adicionarCompra(Compra compra) {

        compras.add(compra);
    }

    public void adicionarVinhoComprado(Item vinho) {
        vinhosComprados.add(vinho);
    }

    public void adicionarVinhoNaoComprado(Item vinho) {
        vinhosNaoComprados.add(vinho);
    }


    // Definindo getters
    public int obterID() {
        return id;
    }

    public String obterNome() {
        return nome;
    }

    public String obterCPF() {
        return cpf;
    }

    public List<Compra> obterHistorico() {
        return compras;
    }

    public float obterTotalCompras() {
        return totalCompras;
    }

    public List<Item> obterVinhosComprados() {
        return vinhosComprados;
    }





    public void incrementarValorTotal(float valor) {
        totalCompras += valor;
    }





    public Item sugerirVinho() { // TODO: isso podia ser um getter - obterSugestao

        Item item = null;

        // Estas duas variáveis armazenam, respectivamente, a soma das similaridades entre todos os clientes
        // que compraram o vinho e a soma das similaridades entre todos os clientes que nao compraram o vinho
        float SSC, SSN;
        float probabilidade = -1.0f, probAux;


        for (Item a : Historico.obterTodosOsItens()) {

            List<Cliente> compradores = a.obterCompradores();
            List<Cliente> naoCompradores = a.obterNaoCompradores();

            SSC = a.obterSSC();
            SSN = a.obterSSN();

            probAux = (SSC - SSN) / (compradores.size() + naoCompradores.size());

            if (probAux >= probabilidade) {
                probabilidade = probAux;
                item = a;
            }
        }

        return item == null ? this.vinhosComprados.get(0) : item;
    }

    public float similaridade(Cliente cliente) { // TODO: podia ser um getter

        // Estas variáveis correspondem às cardinalidades dos conjuntos de interseção e união dos conjuntos de  vinhos comprados e não comprados
        int ICC = Conjunto.intersecao(this.vinhosComprados, cliente.vinhosComprados).size();
        int INN = Conjunto.intersecao(this.vinhosNaoComprados, cliente.vinhosNaoComprados).size();
        int ICN = Conjunto.intersecao(this.vinhosComprados, cliente.vinhosNaoComprados).size();
        int INC = Conjunto.intersecao(this.vinhosNaoComprados, cliente.vinhosComprados).size();
        int U = Conjunto.uniao(this.vinhosComprados, this.vinhosNaoComprados, cliente.vinhosComprados, cliente.vinhosNaoComprados).size();

        return (float) (ICC + INN - ICN - INC) / (float) U;
    }

    public static String validarCPF(String cpf) { // TODO: essa função não deveria estar aqui

        if (cpf.length() != 14) {
            out.println("CPF " + cpf + " inválido");
            return null;
        } else {
            String str = cpf.replace(".", "");
            str = str.replace("-", "");
            return str;
        }
    }
}
