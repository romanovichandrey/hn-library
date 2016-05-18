package by.romanovich.it.pojos;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

/**
 * @see by.romanovich.it.pojos.SuperEntity
 * @author Romanovich Andrei
 * @version 1.0
 */
@Entity
public class Autor extends SuperEntity{

    private static final Long serialVersionUID = 156L;

    @Column (name = "F_FIRSTNAME")
    private String firstname;

    @Column (name = "F_LASTNAME")
    private String lastname;

    @ManyToMany (mappedBy = "autors")
    private Set<Book> books = new HashSet<Book>();

    public Autor() {
    }

    public Autor(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Autor)) return false;
        if (!super.equals(o)) return false;

        Autor autor = (Autor) o;

        if (books != null ? !books.equals(autor.books) : autor.books != null) return false;
        if (firstname != null ? !firstname.equals(autor.firstname) : autor.firstname != null) return false;
        if (lastname != null ? !lastname.equals(autor.lastname) : autor.lastname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);

        return result;
    }

}
