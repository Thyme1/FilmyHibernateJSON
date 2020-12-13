package hibernate.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import hibernate.model.Actors;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SerializeJSON {
    public static void serialize(List list, String name) {
        ObjectMapper mapper=new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            mapper.writeValue(new File(name + ".json"), list);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
