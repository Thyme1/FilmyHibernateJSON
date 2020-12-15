package hibernate;

import hibernate.model.*;
import hibernate.queries.Queries;
import org.joda.time.DateTime;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import static hibernate.json.SerializeJSON.serialize;
import static hibernate.xml.SerializeXML.serializexml;


public class Normal {

    public static void main(String[] args) {

        System.out.println("Start");

        EntityManager entityManager = null;

        EntityManagerFactory entityManagerFactory = null;

        try {

            // FACTORY NAME HAS TO MATCHED THE ONE FROM PERSISTED.XML !!!
            entityManagerFactory = Persistence.createEntityManagerFactory("hibernate-dynamic");

            entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();





            //TWORZYMY ADRESY
            Address address1 = new Address();
            address1.setCity("Poznan");
            address1.setStreet("Lipowa");
            address1.setNr("23");
            address1.setPostcode("12345");

            Address address2 = new Address();
            address2.setCity("Nowy Jork");
            address2.setStreet("Oak");
            address2.setNr("12");
            address2.setPostcode("56789");
            //TWORZYMY AKTOROW
            Actors actor1 = new Actors();
            actor1.setName("Johnny");
            actor1.setSurname("Depp");
            actor1.setAge(35);
            actor1.setSalary(231);
            actor1.setGender("male");
            actor1.setAddress(address1);

            Actors actor2 = new Actors();
            actor2.setName("Morgan");
            actor2.setSurname("Freeman");
            actor2.setAge(56);
            actor2.setSalary(1142);
            actor2.setGender("male");

            Actors actor3 = new Actors();
            actor3.setName("Brad");
            actor3.setSurname("Pitt");
            actor3.setAge(35);
            actor3.setSalary(1234);
            actor3.setGender("male");
            actor3.setFavGenre("komedia");
            actor3.setAddress(address2);

            //TWORZYMY FILM
            Movie movie = new Movie();
            movie.setTitle("Pirates of the Caribbean");
            movie.setLanguage("English");
            movie.setReleaseCountry("USA");
            movie.setTime("134");
            movie.setMovieGenre("komedia");
            movie.setReleaseDate(DateTime.now());
            //TWORZYMY REZYSEROW
            Director director = new Director();
            director.setName("Janusz");
            director.setSurname("Tracz");
            director.setAddress(address1);

            //TWORZYMY OBSADE
            MovieCast cast = new MovieCast();
            cast.setActorId(actor1.getId());
            cast.setMovieId(movie.getId());
            cast.setActor(actor1);
            cast.setMovie(movie);
            cast.setRole("pirat");

            //TWORZYMY GATUNEK
            Genres genre = new Genres();
            genre.setName("komedia");

            List<Actors> aktorzy= new ArrayList<>();
            aktorzy.add(actor1);
            aktorzy.add(actor2);
            aktorzy.add(actor3);

            List<Address> adresy = new ArrayList<>();
            adresy.add(address1);
            adresy.add(address2);

            List<Director> rezyserowie = new ArrayList<>();
            rezyserowie.add(director);

            List<Genres> gatunki = new ArrayList<>();
            gatunki.add(genre);

            List<Movie> filmy = new ArrayList<>();
            filmy.add(movie);

            List<MovieCast> obsada = new ArrayList<>();
            obsada.add(cast);

            serialize(aktorzy, "actors", "src/main/resources/jsonR/");
            serialize(adresy, "addresses","src/main/resources/jsonR/" );
            serialize(rezyserowie, "directors","src/main/resources/jsonR/");
            serialize(gatunki, "genres","src/main/resources/jsonR/");
            serialize(filmy, "movies","src/main/resources/jsonR/");
            serialize(obsada, "cast","src/main/resources/jsonR/");

            serializexml(aktorzy, "actors","src/main/resources/xmlR/");
            serializexml(adresy, "addresses","src/main/resources/xmlR/");
            serializexml(rezyserowie, "directors","src/main/resources/xmlR/");
            serializexml(gatunki, "genres","src/main/resources/xmlR/");
            serializexml(filmy, "movies","src/main/resources/xmlR/");
            serializexml(obsada, "cast","src/main/resources/xmlR/");


            for (Actors actors : aktorzy) {
                entityManager.persist(actors);
                System.out.println(actors);
            }
            for (Address address : adresy) {
                entityManager.persist(address);
                System.out.println(address);
            }
            for (Director value : rezyserowie) {
                entityManager.persist(value);
                System.out.println(value);
            }
            for (Genres genres : gatunki) {
                entityManager.persist(genres);
                System.out.println(genres);
            }
            for (Movie value : filmy) {
                entityManager.persist(value);
                System.out.println(value);
            }
            for (MovieCast movieCast : obsada) {
                entityManager.persist(movieCast);
                System.out.println(movieCast);
            }


            //QUERIES
            getActorByName(entityManager, "Depp");
            getMoviesByGenre(entityManager, "komedia");
            getActorsByGender(entityManager, "male");
            getAllActorsByPage(entityManager, 1);
            getActorsWithSalaryHigherThan(entityManager, 100);



            entityManager.close();

        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
        } finally {
            entityManagerFactory.close();
        }

    }

    public static void getActorByName(EntityManager entityManager, String name)  {
        List<Actors> actors = new Queries(entityManager).getActorBySurname(name);
        for (int i=0;i < actors.size() ; i++){
       System.out.println("I got a person " + actors.get(i).getName());}
    }
    public static void getMoviesByGenre(EntityManager entityManager, String name)  {
        List<Movie> movies = new Queries(entityManager).getMoviesByGenre(name);
        for (int i=0; i < movies.size(); i++) {
            Movie movie=movies.get(i);
            System.out.println("I got a movie " + movie.getTitle());
        }
    }

    public static void getActorsByGender(EntityManager entityManager, String gender)  {
        List<Actors> actors2 = new Queries(entityManager).getActorsByGender(gender);
        for (Actors actors : actors2) {
            System.out.println("I got a actor " + actors.getName() +" "+ actors.getSurname());
        }
    }
    public static void getAllActorsByPage(EntityManager entityManager, int n)  {
        List<Actors> actors = new Queries(entityManager).getAllActorsByPage(n);
        for (Actors actor : actors) {
            System.out.println("I got a person " + actor.getName());
        }
    }

    public static void getActorsWithSalaryHigherThan(EntityManager entityManager, Integer salary)  {
        List<Actors> actors2 = new Queries(entityManager).getActorsBySalaryHigherThan(salary);
        for (Actors actors : actors2) {
            System.out.println("I got a actor " + actors.getName() +" "+ actors.getSurname());
        }
    }




}
