package hibernate.xml;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import hibernate.model.Actors;

import java.io.File;
import java.io.IOException;

public class SerializeXML {
    public static void serializeXML(Object actor1){
        ObjectMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            xmlMapper.writeValue(new File("actor.xml"), actor1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String jsonString =null;
        try {
            jsonString=xmlMapper.writeValueAsString(actor1);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(jsonString);
    }



}
