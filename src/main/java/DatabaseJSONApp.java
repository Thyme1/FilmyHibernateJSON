import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import hibernate.model.*;


import javax.persistence.*;
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
            List<Actors> actorsList = objectMapper.readValue(new File("src/main/resources/jsonR/actors.json"), new TypeReference<List<Actors>>(){});
            List<Address> addressList = objectMapper.readValue(new File("src/main/resources/jsonR/addresses.json"), new TypeReference<List<Address>>(){});
            List<Director> directorList = objectMapper.readValue(new File("src/main/resources/jsonR/directors.json"), new TypeReference<List<Director>>(){});
            List<Genres> genresList = objectMapper.readValue(new File("src/main/resources/jsonR/genres.json"), new TypeReference<List<Genres>>(){});
            List<Movie> movieList = objectMapper.readValue(new File("src/main/resources/jsonR/movies.json"), new TypeReference<List<Movie>>(){});
            List<MovieCast> movieCastList = objectMapper.readValue(new File("src/main/resources/jsonR/cast.json"), new TypeReference<List<MovieCast>>(){});


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




            //READ FROM DATABASE AND CREATE JSON
            List<Actors> readActors = null;
            readActors = entityManager.createQuery("SELECT a FROM Actors a", Actors.class).getResultList();
            serialize(readActors, "actors", "src/main/resources/jsonR/jsonFromBase/");

            List<Address> readAddress = null;
            readAddress = entityManager.createQuery("SELECT a FROM Address a", Address.class).getResultList();
            serialize(readAddress, "addresses", "src/main/resources/jsonR/jsonFromBase/");

            List<Director> readDirector = null;
            readDirector = entityManager.createQuery("SELECT a FROM Director a", Director.class).getResultList();
            serialize(readDirector, "directors", "src/main/resources/jsonR/jsonFromBase/");

            List<Genres> readGenres = null;
            readGenres = entityManager.createQuery("SELECT a FROM Genres a", Genres.class).getResultList();
            serialize(readGenres, "genres", "src/main/resources/jsonR/jsonFromBase/");

            List<Movie> readMovies = null;
            readMovies = entityManager.createQuery("SELECT a FROM Movie a", Movie.class).getResultList();
            serialize(readMovies, "movie", "src/main/resources/jsonR/jsonFromBase/");

            List<MovieCast> readMovieCast = null;
            readMovieCast = entityManager.createQuery("SELECT a FROM MovieCast a", MovieCast.class).getResultList();
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
