package hibernate.model;

import org.joda.time.DateTime;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Movies")
public class Movie {

    @Id
    @GeneratedValue(generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "author_seq")

    @Column(name = "id")
    private int id;

    @Column(nullable = false)
    String title;

    @Column(nullable = false)
    String time;

    @Column(nullable = false)
    String language;

    @Column(nullable = false)
    DateTime releaseDate;

    @Column(nullable = false)
    String releaseCountry;

    @Column
    String movieGenre;



    @ManyToOne
    @JoinColumn(name="dir")
    private Director director;



    @OneToMany(mappedBy="movie")
    private Set<MovieCast> actors;

    Set<MovieCast> getActors() {
        return actors;
    }

    void setActors(Set<MovieCast> actors) {
        this.actors=actors;
    }

    String getMovieGenre() {
        return movieGenre;
    }

    void setMovieGenre(String movieGenre) {
        this.movieGenre=movieGenre;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
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