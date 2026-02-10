import org.junit.jupiter.api.Test;

import java.util.List;

class IDAOCompraTest {

    String arquivo_clientes = "res/clientes";

    String arquivo_compras = "res/compras_historico";

    @Test
    void salvarTudo() {

        IRepositorio repositorio = IRepositorio.obterInstancia();

        repositorio.carregarClientesJSON(arquivo_clientes);
        repositorio.carregarComprasJSON(arquivo_compras);

        List<Compra> compras = repositorio.obterTodasCompras();

        IDAOCompra idaoCompra = new IDAOCompra();

        idaoCompra.salvarTudo(compras);
    }
}