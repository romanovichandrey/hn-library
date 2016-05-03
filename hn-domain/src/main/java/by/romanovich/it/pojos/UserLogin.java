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
public class UserLogin implements Serializable {

    private static final long SerialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "gen",
            strategy = "foreign",
            parameters = @Parameter(name = "property", value = "user")
    )
    private Long id;

    @Column (name = "F_LOGIN")
    private String login;

    @Column (name = "F_PASSWORD")
    private String password;

    @OneToOne
    @PrimaryKeyJoinColumn
    private User user;

    public UserLogin() {
    }

    public UserLogin(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        if (!(o instanceof UserLogin)) return false;

        UserLogin userLogin = (UserLogin) o;

        if (id != null ? !id.equals(userLogin.id) : userLogin.id != null) return false;
        if (login != null ? !login.equals(userLogin.login) : userLogin.login != null) return false;
        if (password != null ? !password.equals(userLogin.password) : userLogin.password != null) return false;
        if (user != null ? !user.equals(userLogin.user) : userLogin.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserLogin{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", user=" + user +
                '}';
    }
}
