import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

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

    @Test
    void buscarVinhoPorCodigo() {

        String codigo = "3fde36a6-c9a1-4d27-9f0f-7c12ab0d1cdd";

        IRepositorio repositorio = new IRepositorio();

        UUID id = UUID.fromString(codigo);

        Vinho vinho = new Vinho();

        vinho.definirCodigo(id);

        vinho.definirCategoria("Tinto");

        repositorio.adicionarVinho(vinho);

        Optional<Vinho> resultado = repositorio.buscarVinhoPorCodigo(UUID.fromString(codigo));

        return;
    }

    @Test
    void testAtualizarVinho() {

        IRepositorio repositorio = new IRepositorio();

        Vinho vinho1 = new Vinho();

        Vinho vinho2;

        UUID codigo = UUID.randomUUID();

        vinho1.definirCodigo(codigo);

        vinho1.definirVariedade("Cabernet");

        vinho1.definirCategoria("Tinto");

        repositorio.adicionarVinho(vinho1);

        vinho2 = new Vinho(vinho1);

        vinho2.definirVariedade("Rose");

        repositorio.atualizar(vinho2);

        return;
    }
}