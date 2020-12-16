package hibernate.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "MovieCast")
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class,
        property="refId", scope=MovieCast.class)
public class MovieCast {


    @Column(name = "id")
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(nullable = false)
    String role;

    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name="actorId", nullable=false)
    @JsonBackReference(value="mvCast")
    @Fetch(FetchMode.SELECT)

    private Actors actorId;



    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name="movieId", nullable=false)
    @JsonBackReference(value="act")
    @Fetch (FetchMode.SELECT)
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

    public String getRole() {
        return role;
    }

   public void setRole(String role) {
        this.role=role;
    }










}