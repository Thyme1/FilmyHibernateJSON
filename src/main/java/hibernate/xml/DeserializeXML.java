package hibernate.xml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;


    public class DeserializeXML {

        public static void deserializeXML(Object actor1) {
            ObjectMapper xmlMapper = new XmlMapper();            try {
                Object deserializedEmployee=xmlMapper.readValue(
                        new File("result.xml" ), Object.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

