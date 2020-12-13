//package hibernate;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import hibernate.model.Actors;
//import hibernate.model.Address;
//import hibernate.model.Movie;
////import hibernate.model.MovieCast;
//import hibernate.model.MovieCast;
//import hibernate.queries.Queries;
//import org.joda.time.DateTime;
//import javax.persistence.*;
//import java.io.File;
//import java.util.List;
//import java.util.Random;
//import java.util.Set;
//import java.util.stream.IntStream;
//
//import static hibernate.json.SerializeJSON.serialize;
//import static hibernate.xml.SerializeXML.serializeXML;
//
//
//public class Manager {
//
//    public static void main(String[] args) {
//
//        System.out.println("Start");
//
//        EntityManager entityManager = null;
//
//        EntityManagerFactory entityManagerFactory = null;
//
//        try {
//
//            // FACTORY NAME HAS TO MATCHED THE ONE FROM PERSISTED.XML !!!
//            entityManagerFactory = Persistence.createEntityManagerFactory("hibernate-dynamic");
//
//            entityManager = entityManagerFactory.createEntityManager();
//
//            entityManager.getTransaction().begin();
//
//
//
//
//            Actors actor1 = new Actors();
//            actor1.setName("Johnny");
//            actor1.setSurname("Depp");
//            actor1.setAge(35);
//            actor1.setSalary(12332);
//            actor1.setGender("male");
//
//            Actors actor2 = new Actors();
//            actor2.setName("Morgan");
//            actor2.setSurname("Freeman");
//            actor2.setAge(56);
//            actor2.setSalary(1142352);
//            actor2.setGender("male");
//
//            Address address = new Address();
//            address.setCity("Poznan");
//            address.setStreet("poznanska");
//            address.setNr("1");
//            address.setPostcode("99090");
//
//            actor1.setAddress(address);
//
//
//            Movie movie = new Movie();
//            movie.setTitle("Pirates of the Caribbean");
//            movie.setLanguage("English");
//            movie.setReleaseCountry("USA");
//            movie.setTime("134");
//
//            MovieCast cast = new MovieCast();
//
//
//
//            entityManager.persist(movie);
//            entityManager.persist(actor1);
//            entityManager.persist(actor2);
//
//
//            getActorByName(entityManager);
//
//
//
//
//
//            entityManager.close();
//
//        } catch (Throwable ex) {
//            // Make sure you log the exception, as it might be swallowed
//            System.err.println("Initial SessionFactory creation failed." + ex);
//        } finally {
//            entityManagerFactory.close();
//        }
//
//    }
//
//    public static void getActorByName(EntityManager entityManager)  {
//        List<Actors> actors = new Queries(entityManager).getActorBySurname("Depp");
//        for (int i=0;i < actors.size() ; i++){
//        System.out.println("I got a person " + actors.get(i).getName());}
//    }
//
//
//
//}
