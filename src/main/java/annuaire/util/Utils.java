package annuaire.util;

import annuaire.model.ClassGroup;
import annuaire.model.Person;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Utils {
    public static List<Person> generatePersons(int nb){
        List<Person> result = new ArrayList<>(nb);

        for(int i = 0; i < nb; ++i){
            Person p = new Person();
            p.setFirstName("Prenom" + i);
            p.setLastName("Nom" + i);
            p.setEmail(i + "@" + i + ".fr");
            p.setWebsite("site" + i + ".com");
            p.setBirthday(new Date());
            p.setPassword("pass" + i);

            result.add(p);
        }

        return result;
    }

    public static List<ClassGroup> generateGroups(int nb){
        List<ClassGroup> result = new ArrayList<>(nb);

        for(int i = 0; i < nb; ++i){
            ClassGroup g = new ClassGroup();
            g.setName("Groupe" + i);

            result.add(g);
        }

        return result;
    }
}
