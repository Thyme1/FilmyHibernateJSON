package hibernate.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class DeserializeJSON {

    public static void deserialize(Object actor1) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Object deserializedEmployee=mapper.readValue(
                     new File("result.json" ), Object.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
