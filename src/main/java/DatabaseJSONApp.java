import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import hibernate.model.*;


import javax.persistence.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static hibernate.json.ReadJSONExample.readJSON;



public class DatabaseJSONApp {


    public static void main(String[] args) {

        ObjectMapper objectMapper =new ObjectMapper();
        objectMapper.registerModule(new JodaModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        ClassLoader classLoader = DatabaseJSONApp.class.getClassLoader();

        List<File> files = new ArrayList<>();
        String[] jsonFiles = {"actors", "addresses", "cast", "directors", "genres", "movies"};

        for(int i=0;i<jsonFiles.length;i++){
            files.add(new File(Objects.requireNonNull(classLoader.getResource("jsonR/" + jsonFiles[i] + ".json")).getFile()));

        }

        Actors actor1 = new Actors();
        actor1.setName("Jan");
        actor1.setGender("female");
        actor1.setSurname("Kowalski");
        actor1.setAge(23);
        actor1.setSalary(122);

        List<Actors> actorsList = new ArrayList<>();
        List<Address> addressList = null;
        List<Director> directorList = null;
        List<Genres> genresList = null;
        List<Movie> movieList = null;
        List<MovieCast> movieCastList = null;
        actorsList.add(actor1);

        try {

            actorsList.add(objectMapper.readValue(files.get(0), Actors.class));
        } catch (Exception e) {
            System.out.println("Invalid values in json file");
        }
        EntityManager entityManager = null;

        EntityManagerFactory entityManagerFactory = null;

        try {

            // FACTORY NAME HAS TO MATCHED THE ONE FROM PERSISTED.XML !!!
            entityManagerFactory = Persistence.createEntityManagerFactory("hibernate-dynamic");

            entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();

            entityManager.persist(actorsList.get(0));



            entityManager.close();

        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
        } finally {
            entityManagerFactory.close();
        }




    }
}
