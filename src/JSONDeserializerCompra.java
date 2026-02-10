import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Optional;
import java.util.UUID;

/**
 *
 * JSONDeserializer para ser usado com a biblioteca Gson
 *
 */
public class JSONDeserializerCompra implements JsonDeserializer<Compra> {

    /**
     *
     * Um repositório onde procurar os clientes
     *
     */
    private IRepositorio repositorio;

    JSONDeserializerCompra(IRepositorio repositorio) {

        this.repositorio = repositorio;
    }

    private static void logarClienteNaoEncontrado(JsonElement jsonElement){

        System.out.println("Erro ao ler compra");
        System.out.println("Cliente " + AdaptadorCPF.removerSeparadores(jsonElement.getAsJsonObject().get("cliente").getAsString()) + " não foi encontrado");
        System.out.println("Cadastrando cliente como \"Desconhecido\"");

    }

    private void adicionarClienteDesconhecido(String cpf, Compra compra){

        Cliente cliente = new Cliente();

        cliente.definirID(UUID.randomUUID());
        cliente.definirNome("Desconhecido");
        cliente.definirCPF(cpf);

        compra.definirCliente(cliente.obterID());

        repositorio.adicionarCliente(cliente);
    }

    @Override
    public Compra deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        Gson gson = new GsonBuilder().registerTypeAdapter(Vinho.class, new JSONDeserializerVinho(repositorio)).create();

        Compra compra = gson.fromJson(jsonElement, Compra.class);

        String cpfCliente = AdaptadorCPF.removerSeparadores(jsonElement.getAsJsonObject().get("cliente").getAsString());

        Optional<Cliente> resultado = repositorio.buscarClientePorCPF(cpfCliente);

        if (resultado.isPresent()) {

            compra.definirCliente(resultado.get().obterID());
        }
        else {

            logarClienteNaoEncontrado(jsonElement);
            adicionarClienteDesconhecido(cpfCliente, compra);
        }

        return compra;
    }
}
