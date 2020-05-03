package annuaire.services;

import annuaire.model.Person;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface IPersonDAO extends CrudRepository<Person, Long> {
    // Récupérer depuis l'id
    Person findById(long id);

    // Récupérer depuis le nom
    List<Person> findByFirstNameContains(String name);

    // Récupérer depuis le nom
    List<Person> findByLastNameContains(String name);

    // Récupérer depuis le nom
    List<Person> findByFirstNameContainsAndClassGroup_Id(String name, long id);

    // Récupérer depuis le nom
    List<Person> findByLastNameContainsAndClassGroup_Id(String name, long id);

    //récuperer depuis l'email et le mot de passe
    Person findByEmailAndPassword(String email, String password);

    // récupérer les personnes
    List<Person> findAll();

    // ajoute la personne en base de données
    Person save(Person p);

    @Modifying
    @Query("update Person p set " +
            "p.firstName = ?2, " +
            "p.lastName = ?3," +
            "p.email = ?4," +
            "p.website = ?5," +
            "p.birthday = ?6," +
            "p.password = ?7 " +
            "where p.id = ?1")
    @Transactional
    int updatePerson(long id, String firstname, String lastname, String email, String website, Date birthday, String password);

    // vide la table
    void deleteAll();
}
