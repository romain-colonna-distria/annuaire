
import annuaire.model.ClassGroup;
import annuaire.model.Person;
import annuaire.services.IClassGroupDAO;
import annuaire.services.IPersonDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.PersistenceException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = SpringConfiguration.class)
public class PersonDAOTest {

    @Autowired
    IPersonDAO daoP;

    @Autowired
    IClassGroupDAO daoG;

    ClassGroup classGroup;

    static Person notInitPerson;
    static Person withoutFirstNamePerson;
    static Person withoutLastNamePerson;
    static Person withoutEMailPerson;
    static Person withoutWebsitePerson;
    static Person withoutBirthdayPerson;
    static Person withoutPasswordPerson;
    static Person withoutClassGroupPerson;
    static Person goodPerson;

    /* ************************************ SETUP ************************************ *
    @BeforeAll
    public static void init(){
        withoutFirstNamePerson = new Person();
        withoutFirstNamePerson.setLastName("COLONNA");
        withoutFirstNamePerson.setEmail("colonna.distria.r@gmail.com");
        withoutFirstNamePerson.setWebsite("romain.com");
        withoutFirstNamePerson.setBirthday(new Date(842616000));
        withoutFirstNamePerson.setPassword("pass");

        withoutLastNamePerson = new Person();
        withoutLastNamePerson.setFirstName("Romain");
        withoutLastNamePerson.setEmail("colonna.distria.r@gmail.com");
        withoutLastNamePerson.setWebsite("romain.com");
        withoutLastNamePerson.setBirthday(new Date(842616000));
        withoutLastNamePerson.setPassword("pass");

        withoutEMailPerson = new Person();
        withoutEMailPerson.setFirstName("Romain");
        withoutEMailPerson.setLastName("COLONNA");
        withoutEMailPerson.setWebsite("romain.com");
        withoutEMailPerson.setBirthday(new Date(842616000));
        withoutEMailPerson.setPassword("pass");

        withoutWebsitePerson = new Person();
        withoutWebsitePerson.setFirstName("Romain");
        withoutWebsitePerson.setLastName("COLONNA");
        withoutWebsitePerson.setEmail("colonna.distria.r@gmail.com");
        withoutWebsitePerson.setBirthday(new Date(842616000));
        withoutWebsitePerson.setPassword("pass");

        withoutBirthdayPerson = new Person();
        withoutBirthdayPerson.setFirstName("Romain");
        withoutBirthdayPerson.setLastName("COLONNA");
        withoutBirthdayPerson.setWebsite("romain.com");
        withoutBirthdayPerson.setEmail("colonna.distria.r@gmail.com");
        withoutBirthdayPerson.setPassword("pass");

        withoutPasswordPerson = new Person();
        withoutPasswordPerson.setFirstName("Romain");
        withoutPasswordPerson.setLastName("COLONNA");
        withoutPasswordPerson.setEmail("colonna.distria.r@gmail.com");
        withoutPasswordPerson.setWebsite("romain.com");
        withoutPasswordPerson.setBirthday(new Date(842616000));

        withoutClassGroupPerson = new Person();
        withoutClassGroupPerson.setFirstName("Romain");
        withoutClassGroupPerson.setLastName("COLONNA");
        withoutClassGroupPerson.setEmail("colonna.distria.r@gmail.com");
        withoutClassGroupPerson.setWebsite("romain.com");
        withoutClassGroupPerson.setBirthday(new Date(842616000));
        withoutClassGroupPerson.setPassword("pass");

        goodPerson = new Person();
        goodPerson.setFirstName("Romain");
        goodPerson.setLastName("COLONNA");
        goodPerson.setEmail("colonna.distria.r@gmail.com");
        goodPerson.setWebsite("romain.com");
        goodPerson.setBirthday(new Date());
        goodPerson.setPassword("pass");
    }
    @BeforeEach
    public void setup(){
        classGroup = new ClassGroup();
        classGroup.setName("M1 ILD 2018/2019");

        daoG.save(classGroup);

        classGroup.addPerson(withoutFirstNamePerson);
        classGroup.addPerson(withoutLastNamePerson);
        classGroup.addPerson(withoutEMailPerson);
        classGroup.addPerson(withoutWebsitePerson);
        classGroup.addPerson(withoutBirthdayPerson);
        classGroup.addPerson(withoutPasswordPerson);
        classGroup.addPerson(goodPerson);
    }

    @AfterEach
    public void tearDown() {
        daoP.deleteAll();
        daoG.deleteAll();
    }

    /* ******************************************************************************* */




    /* ************************************ TEST ************************************* *

    @Test
    public void testAddNotInitPerson() {
        assertThrows(IllegalArgumentException.class, () -> {
            daoP.save(notInitPerson);
        });
    }

    @Test
    public void testAddPersonwWithoutFirstName() {
        assertThrows(PersistenceException.class, () -> {
            daoP.save(withoutFirstNamePerson);
        });
    }

    @Test
    public void testAddPersonWithoutLastName() {
        assertThrows(PersistenceException.class, () -> {
            daoP.save(withoutLastNamePerson);
        });
    }

    @Test
    public void testAddPersonWithoutEMail() {
        assertThrows(PersistenceException.class, () -> {
            daoP.save(withoutEMailPerson);
        });
    }

    @Test
    public void testAddPersonWithoutWebsite() {
        assertThrows(PersistenceException.class, () -> {
            daoP.save(withoutWebsitePerson);
        });
    }

    @Test
    public void testAddPersonWithoutBirthday() {
        assertThrows(PersistenceException.class, () -> {
            daoP.save(withoutBirthdayPerson);
        });
    }

    @Test
    public void testAddPersonWithoutPassword() {
        assertThrows(PersistenceException.class, () -> {
            daoP.save(withoutPasswordPerson);
        });
    }

    @Test
    public void testAddPersonWithoutClassGroup() {
        assertThrows(PersistenceException.class, () -> {
            daoP.save(withoutClassGroupPerson);
        });
    }

    @Test
    public void testAddGoodPersonNoException() {
        Person pInitial = new Person(goodPerson);

        assertDoesNotThrow(() -> {
            daoP.save(pInitial);
        });
    }






    @Test
    public void testFindPerson(){
        Person pInitial = new Person(goodPerson);
        daoP.save(pInitial);

        Person p = daoP.findById(pInitial.getId());

        assertEquals(pInitial, p);
    }



/*
    @Test
    public void testUpdateFirstNamePerson(){
        String newName = "other";
        Person pInitial = new Person(goodPerson);

        daoP.save(pInitial);
        pInitial.setFirstName(newName);
        daoP.updatePerson(pInitial);

        Person p = daoP.findPerson(pInitial.getId());

        assertEquals(p.getFirstName(), newName);
    }

    @Test
    public void testUpdateLastNamePerson(){
        String newName = "other";
        Person pInitial = new Person(goodPerson);

        daoP.addPerson(pInitial);
        pInitial.setLastName(newName);
        daoP.updatePerson(pInitial);

        Person p = daoP.findPerson(pInitial.getId());

        assertEquals(p.getLastName(), newName);
    }

    @Test
    public void testUpdateEMailPerson(){
        String newEMail = "other@other.oth";
        Person pInitial = new Person(goodPerson);

        daoP.addPerson(pInitial);
        pInitial.setEmail(newEMail);
        daoP.updatePerson(pInitial);

        Person p = daoP.findPerson(pInitial.getId());

        assertEquals(p.getEmail(), newEMail);
    }

    @Test
    public void testUpdateWebsitePerson(){
        String newWebsite = "other.oth";
        Person pInitial = new Person(goodPerson);

        daoP.addPerson(pInitial);
        pInitial.setWebsite(newWebsite);
        daoP.updatePerson(pInitial);

        Person p = daoP.findPerson(pInitial.getId());

        assertEquals(p.getWebsite(), newWebsite);
    }

    @Test
    public void testUpdateBirthdayPerson(){
        Date newBirthday = new Date();

        daoP.addPerson(goodPerson);
        Date oldBirthday = daoP.findPerson(goodPerson.getId()).getBirthday();

        goodPerson.setBirthday(newBirthday);
        daoP.updatePerson(goodPerson);

        Person p = daoP.findPerson(goodPerson.getId());

        assertEquals(p.getBirthday(), oldBirthday);
    }

    @Test
    public void testUpdatePasswordPerson(){
        String newPass = "other";
        Person pInitial = new Person(goodPerson);

        daoP.addPerson(pInitial);
        pInitial.setPassword(newPass);
        daoP.updatePerson(pInitial);

        Person p = daoP.findPerson(pInitial.getId());

        assertEquals(p.getPassword(), newPass);
    }
    */

    /* ******************************************************************************* */
}
