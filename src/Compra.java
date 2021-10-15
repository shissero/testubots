import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Compra {

    private String codigo;
    private ZonedDateTime data;
    private Cliente cliente;
    private List<Item> itens = new ArrayList<>();
    private float valorTotal = 0;

    public void definirCodigo(String codigo){
        this.codigo = codigo;
    }

    public void definirData(String data){

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate date = LocalDate.parse(data, formato);

        this.data = date.atStartOfDay(ZoneId.systemDefault());
    }

    public void definirCliente(Cliente cliente){ this.cliente = cliente; }

    public void definirValorTotal(float valor){
        this.valorTotal = valor;
    }

    public Cliente obterCliente(){ return cliente; }

    public String obterCodigo() {
        return codigo;
    }

    public ZonedDateTime obterData(){
        return data;
    }

    public List<Item> obterItens(){ return itens; }

    public float obterValorTotal(){ return valorTotal; }

    public void adicionarItem(Item item){ itens.add(item); }
}
