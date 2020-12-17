package hibernate.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MovieCast")

public class MovieCast {


    @Column(name = "id")
    @Id
    private Long id;

    @Column(nullable = false)
    String role;

    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name="actorId", nullable=false)
    private Actors actorId;

    @ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name="movieId", nullable=false)
    private Movie movieId;


    public Movie getMovie() {
        return movieId;
    }

    public void setMovie(Movie movie) {
        this.movieId=movie;
    }

    public Actors getActor() {
        return actorId;
    }

    public void setActor(Actors actor) {
        this.actorId=actor;
    }

    public MovieCast() {}
    public Actors getActorId() {
        return actorId;
    }

    public void setActorId(Actors actorId) {
        this.actorId=actorId;
    }

    public Movie getMovieId() {
        return movieId;
    }

    public void setMovieId(Movie movieId) {
        this.movieId=movieId;
    }
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role=role;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }

}