package hibernate.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.IOException;
import java.util.*;

@Entity
@Table(name = "Actors")
public class Actors {

    @Id
    @GeneratedValue(generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "author_seq")
    @Column(name = "id")
    private int id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String surname;

    @Column(nullable = false)
    Integer age;


    @Column(nullable = false)
    String gender;

    @Column(nullable = false)
    Integer salary;

    @Column
    String favGenre;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="add_id", referencedColumnName = "id")
    Address address;

    @OneToMany(mappedBy="actor")
    private Set<MovieCast> movies;




    


    Set<MovieCast> getMovies() {
        return movies;
    }

    void setMovies(Set<MovieCast> movies) {
        this.movies=movies;
    }

    String getFavGenre() {
        return favGenre;
    }

    void setFavGenre(String favGenre) {
        this.favGenre=favGenre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String city) {
        this.surname = city;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }




    public static Actors copyEmployee(Actors actor) {
        Actors person = new Actors();
        person.setAddress(actor.getAddress());
        person.setGender(actor.getGender());
        person.setName(actor.getName());
        person.setFavGenre(actor.getFavGenre());
        person.setAge(actor.getAge());
        person.setSurname(actor.getSurname() + new Random().nextInt());
        return person;
    }


    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary=salary;
    }
}