package hibernate.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "Directors")

public class Director {

    @Column(name = "id", nullable=false)
    @Id
    private Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String surname;

    @OneToOne(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
    @JoinColumn(name="add_id", referencedColumnName = "idAdd")
    Address address;

    @OneToMany(mappedBy="director",fetch=FetchType.EAGER)
    private Set<Movie> movies;

    Set<Movie> getMovies() {
        return movies;
    }

    void setMovies(Set<Movie> movies) {
        this.movies=movies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}