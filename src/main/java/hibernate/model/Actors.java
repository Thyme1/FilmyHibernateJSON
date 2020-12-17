package hibernate.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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





public class Actors {

    @Id
    @Column(name = "idAct", nullable=false)
    @XmlAttribute(name = "idAct", required = true)
    private Long idAct;


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


    @OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.PERSIST)
    @JoinColumn(name="address_id", referencedColumnName = "idAdd")
    Address address;

    @OneToMany(mappedBy="actorId",fetch=FetchType.EAGER)
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

    public Long getId() {
        return idAct;
    }

    public void setId(Long id) {
        this.idAct = id;
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


    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary=salary;
    }

    public void setAddress(Address address1) {
        this.address = address1;
    }

    public Address getAddress() {
        return address;
    }
}