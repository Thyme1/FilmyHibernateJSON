package hibernate.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "MovieCast")
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class,
        property="refId", scope=MovieCast.class)
public class MovieCast {


    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    Integer actorId;

    @Column(nullable = false)
    Integer movieId;

    @Column(nullable = false)
    String role;

    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name="actor", nullable=false)
    private Actors actor;



    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name="movie", nullable=false)
    private Movie movie;




    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie=movie;
    }

    public Actors getActor() {
        return actor;
    }

    public void setActor(Actors actor) {
        this.actor=actor;
    }

    public MovieCast() {}


    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId=actorId;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId=movieId;
    }

    public String getRole() {
        return role;
    }

   public void setRole(String role) {
        this.role=role;
    }










}