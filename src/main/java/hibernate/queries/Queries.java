package hibernate.queries;

import hibernate.model.*;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class Queries {

    EntityManager entityManager;

    public Queries(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Actors> getActorBySurname(String name) {
        TypedQuery<Actors> query = entityManager.createQuery(
                "SELECT c FROM Actors c WHERE c.surname LIKE :name", Actors.class);
        return query.setParameter("name", "%" + name + "%").getResultList();
    }

    public List<Actors> getAllActorsByPage(int pagenr) {
        //calculate total number
        Query queryTotal = entityManager.createQuery
                ("Select count(a) from Actors a");
        long countResult = (long)queryTotal.getSingleResult();

        //create query
        Query query = entityManager.createQuery("Select a FROM Actors a");
        //set pageSize
        int pageSize = 10;
        //calculate number of pages
        int pageNumber = (int) ((countResult / pageSize) + 1);

        if (pagenr > pageNumber) pagenr = pageNumber;
        query.setFirstResult((pagenr-1) * pageSize);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }

    public List<Movie> getMoviesByGenre(String name) {
        TypedQuery<Movie> query = entityManager.createQuery(
                "SELECT c FROM Movie c WHERE c.movieGenre LIKE :name", Movie.class);
        return query.setParameter("name", "%" + name + "%").getResultList();

    }



    public  List<Actors> getActorsByGender(String gender) {
        TypedQuery<Actors> query = entityManager.createQuery(
                "SELECT c FROM Actors c WHERE c.gender LIKE :gender", Actors.class);
        return query.setParameter("gender", "%" + gender + "%").getResultList();

    }

    public  List<Actors> getActorsBySalaryHigherThan(Integer salary) {
        TypedQuery<Actors> query = entityManager.createQuery(
                "SELECT c FROM Actors c WHERE c.salary > :salary", Actors.class);
        return query.setParameter("salary", salary).getResultList();

    }


}