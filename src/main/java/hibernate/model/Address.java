package hibernate.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "address", propOrder = {
        "idAdd",
        "street",
        "city",
        "nr",
        "housenr",
        "postcode"
})

@Entity
@Table(name = "ADDRESSES")

public class Address {
    @Id
    @Column(name = "idAdd", nullable=false)
    private Long idAdd;

    @Column(nullable = false)
    String street;

    @Column
    String city;

    @Column(length = 5)
    String nr;

    @Column(length = 5)
    String housenr;

    @Column(length = 5, nullable = false)
    String postcode;

    @JsonProperty("AddressId")
    public Long getId() {
        return idAdd;

    }

    public void setId(Long idAdd) {
        this.idAdd = idAdd;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public String getHousenr() {
        return housenr;
    }

    public void setHousenr(String housenr) {
        this.housenr = housenr;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

}

