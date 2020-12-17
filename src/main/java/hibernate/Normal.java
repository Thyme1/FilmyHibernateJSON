package hibernate;

import hibernate.model.*;
import hibernate.queries.Queries;
import org.joda.time.DateTime;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static hibernate.json.SerializeJSON.serialize;
import static hibernate.xml.SerializeXML.serializexml;
import hibernate.model.Actors.*;

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
            address1.setHousenr("12");
            address1.setId(1L);



            Address address2 = new Address();
            address2.setCity("Nowy Jork");
            address2.setStreet("Oak");
            address2.setNr("12");
            address2.setPostcode("56789");
            address2.setHousenr("122");
            address2.setId(2L);

            Address address3 = new Address();
            address3.setCity("Los Angeles");
            address3.setStreet("Angel");
            address3.setNr("27");
            address3.setPostcode("34561");
            address1.setHousenr("14");
            address3.setId(3L);

            Address address4 = new Address();
            address4.setCity("Las Vegas");
            address4.setStreet("Sesame");
            address4.setNr("213");
            address4.setPostcode("22222");
            address1.setHousenr("16");
            address4.setId(4L);

            Address address5 = new Address();
            address5.setCity("Warszawa");
            address5.setStreet("Cisowa");
            address5.setNr("11");
            address5.setHousenr("17");
            address5.setPostcode("11111");
            address5.setId(5L);


            Address address6 = new Address();
            address6.setCity("Toronto");
            address6.setStreet("Double");
            address6.setNr("17");
            address6.setHousenr("12");
            address6.setPostcode("12122");
            address6.setId(6L);

            //TWORZYMY AKTOROW
            Actors actor1 = new Actors();
            actor1.setName("Johnny");
            actor1.setSurname("Depp");
            actor1.setAge(35);
            actor1.setSalary(231);
            actor1.setGender("male");
            actor1.setAddress(address2);
            actor1.setId(1L);



            Actors actor2 = new Actors();
            actor2.setName("Morgan");
            actor2.setSurname("Freeman");
            actor2.setAge(56);
            actor2.setSalary(1142);
            actor2.setGender("male");
            actor2.setAddress(address1);
            actor2.setId(2L);

            Actors actor3 = new Actors();
            actor3.setName("Brad");
            actor3.setSurname("Pitt");
            actor3.setAge(35);
            actor3.setSalary(1234);
            actor3.setGender("male");
            actor3.setFavGenre("comedy");
            actor3.setAddress(address3);
            actor3.setId(3L);

            Actors actor4 = new Actors();
            actor4.setName("Will");
            actor4.setSurname("Smith");
            actor4.setAge(41);
            actor4.setSalary(2345);
            actor4.setGender("male");
            actor4.setFavGenre("drama");
            actor4.setAddress(address4);
            actor4.setId(4L);

            //TWORZYMY FILM
            Movie movie = new Movie();
            movie.setTitle("Pirates of the Caribbean");
            movie.setLanguage("English");
            movie.setReleaseCountry("USA");
            movie.setTime("134");
            movie.setMovieGenre("comedy");
            movie.setReleaseDate(DateTime.now());
            movie.setId(1L);

            Movie movie2 = new Movie();
            movie2.setTitle("Mank");
            movie2.setLanguage("English");
            movie2.setReleaseCountry("USA");
            movie2.setTime("134");
            movie2.setMovieGenre("drama");
            movie2.setReleaseDate(DateTime.parse("2003-05-20"));
            movie2.setId(2L);

            //TWORZYMY REZYSEROW
            Director director = new Director();
            director.setName("Martin");
            director.setSurname("Scorsese");
            director.setId(1L);
            director.setAddress(address5);

            Director director2 = new Director();
            director2.setName("Steven");
            director2.setSurname("Spielberg");
            director2.setId(2L);
            director2.setAddress(address6);


            //TWORZYMY OBSADE
            MovieCast cast = new MovieCast();
            cast.setActor(actor1);
            cast.setMovie(movie);
            cast.setRole("pirat");
            cast.setId(1L);

            Set<MovieCast> castSet1 = new HashSet<>();
            castSet1.add(cast);
            actor1.setMovies(castSet1);


            MovieCast cast2 = new MovieCast();
            cast2.setActor(actor3);
            cast2.setMovie(movie2);
            cast2.setRole("monk");
            cast2.setId(2L);

            //TWORZYMY GATUNEK
            Genres genre = new Genres();
            genre.setName("comedy");
            genre.setId(1L);

            Genres genre2 = new Genres();
            genre2.setName("drama");
            genre2.setId(2L);

            List<Actors> aktorzy= new ArrayList<>();
            aktorzy.add(actor1);
            aktorzy.add(actor2);
            aktorzy.add(actor3);
            aktorzy.add(actor4);

            List<Address> adresy = new ArrayList<>();
            adresy.add(address1);
            adresy.add(address2);
            adresy.add(address3);
            adresy.add(address4);
            adresy.add(address5);
            adresy.add(address6);

            List<Director> rezyserowie = new ArrayList<>();
            rezyserowie.add(director);
            rezyserowie.add(director2);

            List<Genres> gatunki = new ArrayList<>();
            gatunki.add(genre);
            gatunki.add(genre2);

            List<Movie> filmy = new ArrayList<>();
            filmy.add(movie);
            filmy.add(movie2);

            List<MovieCast> obsada = new ArrayList<>();
            obsada.add(cast);
            obsada.add(cast2);

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
