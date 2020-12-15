package hibernate.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.*;
import java.util.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "address", propOrder = {
        "name",
        "surname",
        "age",
        "gender",
        "salary",
        "favGenre",
        "address",
        "movies"
})





@Entity(name = "Actors")
@Table(name = "actors")


@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class,
        property="idA", scope=Actors.class)


public class Actors {

    @Id
    @SequenceGenerator(name="generator")
    @GeneratedValue
    @Column(name = "id")
    @XmlAttribute(name = "id", required = true)
    @JsonIgnore
    private int id;


    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String surname;

    @Column(nullable = false)
    Integer age;


    @Column
    String gender;

    @Column(nullable = false)
    Integer salary;

    @Column
    String favGenre;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="address_id", referencedColumnName = "id")
    Address address;

    @OneToMany(mappedBy="actorId")
    private Set<MovieCast> movies;








    Set<MovieCast> getMovies() {
        return movies;
    }

    public void setMovies(Set<MovieCast> movies) {
        this.movies=movies;
    }

    String getFavGenre() {
        return favGenre;
    }

    public void setFavGenre(String favGenre) {
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

    public void setSurname(String surname) {
        this.surname = surname;
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