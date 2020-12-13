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


class Manager {

    public static void main(String[] args) {

        System.out.println("Start");

        EntityManager entityManager = null;

        EntityManagerFactory entityManagerFactory = null;

        try {

            // FACTORY NAME HAS TO MATCHED THE ONE FROM PERSISTED.XML !!!
            entityManagerFactory = Persistence.createEntityManagerFactory("hibernate-dynamic");

            entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();

            Actors actor = new Actors();
            actor.setName("Johnny");
            actor.setSurname("Depp");
            actor.setAge(35);
            actor.setGender("male");

            Actors actor2 = new Actors();
            actor.setName("Morgan");
            actor.setSurname("Freeman");
            actor.setAge(56);
            actor.setGender("male");


            Movie movie = new Movie();
            movie.setTitle("Pirates of the Caribbean");
            movie.setLanguage("English");
            movie.setReleaseCountry("USA");
            movie.setReleaseDate(DateTime.parse("05092003"));
            movie.setTime("134");

            MovieCast cast = new MovieCast();










            entityManager.persist(movie);
            entityManager.persist(actor);

            //Simple Query
            Actors act = entityManager.find(Actors.class, actor.getId());
            if (act == null) {
                System.out.println(actor.getId() + " not found! ");
            } else {
                System.out.println("Found " + act);
            }

            System.out.println("Employee " + act.getId() + " " + act.getName() + act.getSurname());

            //User-defined query
            getThemAll(entityManager);
            changeFirstGuyToNowak(entityManager);

            //Pageable query
            for (int i = 1; i < 101; i++) {
                entityManager.persist(Actors.copyEmployee(actor));
            }
            entityManager.flush();
            entityManager.getTransaction().commit();

            entityManager.getTransaction().begin();
            Queries query = new Queries(entityManager);
            List<Actors> resultByPage = query.getAllActorsByPage(1);
            resultByPage = query.getAllActorsByPage(2);
            entityManager.getTransaction().commit();



            System.out.println("Done");

            entityManager.close();

        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
        } finally {
            entityManagerFactory.close();
        }

    }

    static void getThemAll(EntityManager entityManager)  {
        Query query = entityManager.createQuery("SELECT k FROM Actors k");

        List<Actors> result = query.getResultList();
        System.out.println("I got a person " + result.get(0).getName());
    }

    static void changeFirstGuyToNowak(EntityManager entityManager) {

        List<Actors> actors = new Queries(entityManager).getActorBySurname("Depp");

        actors.get(0).setSurname("NowakPRE" + new Random().nextInt());

    }

}
