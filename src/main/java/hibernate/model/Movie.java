package hibernate.model;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import javax.persistence.*;
import com.fasterxml.jackson.datatype.joda.JodaModule;


import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "Movies")

public class Movie {



    @Column(name = "id")
    @Id
    private Long id;

    @Column(nullable = false)
    String title;

    @Column(nullable = false)
    String time;

    @Column(nullable = false)
    String language;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(length = 1000)
    DateTime releaseDate;

    @Column(nullable = false)
    String releaseCountry;

    @Column
    String movieGenre;


    @ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name="dir")

    private Director director;


    @OneToMany(mappedBy="movieId",cascade=CascadeType.ALL)
    private Set<MovieCast> actors;

    Set<MovieCast> getActors() {
        return actors;
    }

    void setActors(Set<MovieCast> actors) {
        this.actors=actors;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(String movieGenre) {
        this.movieGenre=movieGenre;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public DateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(DateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseCountry() {
        return releaseCountry;
    }

    public void setReleaseCountry(String releaseCountry) {
        this.releaseCountry = releaseCountry;
    }

    public Director getDirector(){return director;}

    public void setDirector(Director director) {
        this.director = director;
    }

}