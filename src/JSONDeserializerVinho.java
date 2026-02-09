import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Optional;

/**
 *
 * JSONDeserializer para ser usado com a biblioteca Gson
 *
 */

public class JSONDeserializerVinho implements JsonDeserializer<Vinho> {

    /**
     *
     * Um repositório onde procurar os vinhos
     *
     */
    private IRepositorioVinhos repositorio;

    JSONDeserializerVinho(IRepositorioVinhos repositorio) {

        this.repositorio = repositorio;
    }

    /**
     *
     * Procura o vinho no repositório, se o achar, retorna o objeto que está no repositório,
     * se não, cadastra o vinho novo no repositório e o retorna
     *
     */
    @Override
    public Vinho deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        Vinho vinho = (new Gson()).fromJson(jsonElement, Vinho.class);

        Optional<Vinho> resultado = repositorio.buscarVinhoPorVinho(vinho);

        if (resultado.isPresent()) return resultado.get();
        else{

            repositorio.adicionarVinho(vinho);
            return vinho;
        }
    }
}
