import com.google.gson.annotations.JsonAdapter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Classe de dados para representar compras
 */
public class Compra {

    // Definindo campos
    private UUID codigo;

    @JsonAdapter(JSONDeserializerLocalDate.class)
    private LocalDate data;
    private UUID cliente;

    // Nomear esse item como "vinhos" vai criar problemas na hora da desserialização
    private List<Vinho> itens = new ArrayList<>();

    // Definindo construtores
    Compra(){}

    Compra(Compra compra){

        this.codigo = compra.codigo;
        this.data = compra.data;
        this.cliente = compra.cliente;
        this.itens = new ArrayList<>(compra.itens);
    }



    // Definindo setters
    public void definirCodigo(UUID codigo) {
        this.codigo = codigo;
    }

    public void definirData(String data) {

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // TODO: que tal criar uma classe para cuidar da validação desses dados e passar um objeto LocalDate para o setter?

        this.data = LocalDate.parse(data, formato);
    }

    public void definirCliente(UUID cliente) {
        this.cliente = cliente;
    }

    public void adicionarVinho(Vinho vinho) {
        itens.add(vinho);
    }



    // Definindo getters
    public UUID obterCliente() {
        return cliente;
    }

    public UUID obterCodigo() {
        return codigo;
    }

    public LocalDate obterData() {
        return data;
    }

    public List<Vinho> obterVinhos() {
        return itens;
    }

    public float obterValorTotal() {

        return itens.stream().reduce(0.0f, (subtotal, elemento) -> subtotal + elemento.obterPreco(), Float::sum);
    }
}
