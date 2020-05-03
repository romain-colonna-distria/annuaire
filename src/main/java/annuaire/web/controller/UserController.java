package annuaire.web.controller;


import annuaire.model.User;

import annuaire.web.IDirectoryManager;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller()
@RequestMapping("/utilisateur")
public class UserController {

    @Autowired
    IDirectoryManager manager;



    @RequestMapping(value = "/connexion", method = RequestMethod.POST)
    public ModelAndView login(HttpSession session,
                              @RequestParam(value = "email", required = true) String email,
                              @RequestParam(value = "password", required = true) String pass) {
        User user;
        if(session.getAttribute("user") == null){
            user = manager.newUser();
            session.setAttribute("user", user);
        } else {
            user = (User) session.getAttribute("user");
        }

        session.setMaxInactiveInterval(10*60);

        manager.login(user, email, pass);

        return new ModelAndView("index");
    }

    @RequestMapping("/deconnexion")
    public ModelAndView logout(HttpSession session) {
        User user;
        if(session.getAttribute("user") == null){
            user = new User();
            session.setAttribute("user", user);
        } else {
            user = (User) session.getAttribute("user");
        }

        manager.logout(user);
        session.setAttribute("user", user);
        return new ModelAndView("logout");
    }

    @RequestMapping("/profile")
    public ModelAndView showProfil(HttpSession session){
        if(session.getAttribute("user") == null){
            return new ModelAndView("logout");
        }

        User user = (User) session.getAttribute("user");

        ModelAndView mv = new ModelAndView("profil");
        mv.addObject("user", user);

        return mv;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editProfil(HttpSession session,
                                   @RequestParam(value = "firstName", required = false) String firstName,
                                   @RequestParam(value = "lastName", required = false) String lastName,
                                   @RequestParam(value = "email", required = false) String email,
                                   @RequestParam(value = "website", required = false) String website,
                                   @RequestParam(value = "birthday", required = false) String birthday,
                                   @RequestParam(value = "password", required = false) String password){
        if(session.getAttribute("user") == null) {
            return new ModelAndView("logout");
        }

        User user = (User) session.getAttribute("user");

        if(!firstName.equals("")) {
            user.getPerson().setFirstName(firstName);
        }
        if(!lastName.equals("")) {
            user.getPerson().setLastName(lastName);
        }
        if(!email.equals("")) {
            if(EmailValidator.getInstance().isValid(email))
                user.getPerson().setEmail(email);
        }
        if(!website.equals("")) {
            try {
                URL url = new URL(website);
                user.getPerson().setWebsite(website);
            } catch (MalformedURLException e) {
                System.err.println("Mauvaise url.");
            }

        }
        if(!birthday.equals("")) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date parsed = format.parse(birthday);
                user.getPerson().setBirthday(parsed);
            } catch (ParseException e) {
                System.err.println("Mauvaise date de naissance.");
            }
        }
        if(!password.equals("")) {
            user.getPerson().setPassword(password);
        }


        manager.savePerson(user.getPerson());
        session.setAttribute("user", user);

        ModelAndView mv = new ModelAndView("edit");

        return mv;
    }
}
