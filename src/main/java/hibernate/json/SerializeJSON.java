package hibernate.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import hibernate.model.Actors;

import java.io.File;
import java.io.IOException;

public class SerializeJSON {
    public static void serialize(Object actor1){
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            mapper.writeValue(new File("actor.json"), actor1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String jsonString =null;
        try {
            jsonString=mapper.writeValueAsString(actor1);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(jsonString);
    }



}
