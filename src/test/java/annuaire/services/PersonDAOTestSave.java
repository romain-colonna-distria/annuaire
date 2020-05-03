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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Starter.class)
public class PersonDAOTestSave {

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

    /* ************************************ SETUP ************************************ */
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




    /* ********************************** TEST SAVE ********************************** */
    @Test
    public void testSaveNotInitPerson() {
        assertThrows(InvalidDataAccessApiUsageException.class, () -> {
            daoP.save(notInitPerson);
        });
    }

    @Test
    public void testSavePersonwWithoutFirstName() {
        assertThrows(DataIntegrityViolationException.class, () -> {
            daoP.save(withoutFirstNamePerson);
        });
    }

    @Test
    public void testSavePersonWithoutLastName() {
        assertThrows(DataIntegrityViolationException.class, () -> {
            daoP.save(withoutLastNamePerson);
        });
    }

    @Test
    public void testSavePersonWithoutEMail() {
        assertThrows(DataIntegrityViolationException.class, () -> {
            daoP.save(withoutEMailPerson);
        });
    }

    @Test
    public void testSavePersonWithoutWebsite() {
        assertThrows(DataIntegrityViolationException.class, () -> {
            daoP.save(withoutWebsitePerson);
        });
    }

    @Test
    public void testSavePersonWithoutBirthday() {
        assertThrows(DataIntegrityViolationException.class, () -> {
            daoP.save(withoutBirthdayPerson);
        });
    }

    @Test
    public void testSavePersonWithoutPassword() {
        assertThrows(DataIntegrityViolationException.class, () -> {
            daoP.save(withoutPasswordPerson);
        });
    }

    @Test
    public void testSavePersonWithoutClassGroup() {
        assertDoesNotThrow(() -> {
            daoP.save(withoutClassGroupPerson);
        });
    }

    @Test
    public void testSaveGoodPersonNoException() {
        Person pInitial = new Person(goodPerson);

        assertDoesNotThrow(() -> {
            daoP.save(pInitial);
        });
    }
    /* ******************************************************************************* */
}
