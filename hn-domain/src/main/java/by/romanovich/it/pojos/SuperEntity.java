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
public class SuperEntity implements Serializable {

    private static final long serialVersionUID = 4L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public SuperEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SuperEntity)) return false;

        SuperEntity that = (SuperEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "SuperEntity{" +
                "id=" + id +
                '}';
    }
}
