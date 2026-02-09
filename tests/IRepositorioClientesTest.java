import org.junit.jupiter.api.Test;

class IRepositorioClientesTest {

    @Test
    void atualizar() {

        IRepositorioClientes rep = new IRepositorioClientes();

        rep.carregarTodosClientes();

        Cliente cliente = rep.obterTodosClientes().get(0);

        cliente.definirNome("Elton");

        rep.atualizar(cliente);

        return;
    }

    @Test
    void obterTodosClientes() {

        IRepositorioClientes rep = new IRepositorioClientes();

        rep.carregarTodosClientes();

        Cliente cliente = rep.obterTodosClientes().get(0);

        cliente.definirNome("Paula");

        return;
    }

    @Test
    void remover() {

        IRepositorioClientes rep = new IRepositorioClientes();

        rep.carregarTodosClientes();

        Cliente cliente = rep.obterTodosClientes().get(0);

        rep.remover(cliente.obterID());

        return;
    }
}