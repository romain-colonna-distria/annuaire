package annuaire.services;


import annuaire.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface IPersonDAO extends CrudRepository<Person, Long> {
    // lire une personne
    Person findById(long id);

    // récupérer les personnes
    Collection<Person> findAll();

    // ajoute la personne en base de données
    Person save(Person p);

    // vide la table
    void deleteAll();

    /*
    // modifier une personne
    void updatePerson(Person p);
    */
}
