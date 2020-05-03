package annuaire.web.controller;

import annuaire.model.ClassGroup;
import annuaire.model.Person;

import annuaire.web.IDirectoryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/groupe")
public class ClassGroupController {

    @Autowired
    IDirectoryManager manager;


    @RequestMapping(value = "/liste")
    public ModelAndView list(HttpSession session) {
        ModelAndView mv = new ModelAndView("group-list");
        mv.addObject("user", session.getAttribute("user"));

        return mv;
    }

    @RequestMapping(value = "/{param}", method = RequestMethod.GET)
    public ModelAndView show(HttpSession session,
                             HttpServletRequest request,
                             @PathVariable("param") Integer id,
                             @RequestParam(value = "prenom", required = false) String firstName,
                             @RequestParam(value = "nom", required = false) String lastName) {
        ClassGroup group = manager.findGroupById(id);
        ArrayList<Person> result = new ArrayList<>();
        ArrayList<Person> firstNames = new ArrayList<>();
        ArrayList<Person> lastNames = new ArrayList<>();

        if (firstName != null)
            firstNames = (ArrayList<Person>) manager.findByFirstNameContainsAndClassGroup_Id(firstName, group.getId());
        if(lastName != null)
            lastNames = (ArrayList<Person>) manager.findByLastNameContainsAndClassGroup_Id(lastName, group.getId());

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
            result = new ArrayList<>(group.getPersons());
        }

        ModelAndView mv = new ModelAndView("group");
        mv.addObject("user", session.getAttribute("user"));
        mv.addObject("persons", result);
        mv.addObject("group", group);

        return mv;
    }

    @ModelAttribute("groups")
    public Collection<ClassGroup> searchGroups(@RequestParam(value = "contain", required = false) String groupName) {
        ArrayList<ClassGroup> result;
        if (groupName != null) {
            result = (ArrayList<ClassGroup>) manager.findByNameContains(groupName);
        } else {
            result = (ArrayList<ClassGroup>) manager.findAllGroup();
        }

        return result;
    }
}






/*

 */