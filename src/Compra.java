import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de dados para representar compras
 */
public class Compra {

    // Definindo campos
    private String codigo;
    private ZonedDateTime data;
    private Cliente cliente;
    private List<Item> itens = new ArrayList<>();
    private float valorTotal = 0;



    // Definindo setters
    public void definirCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void definirData(String data) {

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // TODO: que tal criar uma classe para cuidar da validação desses dados e passar um objeto LocalDate para o setter?

        LocalDate date = LocalDate.parse(data, formato);

        this.data = date.atStartOfDay(ZoneId.systemDefault());
    }

    public void definirCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void definirValorTotal(float valor) {
        this.valorTotal = valor;
    }

    public void adicionarItem(Item item) {
        itens.add(item);
    }



    // Definindo getters
    public Cliente obterCliente() {
        return cliente;
    }

    public String obterCodigo() {
        return codigo;
    }

    public ZonedDateTime obterData() {
        return data;
    }

    public List<Item> obterItens() {
        return itens;
    }

    public float obterValorTotal() {
        return valorTotal;
    }
}
