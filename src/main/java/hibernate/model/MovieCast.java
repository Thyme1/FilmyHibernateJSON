package hibernate.model;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MovieCast")
public class MovieCast {

    @Column(nullable = false)
    Integer actorId;

    @Column(nullable = false)
    Integer movieId;

    @Column(nullable = false)
    String role;

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