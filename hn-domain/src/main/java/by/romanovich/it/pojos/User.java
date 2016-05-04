package by.romanovich.it.pojos;

import javax.persistence.*;

/**
 *@see SuperEntity
 * @author Romanovich Andrei
 * @version 1.0
 */
@Entity
public class User extends SuperEntity {

    private static final long serialVersionUID = 1L;

    @Column (name = "F_FIRSTNAME")
    private String firstname;

    @Column (name = "F_LASTNAME")
    private String lastname;

    @Column (name = "F_TELEPHONE")
    private String telephone;

    @Column (name = "F_EMAIL")
    private String email;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Adress adress;

    @OneToOne (mappedBy = "user", cascade = CascadeType.ALL)
    private UserLogin userLogin;

    public User() {
    }

    public User(String firstname, String lastname, String telephone, String email, Adress adress, UserLogin userLogin) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.telephone = telephone;
        this.email = email;
        this.adress = adress;
        this.userLogin = userLogin;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (adress != null ? !adress.equals(user.adress) : user.adress != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (firstname != null ? !firstname.equals(user.firstname) : user.firstname != null) return false;
        if (lastname != null ? !lastname.equals(user.lastname) : user.lastname != null) return false;
        if (telephone != null ? !telephone.equals(user.telephone) : user.telephone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (adress != null ? adress.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", adress=" + adress +
                '}';
    }
}
