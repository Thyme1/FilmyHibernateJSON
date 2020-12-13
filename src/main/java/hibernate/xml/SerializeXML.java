package hibernate.xml;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import hibernate.model.Actors;

import java.io.File;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import hibernate.model.Actors;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SerializeXML {
    public static void serializexml(List list, String name) {
        ObjectMapper mapper=new XmlMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            mapper.writeValue(new File(name + ".xml"), list);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}



