import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

// TODO: conferir se todos os identificadores - nomes de classes, funções, etc. - estão em português
public class Utils {

    public static String lerArquivo(String caminho) throws IOException {

        BufferedReader leitor = new BufferedReader(new FileReader(caminho));

        String resultado = leitor.lines().collect(Collectors.joining("\n"));

        leitor.close();

        return resultado;
    }

    /*public Vinho sugerirVinho() {

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
    }*/
}
