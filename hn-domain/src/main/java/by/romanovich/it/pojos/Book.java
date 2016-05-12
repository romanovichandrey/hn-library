package by.romanovich.it.pojos;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @see by.romanovich.it.pojos.SuperEntity
 * @author Romanovich Andrei
 * @version 1.0
 */
@Entity
public class Book extends SuperEntity {

    private static final Long serialVersionUID = 1L;

    private String name;

    private String description;

    private String yearPublishing;

    @ManyToOne
    @JoinColumn(name = "F_CATEGORY_ID")
    private Category category;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable (name = "BOOK_AUTOR",
            joinColumns = {@JoinColumn (name = "BOOK_ID")},
            inverseJoinColumns = {@JoinColumn (name = "AUTOR_ID")})
    private Set<Autor> autors = new HashSet<Autor>();

    @ManyToOne
    @JoinColumn (name = "F_USER_ID")
    private User user;

    public Book() {
    }

    public Book(String name, String description, String yearPublishing) {
        this.name = name;
        this.description = description;
        this.yearPublishing = yearPublishing;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYearPublishing() {
        return yearPublishing;
    }

    public void setYearPublishing(String yearPublishing) {
        this.yearPublishing = yearPublishing;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Autor> getAutors() {
        return autors;
    }

    public void setAutors(Set<Autor> autors) {
        this.autors = autors;
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
        if (!(o instanceof Book)) return false;
        if (!super.equals(o)) return false;

        Book book = (Book) o;

        if (autors != null ? !autors.equals(book.autors) : book.autors != null) return false;
        if (category != null ? !category.equals(book.category) : book.category != null) return false;
        if (description != null ? !description.equals(book.description) : book.description != null) return false;
        if (name != null ? !name.equals(book.name) : book.name != null) return false;
        if (user != null ? !user.equals(book.user) : book.user != null) return false;
        if (yearPublishing != null ? !yearPublishing.equals(book.yearPublishing) : book.yearPublishing != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (yearPublishing != null ? yearPublishing.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (autors != null ? autors.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", yearPublishing='" + yearPublishing + '\'' +
                ", category=" + category +
                ", autors=" + autors +
                ", user=" + user +
                '}';
    }
}
