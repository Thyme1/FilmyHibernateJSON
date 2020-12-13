package hibernate;

import com.fasterxml.jackson.databind.ObjectMapper;
import hibernate.model.*;
//import hibernate.model.MovieCast;
import hibernate.queries.Queries;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import javax.persistence.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

import static hibernate.json.SerializeJSON.serialize;
import static hibernate.xml.SerializeXML.serializexml;
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

            Actors actor1 = new Actors();
            actor1.setName("Johnny");
            actor1.setSurname("Depp");
            actor1.setAge(35);
            actor1.setSalary(231);
            actor1.setGender("male");


            Actors actor2 = new Actors();
            actor2.setName("Morgan");
            actor2.setSurname("Freeman");
            actor2.setAge(56);
            actor2.setSalary(1142);
            actor2.setGender("male");




            Movie movie = new Movie();
            movie.setTitle("Pirates of the Caribbean");
            movie.setLanguage("English");
            movie.setReleaseCountry("USA");
            movie.setTime("134");
            movie.setMovieGenre("komedia");

            Director director = new Director();
            director.setName("Janusz");
            director.setSurname("Tracz");
            director.setAddress(address1);

            MovieCast cast = new MovieCast();
            cast.setActorId(actor1.getId());
            cast.setMovieId(movie.getId());
            cast.setActor(actor1);
            cast.setMovie(movie);
            cast.setRole("pirat");


            Genres genre = new Genres();
            genre.setName("komedia");

            List<Actors> aktorzy= new ArrayList<>();
            aktorzy.add(actor1);
            aktorzy.add(actor2);

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

            serialize(aktorzy, "actors");
            serialize(adresy, "addresses");
            serialize(rezyserowie, "directors");
            serialize(gatunki, "genres");
            serialize(filmy, "movies");
            serialize(obsada, "cast");

            serializexml(aktorzy, "actors");
            serializexml(adresy, "addresses");
            serializexml(rezyserowie, "directors");
            serializexml(gatunki, "genres");
            serializexml(filmy, "movies");
            serializexml(obsada, "cast");







            entityManager.persist(address1);
            entityManager.persist(address2);
            entityManager.persist(actor1);
            entityManager.persist(actor2);
            entityManager.persist(movie);
            entityManager.persist(director);
            entityManager.persist(cast);
            entityManager.persist(genre);


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
