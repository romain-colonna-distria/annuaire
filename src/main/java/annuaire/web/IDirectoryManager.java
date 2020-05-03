package annuaire.web;

import annuaire.model.ClassGroup;
import annuaire.model.Person;
import annuaire.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface IDirectoryManager {

    // créer un utilisateur anonyme
    User newUser();

    // identifier un utilisateur
    boolean login(User user, String email, String password);

    // oublier l'utilisateur
    void logout(User user);



    Person findPersonById(long id);

    List<Person> findAllPerson();

    List<Person> findByFirstNameContains(String name);

    List<Person> findByLastNameContains(String name);

    List<Person> findByFirstNameContainsAndClassGroup_Id(String name, long id);

    List<Person> findByLastNameContainsAndClassGroup_Id(String name, long id);

    Person findByEmailAndPassword(String email, String password);

    void savePerson(Person p);


    ClassGroup findGroupById(long id);

    List<ClassGroup> findAllGroup();

    List<ClassGroup> findByNameContains(String name);

    void saveGroup(ClassGroup c);
}