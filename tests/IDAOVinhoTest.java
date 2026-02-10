import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IDAOVinhoTest {

    String arquivo_clientes = "res/clientes";

    String arquivo_compras = "res/compras_historico";

    @Test
    void salvarTudo() {

        IRepositorio repositorio = IRepositorio.obterInstancia();

        repositorio.carregarClientesJSON(arquivo_clientes);
        repositorio.carregarComprasJSON(arquivo_compras);

        List<Vinho> vinhos = repositorio.obterTodosVinhos();

        IDAOVinho idaoVinho = new IDAOVinho();

        idaoVinho.salvarTudo(vinhos);
    }
}