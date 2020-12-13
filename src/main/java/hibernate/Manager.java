package hibernate;


import hibernate.model.Actors;
import hibernate.model.Movie;
//import hibernate.model.MovieCast;
import hibernate.model.MovieCast;
import hibernate.queries.Queries;
import org.joda.time.DateTime;


import javax.persistence.*;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;


public class Manager {

    public static void main(String[] args) {

        System.out.println("Start");

        EntityManager entityManager = null;

        EntityManagerFactory entityManagerFactory = null;

        try {

            // FACTORY NAME HAS TO MATCHED THE ONE FROM PERSISTED.XML !!!
            entityManagerFactory = Persistence.createEntityManagerFactory("hibernate-dynamic");

            entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();


            Actors actor1 = new Actors();
            actor1.setName("Johnny");
            actor1.setSurname("Depp");
            actor1.setAge(35);
            actor1.setSalary(12332);
            actor1.setGender("male");

            Actors actor2 = new Actors();
            actor2.setName("Morgan");
            actor2.setSurname("Freeman");
            actor2.setAge(56);
            actor2.setSalary(1142352);
            actor2.setGender("male");


            Movie movie = new Movie();
            movie.setTitle("Pirates of the Caribbean");
            movie.setLanguage("English");
            movie.setReleaseCountry("USA");
            movie.setTime("134");

            MovieCast cast = new MovieCast();














            entityManager.persist(movie);
            entityManager.persist(actor1);
            entityManager.persist(actor2);

            System.out.println("TUUUUUUUUUUUUUUUUUUUU\n");
            getActorByName(entityManager);
            System.out.println("TUUUUUUUUUUUUUUUUUUUU\n");




            entityManager.close();

        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
        } finally {
            entityManagerFactory.close();
        }

    }

    public static void getActorByName(EntityManager entityManager)  {
        List<Actors> actors = new Queries(entityManager).getActorBySurname("Depp");
        for (int i=0;i < actors.size() ; i++){
        System.out.println("I got a person " + actors.get(i).getName());}
    }

    public static void changeFirstGuyToNowak(EntityManager entityManager) {

        List<Actors> actors = new Queries(entityManager).getActorBySurname("Depp");

        actors.get(0).setSurname("NowakPRE" + new Random().nextInt());

    }

}
