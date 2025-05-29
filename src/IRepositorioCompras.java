import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class IRepositorioCompras {

    public void carregarTodasCompras(IRepositorioClientes repClientes) {

        String arquivo_compras = "res/compras_historico";

        try {

            BufferedReader reader = new BufferedReader(new FileReader(arquivo_compras));

            String compras_json = reader.lines().collect(Collectors.joining("\n"));

            reader.close();

            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, type, jsonDeserializationContext) -> {

                try {
                    return LocalDate.parse(json.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                } catch (DateTimeParseException e) {
                    return LocalDate.parse(json.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                }

            })
                    .registerTypeAdapter(Cliente.class,
                            (JsonDeserializer<Cliente>) (json,
                                                         type,
                                                         jsonDeserializationContext)
                                    -> repClientes.buscarPorCPF(
                                            AdaptadorCPF.removerSeparadores(
                                                    json.getAsJsonPrimitive().getAsString()
                                            )
                                    )
                                    .get())
                    .create();

            TypeToken<List<Compra>> collectionType = new TypeToken<>() {
            };

            todasAsCompras = gson.fromJson(compras_json, collectionType);
        } catch (FileNotFoundException e) {

            System.out.println("Arquivo de clientes não encontrado");

            e.printStackTrace();

            System.exit(1);
        } catch (IOException e) {
        }
    }


    // Definindo campos
    private List<Compra> todasAsCompras = new ArrayList<>();
}
