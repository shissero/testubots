import java.util.ArrayList;
import java.util.List;

import static java.lang.System.*;

public class Cliente {

    private int id;
    private String nome;
    private String cpf;
    private List<Compra> historico = new ArrayList<>();
    private float totalCompras = 0.0f;
    private List<Item> vinhosComprados = new ArrayList<>();
    private List<Item> vinhosNaoComprados = new ArrayList<>();

    public void definirID(int id){ this.id = id; }

    public void definirNome(String nome){ this.nome = nome; }

    public void definirCPF(String cpf){

        this.cpf = validarCPF(cpf);
    }

    public void adicionarCompra(Compra compra){

        historico.add(compra);
    }

    public void incrementarValorTotal(float valor){ totalCompras += valor; }

    public void adicionarVinhoComprado(Item vinho){ vinhosComprados.add(vinho); }

    public void adicionarVinhoNaoComprado(Item vinho){ vinhosNaoComprados.add(vinho);}

    public int obterID(){ return id; }

    public String obterNome(){ return nome; }

    public String obterCPF(){ return cpf; }

    public List<Compra> obterHistorico(){ return historico; }

    public float obterTotalCompras(){ return totalCompras; }

    public List<Item> obterVinhosComprados(){ return vinhosComprados; }

    public Item sugerirVinho(){

        Item item = null;

        // Estas duas variáveis armazenam, respectivamente, a soma das similaridades entre todos os clientes
        // que compraram o vinho e a soma das similaridades entre todos os clientes que nao compraram o vinho
        float SSC, SSN;
        float probabilidade = -1.0f, probAux;



        for (Item a : Historico.obterTodosOsItens()){

            List<Cliente> compradores = a.obterCompradores();
            List<Cliente> naoCompradores = a.obterNaoCompradores();

            SSC = a.obterSSC();
            SSN = a.obterSSN();

            probAux = (SSC - SSN)/(compradores.size() + naoCompradores.size());

            if(probAux >= probabilidade){
                probabilidade = probAux;
                item = a;
            }
        }

        return item == null ? this.vinhosComprados.get(0) : item;
    }

    public float similaridade(Cliente cliente){

        // Estas variáveis correspondem às cardinalidades dos conjuntos de interseção e união dos conjuntos de  vinhos comprados e não comprados
        int ICC = Conjunto.intersecao(this.vinhosComprados, cliente.vinhosComprados).size();
        int INN = Conjunto.intersecao(this.vinhosNaoComprados, cliente.vinhosNaoComprados).size();
        int ICN = Conjunto.intersecao(this.vinhosComprados, cliente.vinhosNaoComprados).size();
        int INC = Conjunto.intersecao(this.vinhosNaoComprados, cliente.vinhosComprados).size();
        int U = Conjunto.uniao(this.vinhosComprados, this.vinhosNaoComprados, cliente.vinhosComprados, cliente.vinhosNaoComprados).size();

        return (float)(ICC + INN - ICN - INC) / (float)U;
    }

    public static String validarCPF(String cpf){

        if(cpf.length() != 14) {
            out.println("CPF " + cpf + " inválido");
            return null;
        }
        else{
            String str = cpf.replace(".", "");
            str = str.replace("-", "");
            return str;
        }
    }
}
