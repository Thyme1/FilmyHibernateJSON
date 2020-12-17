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


            for (int i=0; i < movieCastList.size(); i++) {
                MovieCast movieCast=movieCastList.get(i);
                entityManager.persist(movieCast);
                System.out.println(movieCast);
            }

            OUTER_LOOP2:
            for (int i=0; i < actorsList.size(); i++) {
                Actors actors=actorsList.get(i);
                for (int j=0; j < movieCastList.size(); j++) {
                    if (actors.getId().equals(movieCastList.get(j).getActorId().getId())) {
                        continue OUTER_LOOP2;
                    }
                }
                entityManager.persist(actors);
                System.out.println(actors);
            }

            for (Director director : directorList) {
                entityManager.persist(director);
                System.out.println(director);
            }


            OUTER_LOOP:
            for (int i=0; i < addressList.size(); i++) {
                Address address=addressList.get(i);
                for (int j=0; j < movieCastList.size(); j++) {
                    if (address.getId().equals(movieCastList.get(j).getActorId().getAddress().getId())) {
                        continue OUTER_LOOP;
                    }
                }
                for (int j=0; j < actorsList.size(); j++) {
                    if (address.getId().equals(actorsList.get(j).getAddress().getId())) {
                        continue OUTER_LOOP;
                    }
                    for (int k=0; k < directorList.size(); k++) {
                        if (address.getId().equals(directorList.get(k).getAddress().getId())) {
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
            OUTER_LOOP5:
            for (Movie movie : movieList) {
                for (int j=0; j < movieCastList.size(); j++) {
                    if (movie.getId().equals(movieCastList.get(j).getMovieId().getId())) {
                        continue OUTER_LOOP5;
                    }
                }
                entityManager.persist(movie);
                System.out.println(movie);
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
