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
    private List<Cliente> compradores = new ArrayList<>();
    private List<Cliente> naoCompradores = new ArrayList<>();

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

    public void adicionarComprador(Cliente cliente) {
        compradores.add(cliente);
    }

    public void adicionarNaoComprador(Cliente cliente) {
        naoCompradores.add(cliente);
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

    public List<Cliente> obterCompradores() {
        return compradores;
    }

    public List<Cliente> obterNaoCompradores() {
        return naoCompradores;
    }

    public float obterSSC() {

        float SSC = 0.0f;

        for (Cliente b : compradores) {
            for (Cliente c : compradores) {
                if (compradores.indexOf(c) <= compradores.indexOf(b)) continue;

                SSC += b.similaridade(c);
            }
        }

        return SSC;
    }

    public float obterSSN() {

        float SSN = 0.0f;

        for (Cliente b : naoCompradores) {
            for (Cliente c : naoCompradores) {
                if (naoCompradores.indexOf(c) <= naoCompradores.indexOf(b)) continue;

                SSN += b.similaridade(c);
            }
        }

        return SSN;
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
