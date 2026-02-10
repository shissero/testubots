import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IDAOClienteTest {

    String arquivo_clientes = "res/clientes";

    @Test
    void salvarTudo() {

        IRepositorio repositorio = IRepositorio.obterInstancia();

        repositorio.carregarClientesJSON(arquivo_clientes);

        List<Cliente> clientes = repositorio.obterTodosClientes();

        IDAOCliente daoCliente = new IDAOCliente();

        daoCliente.salvarTudo(clientes);
    }
}