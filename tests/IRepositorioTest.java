import org.junit.jupiter.api.Test;

class IRepositorioTest {

    @Test
    void atualizar() {

        IRepositorio rep = new IRepositorio();

        rep.carregarTodosClientes();

        Cliente cliente = rep.obterTodosClientes().get(0);

        cliente.definirNome("Elton");

        rep.atualizar(cliente);

        return;
    }

    @Test
    void obterTodosClientes() {

        IRepositorio rep = new IRepositorio();

        rep.carregarTodosClientes();

        Cliente cliente = rep.obterTodosClientes().get(0);

        cliente.definirNome("Paula");

        return;
    }

    @Test
    void remover() {

        IRepositorio rep = new IRepositorio();

        rep.carregarTodosClientes();

        Cliente cliente = rep.obterTodosClientes().get(0);

        rep.remover(cliente.obterID());

        return;
    }
}