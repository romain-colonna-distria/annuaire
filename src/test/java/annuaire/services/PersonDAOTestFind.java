package annuaire.services;

import annuaire.Starter;
import annuaire.model.ClassGroup;
import annuaire.model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Starter.class)
public class PersonDAOTestFind {

    @Autowired
    IPersonDAO daoP;

    @Autowired
    IClassGroupDAO daoG;

    ClassGroup classGroup;

    static Person p1;
    static Person p2;


    /* ************************************ SETUP ************************************ */
    @BeforeAll
    public static void init(){
        p1 = new Person();
        p1.setFirstName("Rom1");
        p1.setLastName("COLONN1");
        p1.setEmail("colonna.r@gmail.com");
        p1.setWebsite("romain.com");
        p1.setBirthday(new Date());
        p1.setPassword("pass");

        p2 = new Person();
        p2.setFirstName("Rom2");
        p2.setLastName("COLONN2");
        p2.setEmail("colonna.distria.r@gmail.com");
        p2.setWebsite("romain.com");
        p2.setBirthday(new Date());
        p2.setPassword("pass");
    }

    @BeforeEach
    public void setup(){
        classGroup = new ClassGroup();
        classGroup.setName("M1 ILD 2018/2019");

        daoG.save(classGroup);

        classGroup.addPerson(p1);
        classGroup.addPerson(p2);
    }

    @AfterEach
    public void tearDown() {
        daoP.deleteAll();
        daoG.deleteAll();
    }
    /* ******************************************************************************* */



    /* ********************************** TEST FIND ********************************** */
    @Test
    public void testFindById(){
        Person pInitial = new Person(p1);
        System.out.println(pInitial.getId());
        daoP.save(pInitial);

        Person p = daoP.findById(pInitial.getId());

        assertEquals(pInitial, p);
    }

    @Test
    public void testFindByFirstNameContainsAndClassGroupId_ExactFirstName_OneResult(){
        Person p1 = new Person(PersonDAOTestFind.p1);
        daoP.save(p1);

        Person p2 = new Person(PersonDAOTestFind.p2);
        daoP.save(p2);

        ArrayList<Person> result = (ArrayList<Person>) daoP.findByFirstNameContainsAndClassGroup_Id("Rom1", classGroup.getId());

        assertEquals(1, result.size());
    }

    @Test
    public void testFindByFirstNameContainsAndClassGroupId_ExactFirstName_GoodResult(){
        Person p1 = new Person(PersonDAOTestFind.p1);
        daoP.save(p1);

        Person p2 = new Person(PersonDAOTestFind.p2);
        daoP.save(p2);

        ArrayList<Person> result = (ArrayList<Person>) daoP.findByFirstNameContainsAndClassGroup_Id("Rom1", classGroup.getId());

        assertEquals("Rom1", result.get(0).getFirstName());
    }

    @Test
    public void testFindByFirstNameContainsAndClassGroupId_ExactFirstName_MultipleResults(){
        Person p1 = new Person(PersonDAOTestFind.p1);
        p1.setFirstName("Romain");
        daoP.save(p1);

        Person p2 = new Person(PersonDAOTestFind.p2);
        p2.setFirstName("Romain");
        daoP.save(p2);

        ArrayList<Person> result = (ArrayList<Person>) daoP.findByFirstNameContainsAndClassGroup_Id("Romain", classGroup.getId());

        assertEquals(2, result.size());
    }




    @Test
    public void testFindByLastNameContainsAndClassGroupId_ExactLastName_OneResult(){
        Person p1 = new Person(PersonDAOTestFind.p1);
        daoP.save(p1);

        Person p2 = new Person(PersonDAOTestFind.p2);
        daoP.save(p2);

        ArrayList<Person> result = (ArrayList<Person>) daoP.findByLastNameContainsAndClassGroup_Id("COLONN1", classGroup.getId());

        assertEquals(1, result.size());
    }

    @Test
    public void testFindByLastNameContainsAndClassGroupId_ExactLastName_GoodResult(){
        Person p1 = new Person(PersonDAOTestFind.p1);
        daoP.save(p1);

        Person p2 = new Person(PersonDAOTestFind.p2);
        daoP.save(p2);

        ArrayList<Person> result = (ArrayList<Person>) daoP.findByLastNameContainsAndClassGroup_Id("COLONN1", classGroup.getId());

        assertEquals("COLONN1", result.get(0).getLastName());
    }

    @Test
    public void testFindByLastNameContainsAndClassGroupId_ExactLastName_MultipleResults(){
        Person p1 = new Person(PersonDAOTestFind.p1);
        p1.setLastName("colonna");
        daoP.save(p1);

        Person p2 = new Person(PersonDAOTestFind.p2);
        p2.setLastName("colonna");
        daoP.save(p2);

        ArrayList<Person> result = (ArrayList<Person>) daoP.findByLastNameContainsAndClassGroup_Id("colonna", classGroup.getId());

        assertEquals(2, result.size());
    }
    /* ******************************************************************************* */
}
