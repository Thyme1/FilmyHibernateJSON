import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import hibernate.Normal;
import hibernate.model.*;


import javax.persistence.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static hibernate.json.ReadJSONExample.readJSON;



public class DatabaseJSONApp {


    public static void main(String[] args) throws IOException {

        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.registerModule(new JodaModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        ClassLoader classLoader=DatabaseJSONApp.class.getClassLoader();






        EntityManager entityManager = null;

        EntityManagerFactory entityManagerFactory = null;

        try {

            // FACTORY NAME HAS TO MATCHED THE ONE FROM PERSISTED.XML !!!
            entityManagerFactory = Persistence.createEntityManagerFactory("hibernate-dynamic");

            entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();
            List<Actors> actorsList = objectMapper.readValue(new File("src/main/resources/jsonR/actors.json"), new TypeReference<List<Actors>>(){});


            for(int i=0;i<actorsList.size();i++){
                entityManager.persist(actorsList.get(i));
                System.out.println(actorsList.get(i));
            }




            entityManager.close();

        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
        } finally {
            entityManagerFactory.close();
        }


    }
}
