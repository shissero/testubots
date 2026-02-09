import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

class IRepositorioTest {

    @Test
    void atualizarCliente() {

        String id = "3fde36a6-c9a1-4d27-9f0f-7c12ab0d1cdd";

        IRepositorio repositorio = new IRepositorio();

        String cpf = "00000000001";

        Cliente cliente = new Cliente();

        cliente.definirID(UUID.fromString(id));

        cliente.definirCPF(cpf);

        repositorio.adicionarCliente(cliente);

        Cliente novo = new Cliente(cliente);

        novo.definirID(UUID.fromString(id));

        novo.definirCPF("00000000002");

        repositorio.atualizar(novo);

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
    void removerCliente() {

        IRepositorio repositorio = new IRepositorio();

        String codigo = "3fde36a6-c9a1-4d27-9f0f-7c12ab0d1cdd";

        Cliente cliente = new Cliente();

        cliente.definirID(UUID.fromString(codigo));

        repositorio.adicionarCliente(cliente);

        repositorio.removerCliente(UUID.fromString(codigo));

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

    @Test
    void removerVinho() {

        String codigo = "3fde36a6-c9a1-4d27-9f0f-7c12ab0d1cdd";

        IRepositorio repositorio = new IRepositorio();

        Vinho vinho1 = new Vinho();

        vinho1.definirCodigo(UUID.fromString(codigo));

        vinho1.definirVariedade("Cabernet");

        vinho1.definirCategoria("Tinto");

        repositorio.adicionarVinho(vinho1);

        repositorio.removerVinho(UUID.fromString(codigo));

        return;
    }

    @Test
    void buscarClientePorCPF() {

        IRepositorio repositorio = new IRepositorio();

        String cpf1 = "00000000001";

        String cpf2 = "00000000001";

        Cliente cliente = new Cliente();

        cliente.definirCPF(cpf1);

        repositorio.adicionarCliente(cliente);

        Optional<Cliente> resultado = repositorio.buscarClientePorCPF(cpf2);

        return;
    }

    @Test
    void buscarClientePorId() {

        IRepositorio repositorio = new IRepositorio();

        String codigo = "3fde36a6-c9a1-4d27-9f0f-7c12ab0d1cdd";

        Cliente cliente = new Cliente();

        cliente.definirID(UUID.fromString(codigo));

        repositorio.adicionarCliente(cliente);

        Optional<Cliente> resultado = repositorio.buscarClientePorId(UUID.fromString(codigo));

        return;
    }
}