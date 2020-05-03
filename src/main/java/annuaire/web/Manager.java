package annuaire.web;

import annuaire.model.ClassGroup;
import annuaire.model.Person;
import annuaire.model.User;
import annuaire.services.IClassGroupDAO;
import annuaire.services.IPersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service()
public class Manager implements IDirectoryManager {

    @Autowired
    IPersonDAO daoP;

    @Autowired
    IClassGroupDAO daoC;

    /* ******************************** SESSION ******************************** */
    @Override
    public User newUser() {
        return new User();
    }

    @Override
    public boolean login(User user, String email, String password) {
        Person p = daoP.findByEmailAndPassword(email, password);

        if(p == null) {
            user.setBadPassword(true);
            return false;
        }

        user.setPerson(p);
        user.setConnected(true);
        return true;
    }

    @Override
    public void logout(User user) {
        user.setPerson(null);
        user.setConnected(false);
        user.setBadPassword(false);
    }
    /* ************************************************************************* */



    /* ******************************** PERSON ********************************* */
    @Override
    public Person findPersonById(long id){
        Person person = daoP.findById(id);

        return person;
    }

    @Override
    public List<Person> findAllPerson(){
        ArrayList<Person> persons = (ArrayList<Person>) daoP.findAll();

        return persons;
    }

    @Override
    public List<Person> findByFirstNameContains(String name){
        ArrayList<Person> persons = (ArrayList<Person>) daoP.findByFirstNameContains(name);

        return persons;
    }

    @Override
    public List<Person> findByLastNameContains(String name){
        ArrayList<Person> persons = (ArrayList<Person>) daoP.findByLastNameContains(name);

        return persons;
    }

    @Override
    public List<Person> findByFirstNameContainsAndClassGroup_Id(String name, long id){
        ArrayList<Person> persons = (ArrayList<Person>) daoP.findByFirstNameContainsAndClassGroup_Id(name, id);

        return persons;
    }

    @Override
    public List<Person> findByLastNameContainsAndClassGroup_Id(String name, long id){
        ArrayList<Person> persons = (ArrayList<Person>) daoP.findByLastNameContainsAndClassGroup_Id(name, id);

        return persons;
    }

    @Override
    public Person findByEmailAndPassword(String email, String password){
        Person person = daoP.findByEmailAndPassword(email, password);

        return person;
    }

    @Override
    public void savePerson(Person p) {
        daoP.save(p);
    }
    /* ************************************************************************* */



    /* ********************************* GROUP ********************************* */
    @Override
    public ClassGroup findGroupById(long id){
        ClassGroup classGroup = daoC.findById(id);

        return classGroup;
    }

    @Override
    public List<ClassGroup> findAllGroup(){
        ArrayList<ClassGroup> classGroups = (ArrayList<ClassGroup>) daoC.findAll();

        return classGroups;
    }

    @Override
    public List<ClassGroup> findByNameContains(String name){
        ArrayList<ClassGroup> classGroups = (ArrayList<ClassGroup>) daoC.findByNameContains(name);

        return classGroups;
    }

    @Override
    public void saveGroup(ClassGroup c) {
        daoC.save(c);
    }
    /* ************************************************************************* */
}
