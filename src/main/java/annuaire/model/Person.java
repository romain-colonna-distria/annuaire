package annuaire.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
public class Person {

    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;

    @Column(name = "email", length = 254, nullable = false, unique = true)
    private String email;

    @Column(name = "website", length = 250, nullable = false)
    private String website;

    @Column(name = "birthday", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column(name = "password", length = 50, nullable = false)
    private String password;

    @ManyToOne(optional = true)
    @JoinColumn(name = "classGroup")
    private ClassGroup classGroup;


    public Person(){}

    public Person(Person p){
        this.firstName = p.firstName;
        this.lastName = p.lastName;
        this.email = p.email;
        this.website = p.website;
        this.birthday = p.birthday;
        this.password = p.password;
        this.classGroup = p.classGroup;
    }


    public long getId() {
        return id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ClassGroup getClassGroup() {
        return classGroup;
    }

    public void setClassGroup(ClassGroup classGroup) {
        this.classGroup = classGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id &&
                firstName.equals(person.firstName) &&
                lastName.equals(person.lastName) &&
                email.equals(person.email) &&
                website.equals(person.website) &&
                password.equals(person.password) &&
                classGroup.equals(person.classGroup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, website, birthday, password);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", website='" + website + '\'' +
                ", birthday=" + birthday +
                ", password='" + password + '\'' +
                ", classGroup=" + classGroup.getName() +
                '}';
    }
}
