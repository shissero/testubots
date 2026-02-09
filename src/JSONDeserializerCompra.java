import com.google.gson.*;

import java.lang.reflect.Type;
import java.security.InvalidParameterException;
import java.util.Optional;

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

    @Override
    public Compra deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        Compra compra = (new Gson()).fromJson(jsonElement, Compra.class);

        Optional<Cliente> resultado = repositorio.buscarPorCPF(AdaptadorCPF.removerSeparadores(jsonElement.getAsJsonObject().get("cliente").getAsString()));

        if (resultado.isPresent()) {

            //compra.definirCliente(resultado.get());
        }
        else {

            System.err.println("Cliente " + AdaptadorCPF.removerSeparadores(jsonElement.getAsJsonObject().get("cliente").getAsString()) + " não foi encontrado");
            throw new InvalidParameterException();
        }

        return compra;
    }
}
