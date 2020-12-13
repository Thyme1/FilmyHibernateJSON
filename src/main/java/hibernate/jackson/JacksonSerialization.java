package hibernate.jackson;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import hibernate.model.Actors;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JacksonSerialization {

    final static Logger logger = Logger.getLogger(JacksonSerialization.class);

    public static void serializeDemo(ObjectMapper mapper, String fileSuffix) throws IOException {
        //Set mapper to pretty-print
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        //Create objects to serialize
        ModelObjectsCreator objectsCreator = new ModelObjectsCreator();
        Actors actor =(Actors) objectsCreator.getActors();

        //Serialize to file and string
        mapper.writeValue(new File("result." + fileSuffix), actor);
        String jsonString = mapper.writeValueAsString(actor);
        logger.info("Printing serialized original object " + fileSuffix);
        System.out.println(jsonString);

        //Deserialize from file
        Actors deserializedEmployee = mapper.readValue(
                new File("result." + fileSuffix), Actors.class);

        //Give a rise
        deserializedEmployee.setSalary(deserializedEmployee.getSalary() * 2);

        //Serialize back
        mapper.writeValue(new File("result-modified." + fileSuffix), deserializedEmployee);
        String modifiedJsonString = mapper.writeValueAsString(deserializedEmployee);
        logger.info("Printing serialized modified object " + fileSuffix);
        System.out.println(modifiedJsonString);
    }

    public static void serializeListDemo(ObjectMapper mapper, String fileSuffix) throws IOException {
        //Set mapper to pretty-print
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        //Create objects to serialize
        ModelObjectsCreator objectsCreator = new ModelObjectsCreator();
        List<Actors> employee = new ArrayList<>();
        employee.add(objectsCreator.getAct());
        employee.add(objectsCreator.getAct2());

        //Serialize to file and string
        mapper.writeValue(new File("result." + fileSuffix), employee);

        //Deserialize from file
        List<Actors> deserializedEmployee = mapper.readValue(
                new File("result." + fileSuffix), new TypeReference<List<Actors>>() {
                });
    }

    public static void deserializeDemo(ObjectMapper mapper, String fileSuffix) throws IOException {
        //Deserialized employee object from employees.* file in resources
        InputStream employeeIs = JacksonSerialization.class.getClassLoader().
                getResourceAsStream("employee." + fileSuffix);

        //Read value - set class type of serialization
        Actors deserializedEmployee = mapper.readValue(employeeIs, Actors.class);

        //Give eployee big salary
        deserializedEmployee.setSalary(100000);

        String modifiedSerialzied = mapper.writeValueAsString(deserializedEmployee);
        logger.info("Printing modified re-serialized employee to" + fileSuffix);

        System.out.println(modifiedSerialzied);
    }

    public static void main(String[] args) throws IOException {

        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.registerModule(new JodaModule());
        serializeListDemo(jsonMapper, "json");
        serializeDemo(jsonMapper, "json");
        //deserializeDemo(jsonMapper, "json");
        try {
            unmarshall();
        } catch (Exception e) {

        }

    }

    public static void unmarshall() throws JAXBException, IOException {
        File file = new File("classes.xml");

        try (InputStream inputStream = new FileInputStream(file)) {
            Actors a = new Unmarshaller().unmarshallConfiguration(inputStream);
            System.out.println(a.getName() + " " + a.getAddress().getCity());
        }

    }
}