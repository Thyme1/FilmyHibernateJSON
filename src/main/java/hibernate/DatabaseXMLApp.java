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

        EntityManager entityManager=null;
        EntityManagerFactory entityManagerFactory=null;

        try {
            // FACTORY NAME HAS TO MATCHED THE ONE FROM PERSISTED.XML !!!
            entityManagerFactory=Persistence.createEntityManagerFactory("hibernate-dynamic");
            entityManager=entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();
            List<Actors> actorsList=objectMapper.readValue(new File("src/main/resources/xmlR/actors.xml"), new TypeReference<List<Actors>>() {
            });
            List<Address> addressList=objectMapper.readValue(new File("src/main/resources/xmlR/addresses.xml"), new TypeReference<List<Address>>() {
            });
            List<Director> directorsList=objectMapper.readValue(new File("src/main/resources/xmlR/directors.xml"), new TypeReference<List<Director>>() {
            });
            List<Genres> genresList=objectMapper.readValue(new File("src/main/resources/xmlR/genres.xml"), new TypeReference<List<Genres>>() {
            });
            List<Movie> moviesList=objectMapper.readValue(new File("src/main/resources/xmlR/movies.xml"), new TypeReference<List<Movie>>() {
            });
            List<MovieCast> movieCastList=objectMapper.readValue(new File("src/main/resources/xmlR/cast.xml"), new TypeReference<List<MovieCast>>() {
            });


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

            //READ FROM DATABASE AND CREATE XML
            List<Actors> readActors=null;
            readActors=entityManager.createQuery("SELECT a FROM Actors a", Actors.class).getResultList();
            serializexml(readActors, "actors", "src/main/resources/xmlR/xmlFromBase/");

            List<Address> readAddress=null;
            readAddress=entityManager.createQuery("SELECT a FROM Address a", Address.class).getResultList();
            serializexml(readAddress, "addresses", "src/main/resources/xmlR/xmlFromBase/");

            List<Director> readDirector=null;
            readDirector=entityManager.createQuery("SELECT a FROM Director a", Director.class).getResultList();
            serializexml(readDirector, "directors", "src/main/resources/xmlR/xmlFromBase/");

            List<Genres> readGenres=null;
            readGenres=entityManager.createQuery("SELECT a FROM Genres a", Genres.class).getResultList();
            serializexml(readGenres, "genres", "src/main/resources/xmlR/xmlFromBase/");

            List<Movie> readMovies=null;
            readMovies=entityManager.createQuery("SELECT a FROM Movie a", Movie.class).getResultList();
            serializexml(readMovies, "movie", "src/main/resources/xmlR/xmlFromBase/");

            List<MovieCast> readMovieCast=null;
            readMovieCast=entityManager.createQuery("SELECT a FROM MovieCast a", MovieCast.class).getResultList();
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
