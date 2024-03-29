package hibernate.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class SerializeJSON {
    public static void serialize(List list, String name, String path) {
        ObjectMapper mapper=new ObjectMapper();
        mapper.registerModule(new JodaModule());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            mapper.writeValue(new File(path + name + ".json"), list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
