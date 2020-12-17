package hibernate.model;

import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Genres")

public class Genres {


    @Column(name="id", nullable=false)
    @Id
    private Long id;

    @Column(nullable=false)
    String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

}