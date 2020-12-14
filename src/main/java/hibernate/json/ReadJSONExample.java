package hibernate.json;

import java.io.FileReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJSONExample {

    public static String readJSON(String name) {
        //JSON parser object to parse read file
        JSONParser jsonParser=new JSONParser();

        String toPrint=null;
        try (FileReader reader=new FileReader(name + ".json")) {
            //Read JSON file
            Object obj=jsonParser.parse(reader);


            JSONArray objectsList=(JSONArray) obj;
            ObjectMapper mapper=new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            toPrint=mapper.writeValueAsString(obj);
            System.out.println(toPrint);


        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return toPrint;
    }
}