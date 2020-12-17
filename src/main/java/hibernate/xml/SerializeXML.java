package hibernate.xml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class SerializeXML {
    public static void serializexml(List list, String name, String path) {
        ObjectMapper mapper=new XmlMapper();
        mapper.registerModule(new JodaModule());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            mapper.writeValue(new File(path + name + ".xml"), list);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}



