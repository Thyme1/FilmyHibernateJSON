package hibernate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import hibernate.model.*;
import org.hibernate.Criteria;
import org.hibernate.Session;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static hibernate.json.SerializeJSON.serialize;

public class DatabaseJSONApp {

    public static void main(String[] args) throws IOException {

        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.registerModule(new JodaModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        EntityManager entityManager = null;
        EntityManagerFactory entityManagerFactory = null;

        try {

            // FACTORY NAME HAS TO MATCHED THE ONE FROM PERSISTED.XML !!!
            entityManagerFactory = Persistence.createEntityManagerFactory("hibernate-dynamic");

            entityManager = entityManagerFactory.createEntityManager();



            entityManager.getTransaction().begin();


            List<Actors> actorsList = objectMapper.readValue(new File("src/main/resources/jsonR/actors.json"), objectMapper.getTypeFactory().constructCollectionType(List.class, Actors.class));
            List<Address> addressList = objectMapper.readValue(new File("src/main/resources/jsonR/addresses.json"), objectMapper.getTypeFactory().constructCollectionType(List.class, Address.class));
            List<Director> directorsList = objectMapper.readValue(new File("src/main/resources/jsonR/directors.json"), objectMapper.getTypeFactory().constructCollectionType(List.class, Director.class));
            List<Genres> genresList = objectMapper.readValue(new File("src/main/resources/jsonR/genres.json"), objectMapper.getTypeFactory().constructCollectionType(List.class, Genres.class));
            List<Movie> moviesList = objectMapper.readValue(new File("src/main/resources/jsonR/movies.json"), objectMapper.getTypeFactory().constructCollectionType(List.class, Movie.class));
            List<MovieCast> movieCastList = objectMapper.readValue(new File("src/main/resources/jsonR/cast.json"), objectMapper.getTypeFactory().constructCollectionType(List.class, MovieCast.class));



            try {
                for (int i=0; i < actorsList.size(); i++) {
                    Actors actors=actorsList.get(i);
                    entityManager.persist(actors);
                    System.out.println(actors);
                }
                for (int i=0; i < addressList.size(); i++) {
                    Address address=addressList.get(i);
                    entityManager.persist(address);
                    System.out.println(address);
                }
                for (Director director : directorsList) {
                    entityManager.persist(director);
                    System.out.println(director);
                }
                for (Genres genres : genresList) {
                    entityManager.persist(genres);
                    System.out.println(genres);
                }
                for (Movie movie : moviesList) {
                    entityManager.persist(movie);
                    System.out.println(movie);
                }
                for (MovieCast movieCast : movieCastList) {
                    entityManager.persist(movieCast);
                    System.out.println(movieCast);
                }
            }catch(EntityExistsException e){



            }



            //READ FROM DATABASE AND CREATE JSON
            List<Actors> readActors = null;
            readActors = entityManager.createQuery("SELECT  a FROM Actors a", Actors.class).getResultList();
            serialize(readActors, "actors", "src/main/resources/jsonR/jsonFromBase/");

            List<Address> readAddress = null;
            readAddress = entityManager.createQuery("SELECT  a FROM Address a", Address.class).getResultList();
            serialize(readAddress, "addresses", "src/main/resources/jsonR/jsonFromBase/");

            List<Director> readDirector = null;
            readDirector = entityManager.createQuery("SELECT  a FROM Director a", Director.class).getResultList();
            serialize(readDirector, "directors", "src/main/resources/jsonR/jsonFromBase/");

            List<Genres> readGenres = null;
            readGenres = entityManager.createQuery("SELECT  a FROM Genres a", Genres.class).getResultList();
            serialize(readGenres, "genres", "src/main/resources/jsonR/jsonFromBase/");

            List<Movie> readMovies = null;
            readMovies = entityManager.createQuery("SELECT  a FROM Movie a", Movie.class).getResultList();
            serialize(readMovies, "movie", "src/main/resources/jsonR/jsonFromBase/");

            List<MovieCast> readMovieCast = null;
            readMovieCast = entityManager.createQuery("SELECT  a FROM MovieCast a", MovieCast.class).getResultList();
            serialize(readMovieCast, "movieCast", "src/main/resources/jsonR/jsonFromBase/");

            entityManager.close();

        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
        } finally {
            entityManagerFactory.close();
        }


    }
}
