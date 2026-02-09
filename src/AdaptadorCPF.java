import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class AdaptadorCPF extends TypeAdapter<String> {

    private String formato;

    public static String removerSeparadores(String cpf){

        return cpf.replaceAll("\\D", "");
    }

    @Override
    public void write(JsonWriter jsonWriter, String s) throws IOException {

    }

    @Override
    public String read(JsonReader jsonReader) throws IOException {

        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }

        String stringCPF = jsonReader.nextString();

        return removerSeparadores(stringCPF);
    }
}
