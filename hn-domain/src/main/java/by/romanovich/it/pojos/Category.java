package by.romanovich.it.pojos;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

/**
 * @see by.romanovich.it.pojos.SuperEntity
 * @author Romanovich Andrei
 * @version 1.0
 */
@Entity
public class Category extends SuperEntity {

    private static final Long serialVersionUID = 1L;

    @Column (name = "F_NAME")
    private String name;

    @OneToMany (mappedBy = "category")
    private Set<Book> books;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(o instanceof Category)) return false;
        if (!super.equals(o)) return false;

        Category category = (Category) o;

        if (books != null ? !books.equals(category.books) : category.books != null) return false;
        if (name != null ? !name.equals(category.name) : category.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);

        return result;
    }

}
