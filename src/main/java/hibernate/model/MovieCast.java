package hibernate.model;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "MovieCast")
public class MovieCast {

    @Id
    @GeneratedValue(generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "author_seq")
    @Column(name = "id")
    private int id;

    @Column(nullable = false)
    Integer actorId;

    @Column(nullable = false)
    Integer movieId;

    @Column(nullable = false)
    String role;

    @ManyToOne
    @JoinColumn(name="actor", nullable=false)
    private Actors actor;



    @ManyToOne
    @JoinColumn(name="movie", nullable=false)
    private Movie movie;




    Movie getMovie() {
        return movie;
    }

    void setMovie(Movie movie) {
        this.movie=movie;
    }

    Actors getActor() {
        return actor;
    }

    void setActor(Actors actor) {
        this.actor=actor;
    }

    public MovieCast() {}


    Integer getActorId() {
        return actorId;
    }

    void setActorId(Integer actorId) {
        this.actorId=actorId;
    }

    Integer getMovieId() {
        return movieId;
    }

    void setMovieId(Integer movieId) {
        this.movieId=movieId;
    }

    String getRole() {
        return role;
    }

    void setRole(String role) {
        this.role=role;
    }










}