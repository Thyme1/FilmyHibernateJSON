package hibernate;

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

        EntityManager entityManager=null;
        EntityManagerFactory entityManagerFactory=null;

        try {

            // FACTORY NAME HAS TO MATCHED THE ONE FROM PERSISTED.XML !!!
            entityManagerFactory=Persistence.createEntityManagerFactory("hibernate-dynamic");

            entityManager=entityManagerFactory.createEntityManager();


            entityManager.getTransaction().begin();


            List<Actors> actorsList=objectMapper.readValue(new File("src/main/resources/jsonR/actors.json"), objectMapper.getTypeFactory().constructCollectionType(List.class, Actors.class));
            List<Address> addressList=objectMapper.readValue(new File("src/main/resources/jsonR/addresses.json"), objectMapper.getTypeFactory().constructCollectionType(List.class, Address.class));
            List<Director> directorsList=objectMapper.readValue(new File("src/main/resources/jsonR/directors.json"), objectMapper.getTypeFactory().constructCollectionType(List.class, Director.class));
            List<Genres> genresList=objectMapper.readValue(new File("src/main/resources/jsonR/genres.json"), objectMapper.getTypeFactory().constructCollectionType(List.class, Genres.class));
            List<Movie> moviesList=objectMapper.readValue(new File("src/main/resources/jsonR/movies.json"), objectMapper.getTypeFactory().constructCollectionType(List.class, Movie.class));
            List<MovieCast> movieCastList=objectMapper.readValue(new File("src/main/resources/jsonR/cast.json"), objectMapper.getTypeFactory().constructCollectionType(List.class, MovieCast.class));


            for (MovieCast movieCast : movieCastList) {
                entityManager.persist(movieCast);
                System.out.println(movieCast);
            }

            OUTER_LOOP2:
            for (Actors actors : actorsList) {
                for (MovieCast movieCast : movieCastList) {
                    if (actors.getId().equals(movieCast.getActorId().getId())) {
                        continue OUTER_LOOP2;
                    }
                }
                entityManager.persist(actors);
                System.out.println(actors);
            }

            OUTER_LOOP5:
            for (Movie movie : moviesList) {
                for (MovieCast movieCast : movieCastList) {
                    if (movie.getId().equals(movieCast.getMovieId().getId())) {
                        continue OUTER_LOOP5;
                    }
                }
                entityManager.persist(movie);
                System.out.println(movie);
            }

            OUTER_LOOP5D:
            for (Director director : directorsList) {
                for (int j=0; j < moviesList.size(); j++) {
                    if (director.getAddress().getId().equals(moviesList.get(j).getDirector().getAddress().getId())) {
                        continue OUTER_LOOP5D;
                    }
                    for (Movie movie : moviesList) {
                        if (director.getId().equals(movie.getDirector().getId())) {
                            continue OUTER_LOOP5D;
                        }
                    }
                    for (MovieCast movieCast : movieCastList) {
                        if (director.getId().equals(movieCast.getMovie().getDirector().getId())) {
                            continue OUTER_LOOP5D;
                        }
                    }
                    entityManager.persist(director);
                    System.out.println(director);
                }
            }


            OUTER_LOOP:
            for (Address address : addressList) {
                for (MovieCast movieCast : movieCastList) {
                    if (address.getId().equals(movieCast.getActorId().getAddress().getId())) {
                        continue OUTER_LOOP;
                    }
                }
                for (Actors actors : actorsList) {
                    if (address.getId().equals(actors.getAddress().getId())) {
                        continue OUTER_LOOP;
                    }
                    for (Director director : directorsList) {
                        if (address.getId().equals(director.getAddress().getId())) {
                            continue OUTER_LOOP;
                        }
                    }
                }

                entityManager.persist(address);
                System.out.println(address);
            }

            for (Genres genres : genresList) {

                entityManager.persist(genres);
                System.out.println(genres);
            }


            //READ FROM DATABASE AND CREATE JSON
            List<Actors> readActors=null;
            readActors=entityManager.createQuery("SELECT  a FROM Actors a", Actors.class).getResultList();
            serialize(readActors, "actors", "src/main/resources/jsonR/jsonFromBase/");

            List<Address> readAddress=null;
            readAddress=entityManager.createQuery("SELECT  a FROM Address a", Address.class).getResultList();
            serialize(readAddress, "addresses", "src/main/resources/jsonR/jsonFromBase/");

            List<Director> readDirector=null;
            readDirector=entityManager.createQuery("SELECT  a FROM Director a", Director.class).getResultList();
            serialize(readDirector, "directors", "src/main/resources/jsonR/jsonFromBase/");

            List<Genres> readGenres=null;
            readGenres=entityManager.createQuery("SELECT  a FROM Genres a", Genres.class).getResultList();
            serialize(readGenres, "genres", "src/main/resources/jsonR/jsonFromBase/");

            List<Movie> readMovies=null;
            readMovies=entityManager.createQuery("SELECT  a FROM Movie a", Movie.class).getResultList();
            serialize(readMovies, "movie", "src/main/resources/jsonR/jsonFromBase/");

            List<MovieCast> readMovieCast=null;
            readMovieCast=entityManager.createQuery("SELECT  a FROM MovieCast a", MovieCast.class).getResultList();
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
