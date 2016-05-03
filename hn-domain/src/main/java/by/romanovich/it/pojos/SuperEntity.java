package by.romanovich.it.pojos;

import javax.persistence.*;
import java.io.Serializable;

/**
 * This is superclass for pojos
 * @see java.io.Serializable
 * @author Romanovich Andrey
 * @version 1.0
 */
@MappedSuperclass
@SequenceGenerator(name = "PK")
public class SuperEntity implements Serializable {

    private static final long serialVersionUID = 4L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "PK")
    private long id;

    public SuperEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SuperEntity)) return false;

        SuperEntity that = (SuperEntity) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "SuperIntity{" +
                "id=" + id +
                '}';
    }
}
