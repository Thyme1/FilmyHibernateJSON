package hibernate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import hibernate.model.*;
import javax.persistence.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import static hibernate.xml.SerializeXML.serializexml;


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


            for (Actors actors : actorsList) {
                entityManager.persist(actors);
                System.out.println(actors);
            }
            for (Address address : addressList) {
                entityManager.persist(address);
                System.out.println(address);
            }
            for (Director director : directorList) {
                entityManager.persist(director);
                System.out.println(director);
            }
            for (Genres genres : genresList) {
                entityManager.persist(genres);
                System.out.println(genres);
            }
            for (Movie movie : movieList) {
                entityManager.persist(movie);
                System.out.println(movie);
            }
            for (MovieCast movieCast : movieCastList) {
                entityManager.persist(movieCast);
                System.out.println(movieCast);
            }

            //READ FROM DATABASE AND CREATE XML
            List<Actors> readActors = null;
            readActors = entityManager.createQuery("SELECT a FROM Actors a", Actors.class).getResultList();
            serializexml(readActors, "actors", "src/main/resources/xmlR/xmlFromBase/");

            List<Address> readAddress = null;
            readAddress = entityManager.createQuery("SELECT a FROM Address a", Address.class).getResultList();
            serializexml(readAddress, "addresses", "src/main/resources/xmlR/xmlFromBase/");

            List<Director> readDirector = null;
            readDirector = entityManager.createQuery("SELECT a FROM Director a", Director.class).getResultList();
            serializexml(readDirector, "directors", "src/main/resources/xmlR/xmlFromBase/");

            List<Genres> readGenres = null;
            readGenres = entityManager.createQuery("SELECT a FROM Genres a", Genres.class).getResultList();
            serializexml(readGenres, "genres", "src/main/resources/xmlR/xmlFromBase/");

            List<Movie> readMovies = null;
            readMovies = entityManager.createQuery("SELECT a FROM Movie a", Movie.class).getResultList();
            serializexml(readMovies, "movie", "src/main/resources/xmlR/xmlFromBase/");

            List<MovieCast> readMovieCast = null;
            readMovieCast = entityManager.createQuery("SELECT a FROM MovieCast a", MovieCast.class).getResultList();
            serializexml(readMovieCast, "movieCast", "src/main/resources/xmlR/xmlFromBase/");

            entityManager.close();

        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
        } finally {
            entityManagerFactory.close();
        }


    }
}
