import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

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

    @Test
    void lerTudo() {

        IRepositorio repositorio = IRepositorio.obterInstancia();

        repositorio.carregarClientesJSON(arquivo_clientes);

        List<Cliente> clientesJSON = repositorio.obterTodosClientes();

        List<Cliente> clientesDAO = (new IDAOCliente()).lerTudo();

        Assertions.assertEquals(clientesDAO.size(), clientesJSON.size());

        for(Cliente cliente : clientesDAO){

            Assertions.assertTrue(clientesJSON.stream().anyMatch(el -> Utils.compararCLientes(el, cliente)));
        }
    }
}