package annuaire.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;


@Entity
public class ClassGroup {

    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", length = 100, unique = true)
    private String name;


    @OneToMany(cascade = {CascadeType.REMOVE},
               fetch = FetchType.LAZY, mappedBy = "classGroup")
    private Collection<Person> persons;


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Person> getPersons() {
        return persons;
    }

    public void setPersons(Collection<Person> persons) {
        this.persons = persons;
    }

    public void addPerson(Person person){
        if (persons == null) {
            persons = new HashSet<Person>();
        }
        this.persons.add(person);
        person.setClassGroup(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassGroup that = (ClassGroup) o;
        return id == that.id &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, persons);
    }
}
