import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de dados para representar compras
 */
public class Compra {

    // Definindo campos
    private String codigo;
    private LocalDate data;
    private Cliente cliente;

    // TODO: Esse nome é provisório, apenas enquanto não domino melhor a Gson
    // Nomear esse item como "vinhos" vai criar problemas na hora da desserialização
    //@JsonAdapter(JSONDeserializerVinho.class)
    private List<Vinho> itens = new ArrayList<>();
    private float valorTotal = 0;



    // Definindo setters
    public void definirCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void definirData(String data) {

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // TODO: que tal criar uma classe para cuidar da validação desses dados e passar um objeto LocalDate para o setter?

        this.data = LocalDate.parse(data, formato);
    }

    public void definirCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void definirValorTotal(float valor) {
        this.valorTotal = valor;
    }

    public void adicionarVinho(Vinho vinho) {
        itens.add(vinho);
    }



    // Definindo getters
    public Cliente obterCliente() {
        return cliente;
    }

    public String obterCodigo() {
        return codigo;
    }

    public LocalDate obterData() {
        return data;
    }

    public List<Vinho> obterVinhos() {
        return itens;
    }

    public float obterValorTotal() {
        return valorTotal;
    }
}
