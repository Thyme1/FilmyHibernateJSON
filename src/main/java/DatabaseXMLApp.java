import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import hibernate.model.*;


import javax.persistence.*;
import java.io.File;
import java.io.IOException;
import java.util.*;





public class DatabaseXMLApp {


    public static void main(String[] args) throws IOException {

        XmlMapper objectMapper=new XmlMapper();
        objectMapper.registerModule(new JodaModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);



        EntityManager entityManager = null;

        EntityManagerFactory entityManagerFactory = null;

        try {

            // FACTORY NAME HAS TO MATCHED THE ONE FROM PERSISTED.XML !!!
            entityManagerFactory = Persistence.createEntityManagerFactory("hibernate-dynamic");

            entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();
            List<Actors> actorsList = objectMapper.readValue(new File("src/main/resources/xmlR/actors.xml"), new TypeReference<List<Actors>>(){});
            List<Address> addressList = objectMapper.readValue(new File("src/main/resources/xmlR/addresses.xml"), new TypeReference<List<Address>>(){});
            List<Director> directorList = objectMapper.readValue(new File("src/main/resources/xmlR/directors.xml"), new TypeReference<List<Director>>(){});
            List<Genres> genresList = objectMapper.readValue(new File("src/main/resources/xmlR/genres.xml"), new TypeReference<List<Genres>>(){});
            List<Movie> movieList = objectMapper.readValue(new File("src/main/resources/xmlR/movies.xml"), new TypeReference<List<Movie>>(){});
            List<MovieCast> movieCastList = objectMapper.readValue(new File("src/main/resources/xmlR/cast.xml"), new TypeReference<List<MovieCast>>(){});


            for(int i=0;i<actorsList.size();i++){
                entityManager.persist(actorsList.get(i));
                System.out.println(actorsList.get(i));
            }
            for(int i=0;i<addressList.size();i++){
                entityManager.persist(addressList.get(i));
                System.out.println(addressList.get(i));
            }
            for(int i=0;i<directorList.size();i++){
                entityManager.persist(directorList.get(i));
                System.out.println(directorList.get(i));
            }
            for(int i=0;i<genresList.size();i++){
                entityManager.persist(genresList.get(i));
                System.out.println(genresList.get(i));
            }
            for(int i=0;i<movieList.size();i++){
                entityManager.persist(movieList.get(i));
                System.out.println(movieList.get(i));
            }
            for(int i=0;i<movieCastList.size();i++){
                entityManager.persist(movieCastList.get(i));
                System.out.println(movieCastList.get(i));
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
