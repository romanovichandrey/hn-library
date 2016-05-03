package by.romanovich.it.pojos;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @see java.io.Serializable
 * @author Romanovich Andrei
 * @version 1.0
 */
@Entity
public class Adress implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(
            name = "gen",
            strategy = "foreign",
            parameters = @Parameter(name = "property", value = "user")
    )
    @GeneratedValue(generator = "gen")
    private Long id;

    @Column (name = "F_STREET")
    private String street;

    @Column (name = "F_CITY")
    private String city;

    @Column (name = "F_STATE")
    private String state;

    @Column (name = "F_COUNTRY")
    private String country;

    @OneToOne
    @PrimaryKeyJoinColumn
    private User user;

    public Adress() {
    }

    public Adress(String street, String city, String state, String country) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Adress)) return false;

        Adress adress = (Adress) o;

        if (city != null ? !city.equals(adress.city) : adress.city != null) return false;
        if (country != null ? !country.equals(adress.country) : adress.country != null) return false;
        if (id != null ? !id.equals(adress.id) : adress.id != null) return false;
        if (state != null ? !state.equals(adress.state) : adress.state != null) return false;
        if (street != null ? !street.equals(adress.street) : adress.street != null) return false;
        if (user != null ? !user.equals(adress.user) : adress.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Adress{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", user=" + user +
                '}';
    }
}
