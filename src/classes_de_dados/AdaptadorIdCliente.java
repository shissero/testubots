package classes_de_dados;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.UUID;

public class AdaptadorIdCliente extends TypeAdapter<UUID> {
    @Override
    public void write(JsonWriter jsonWriter, UUID uuid) throws IOException {

    }

    @Override
    public UUID read(JsonReader jsonReader) throws IOException {

        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }

        long leastSignificant = jsonReader.nextInt();
        long mostSignificant = 0L;

        return new UUID(mostSignificant, leastSignificant);
    }
}
