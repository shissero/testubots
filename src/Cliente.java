import com.google.gson.annotations.JsonAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.lang.System.*;

/**
 * Classe de dados que representa os clientes
 */
public class Cliente {

    // Definindo campos
    @JsonAdapter(AdaptadorIdCliente.class)
    private UUID id;
    private String nome;

    @JsonAdapter(AdaptadorCPF.class)
    private String cpf;
    private List<Compra> compras = new ArrayList<>();
    private float totalCompras = 0.0f; // TODO: essa variável é realmente necessária? Alterar o getter dela para iterar pela variável compras não é melhor?
    private List<Vinho> vinhosComprados = new ArrayList<>();
    private List<Vinho> vinhosNaoComprados = new ArrayList<>();

    Cliente(){}

    Cliente(Cliente cliente){

        this.id = cliente.id;
        this.nome = cliente.nome;
        this.cpf = cliente.cpf;
    }


    // Definindo setters
    public void definirID(UUID id) {
        this.id = id;
    }

    public void definirNome(String nome) {
        this.nome = nome;
    }

    public void definirCPF(String cpf) {

        this.cpf = cpf;
    }

    public void adicionarCompra(Compra compra) {

        compras.add(compra);
    }

    public void adicionarVinhoComprado(Vinho vinho) {
        vinhosComprados.add(vinho);
    }

    public void adicionarVinhoNaoComprado(Vinho vinho) {
        vinhosNaoComprados.add(vinho);
    }


    // Definindo getters
    public UUID obterID() {
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

        return compras.stream().reduce(0.0f, (subtotal, element) -> subtotal + (element.obterValorTotal()), Float::sum);
    }

    public List<Vinho> obterVinhosComprados() {
        return vinhosComprados;
    }





    public void incrementarValorTotal(float valor) {
        totalCompras += valor;
    }





    public Vinho sugerirVinho() { // TODO: isso podia ser um getter - obterSugestao

        Vinho vinho = null;

        // Estas duas variáveis armazenam, respectivamente, a soma das similaridades entre todos os clientes
        // que compraram o vinho e a soma das similaridades entre todos os clientes que nao compraram o vinho
        float SSC, SSN;
        float probabilidade = -1.0f, probAux;


        for (Vinho a : Historico.obterTodosOsItens()) {

            List<Cliente> compradores = a.obterCompradores();
            List<Cliente> naoCompradores = a.obterNaoCompradores();

            SSC = a.obterSSC();
            SSN = a.obterSSN();

            probAux = (SSC - SSN) / (compradores.size() + naoCompradores.size());

            if (probAux >= probabilidade) {
                probabilidade = probAux;
                vinho = a;
            }
        }

        return vinho == null ? this.vinhosComprados.get(0) : vinho;
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

    @Override
    public Cliente clone() throws CloneNotSupportedException {

        return (Cliente) super.clone();
    }
}
