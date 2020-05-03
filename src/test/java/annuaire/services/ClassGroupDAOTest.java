package annuaire.services;

import annuaire.Starter;
import annuaire.model.ClassGroup;
import annuaire.model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Starter.class)
public class ClassGroupDAOTest {

    @Autowired
    IClassGroupDAO daoG;


    ClassGroup notInitClassGroup;
    ClassGroup withoutNameClassGroup;
    ClassGroup classGroupWithEmptyName;

    @BeforeEach
    public void setup(){
        withoutNameClassGroup = new ClassGroup();

        classGroupWithEmptyName = new ClassGroup();
        classGroupWithEmptyName.setName("");
    }

    @AfterEach
    public void tearDown() {
        daoG.deleteAll();
    }

    @Test
    public void testSaveNotInitClassGroup() {
        assertThrows(InvalidDataAccessApiUsageException.class, () -> {
            daoG.save(notInitClassGroup);
        });
    }

    @Test
    public void testSaveClassGroupWithEmptyName() {
        /*
        assertThrows(DataIntegrityViolationException.class, () -> {
            daoG.save(classGroupWithEmptyName);
        });
        */
    }

    @Test
    public void testSaveClassGroupWithoutName() {
        assertThrows(DataIntegrityViolationException.class, () -> {
            daoG.save(withoutNameClassGroup);
        });
    }
}
