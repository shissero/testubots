import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class IRepositorioCompras {

    public void carregarTodasCompras(IRepositorio repClientes, IRepositorioVinhos repVinhos) {

        String arquivo_compras = "res/compras_historico";

        try {

            BufferedReader reader = new BufferedReader(new FileReader(arquivo_compras));

            String compras_json = reader.lines().collect(Collectors.joining("\n"));

            reader.close();

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Compra.class, new JSONDeserializerCompra(repClientes))
                    .registerTypeAdapter(Vinho.class, new JSONDeserializerVinho(repVinhos))
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

    /*public float obterTotalComprasPorCliente(Cliente cliente) {

        return todasAsCompras.stream().filter( x -> x.obterCliente() == cliente).reduce(0.0f, (subtotal, elemento) -> subtotal + elemento.obterValorTotal(), Float::sum);
    }*/


    // Definindo campos
    private List<Compra> todasAsCompras = new ArrayList<>();
}
