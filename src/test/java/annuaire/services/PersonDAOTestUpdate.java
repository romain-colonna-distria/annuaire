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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Starter.class)
public class PersonDAOTestUpdate {

    @Autowired
    IPersonDAO daoP;

    @Autowired
    IClassGroupDAO daoG;

    ClassGroup classGroup;
    static Person goodPerson;

    /* ************************************ SETUP ************************************ */
    @BeforeAll
    public static void init(){
        goodPerson = new Person();
        goodPerson.setFirstName("Romain");
        goodPerson.setLastName("COLONNA");
        goodPerson.setEmail("colonna.distria.r@gmail.com");
        goodPerson.setWebsite("romain.com");
        goodPerson.setBirthday(new Date());
        goodPerson.setPassword("pass");
    }

    @AfterEach
    public void tearDown() {
        daoP.deleteAll();
        daoG.deleteAll();
    }
    /* ******************************************************************************* */

    /* ********************************* TEST UPDATE ********************************* */
    @Test
    public void testUpdateFirstName_GoodFirstName(){
        Person p = new Person(goodPerson);
        daoP.save(p);

        p.setFirstName("New");
        daoP.updatePerson(p.getId(), p.getFirstName(), p.getLastName(), p.getEmail(), p.getWebsite(), p.getBirthday(), p.getPassword());

        Person p2 = daoP.findById(p.getId());

        assertEquals("New", p2.getFirstName());
    }

    @Test
    public void testUpdateFirstName_EmptyFirstName(){
        Person p = new Person(goodPerson);
        daoP.save(p);

        p.setFirstName("");

        assertThrows(DataIntegrityViolationException.class, () -> {
            daoP.updatePerson(p.getId(), p.getFirstName(), p.getLastName(), p.getEmail(), p.getWebsite(), p.getBirthday(), p.getPassword());
        });
    }

    @Test
    public void testUpdateFirstName_NullFirstName(){
        Person p = new Person(goodPerson);
        daoP.save(p);

        p.setFirstName(null);

        assertThrows(DataIntegrityViolationException.class, () -> {
            daoP.updatePerson(p.getId(), p.getFirstName(), p.getLastName(), p.getEmail(), p.getWebsite(), p.getBirthday(), p.getPassword());
        });

    }

    /* ******************************************************************************* */
}

