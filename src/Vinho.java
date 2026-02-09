import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Esta classe representa os vinhos
 */
public class Vinho {

    // Definindo campos
    private UUID codigo;
    private String produto;
    private String variedade;
    private String pais;
    private String categoria;
    private int safra;
    private float preco;

    // Definindo construtores
    Vinho(){}

    Vinho(Vinho vinho){

        this.codigo = vinho.codigo;
        this.produto = vinho.produto;
        this.variedade = vinho.variedade;
        this.pais = vinho.pais;
        this.categoria = vinho.categoria;
        this.safra = vinho.safra;
        this.preco = vinho.preco;
    }


    // Definindo setters
    public void definirCodigo(UUID codigo) {
        this.codigo = codigo;
    }

    public void definirProduto(String produto) {
        this.produto = produto;
    }

    public void definirVariedade(String variedade) {
        this.variedade = variedade;
    }

    public void definirPais(String pais) {
        this.pais = pais;
    }

    public void definirCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void definirSafra(int safra) {
        this.safra = safra;
    }

    public void definirPreco(float preco) {
        this.preco = preco;
    }


    // Definindo getters
    public UUID obterCodigo() {
        return codigo;
    }

    public String obterProduto() {
        return produto;
    }

    public String obterVariedade() {
        return variedade;
    }

    public String obterPais() {
        return pais;
    }

    public String obterCategoria() {
        return categoria;
    }

    public int obterSafra() {
        return safra;
    }



    float obterPreco() { return preco; }

    public boolean equals(Vinho vinho) { // Esse método é necessário ao extrair os dados dos arquivos json. Nesses arquivos, os vinhos não vêm com uma informação específica que os identifique.

        return this.produto.equals(vinho.produto) && // Se for incluir o código do produto nesta comparação, alter o método IRepositórioVinhos.buscarVinhoPorVinho, pois este espera que o método equals ignore o campo Vinho.código
                this.variedade.equals(vinho.variedade) &&
                this.pais.equals(vinho.pais) &&
                this.categoria.equals(vinho.categoria) &&
                this.safra == vinho.safra;
    }
}
