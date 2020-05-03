package annuaire.web.controller;

import annuaire.model.Person;
import annuaire.web.IDirectoryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
@RequestMapping("/personne")
public class PersonController {

    @Autowired
    IDirectoryManager manager;

    @RequestMapping(value = "liste")
    public ModelAndView list(HttpSession session,
                             @RequestParam(value = "prenom", required = false) String firstName,
                             @RequestParam(value = "nom", required = false) String lastName) {
        ArrayList<Person> result = new ArrayList<>();
        ArrayList<Person> firstNames = new ArrayList<>();
        ArrayList<Person> lastNames = new ArrayList<>();

        if (firstName != null)
            firstNames = (ArrayList<Person>) manager.findByFirstNameContains(firstName);
        if(lastName != null)
            lastNames = (ArrayList<Person>) manager.findByLastNameContains(lastName);

        if(firstName != null && lastName != null){
            if(firstNames.size() < lastNames.size() && firstNames.size() > 0){
                for(Person p : firstNames) {
                    if (!lastNames.contains(p)) continue;
                    result.add(p);
                }
            } else if (lastNames.size() <= firstNames.size() && lastNames.size() > 0) {
                for(Person p : lastNames) {
                    if (!firstNames.contains(p)) continue;
                    result.add(p);
                }
            }
        } else if (firstName != null) {
            result = firstNames;
        } else if (lastName != null) {
            result = lastNames;
        } else {
            result = new ArrayList<>(manager.findAllPerson());
        }

        ModelAndView mv = new ModelAndView("person-list");
        mv.addObject("user", session.getAttribute("user"));
        mv.addObject("persons", result);

        return mv;
    }
}
