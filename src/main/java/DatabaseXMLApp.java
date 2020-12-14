
import hibernate.model.*;
import hibernate.queries.Queries;
import org.joda.time.DateTime;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import static hibernate.json.SerializeJSON.serialize;
import static hibernate.xml.SerializeXML.serializexml;


public class DatabaseXMLApp {

    public static void main(String[] args) {

        System.out.println("Start");

        EntityManager entityManager = null;

        EntityManagerFactory entityManagerFactory = null;

        try {

            // FACTORY NAME HAS TO MATCHED THE ONE FROM PERSISTED.XML !!!
            entityManagerFactory = Persistence.createEntityManagerFactory("hibernate-dynamic");

            entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();








            List<Actors> aktorzy= new ArrayList<>();


            List<Address> adresy = new ArrayList<>();


            List<Director> rezyserowie = new ArrayList<>();


            List<Genres> gatunki = new ArrayList<>();


            List<Movie> filmy = new ArrayList<>();


            List<MovieCast> obsada = new ArrayList<>();










            entityManager.persist(aktorzy);
            entityManager.persist(adresy);
            entityManager.persist(rezyserowie);
            entityManager.persist(gatunki);
            entityManager.persist(filmy);
            entityManager.persist(obsada);











            entityManager.close();

        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
        } finally {
            entityManagerFactory.close();
        }

    }






}
