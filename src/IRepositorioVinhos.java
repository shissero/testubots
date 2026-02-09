import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class IRepositorioVinhos {

    // Definindo membros
    List<Vinho> todosVinhos = new ArrayList<>();

    // Definindo métodos

    public Optional<Vinho> buscarVinhoPorVinho(Vinho vinho) {

        return todosVinhos.stream().filter(x -> x.equals(vinho)).findFirst();
    }

    public void adicionarVinho(Vinho vinho) {

        vinho.definirCodigo(UUID.randomUUID());

        todosVinhos.add(vinho);
    }
}
