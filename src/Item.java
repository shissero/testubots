import java.util.ArrayList;
import java.util.List;

public class Item {

    private String codigo;
    private String produto;
    private String variedade;
    private String pais;
    private String categoria;
    private int safra;
    private float preco;
    private List<Cliente> compradores = new ArrayList<>();
    private List<Cliente> naoCompradores = new ArrayList<>();


    public void definirCodigo(String codigo){ this.codigo = codigo; }

    public void definirProduto(String produto){
        this.produto = produto;
    }

    public void definirVariedade(String variedade){
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

    public void adicionarComprador(Cliente cliente){ compradores.add(cliente); }

    public void adicionarNaoComprador(Cliente cliente){ naoCompradores.add(cliente); }

    public String obterProduto() { return produto; }

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

    public List<Cliente> obterCompradores(){ return compradores; }

    public List<Cliente> obterNaoCompradores(){ return naoCompradores; }

    public float obterSSC(){

        float SSC = 0.0f;

        for(Cliente b : compradores){
            for(Cliente c : compradores){
                if(compradores.indexOf(c) <= compradores.indexOf(b)) continue;

                SSC += b.similaridade(c);
            }
        }

        return SSC;
    }

    public float obterSSN(){

        float SSN = 0.0f;

        for(Cliente b : naoCompradores){
            for(Cliente c : naoCompradores){
                if(naoCompradores.indexOf(c) <= naoCompradores.indexOf(b)) continue;

                SSN += b.similaridade(c);
            }
        }

        return SSN;
    }

    public boolean equals(Item item){
        return this.produto.equals(item.produto) &&
                this.variedade.equals(item.variedade) &&
                this.pais.equals(item.pais) &&
                this.categoria.equals(item.categoria) &&
                this.safra == item.safra;
    }
}
