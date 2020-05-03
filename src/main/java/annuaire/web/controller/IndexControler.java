package annuaire.web.controller;

import annuaire.util.Utils;
import annuaire.model.ClassGroup;
import annuaire.model.Person;

import annuaire.web.IDirectoryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;

import java.util.Date;
import java.util.List;
import java.util.Random;


@Controller
@RequestMapping("/")
public class IndexControler {

	@Autowired
	IDirectoryManager manager;

	@PostConstruct
	public void init() {
		List<Person> persons = Utils.generatePersons(10000);
		List<ClassGroup> groups = Utils.generateGroups(700);

		for(Person p : persons){
			Random rand = new Random();
			int r = rand.nextInt(groups.size());

			groups.get(r).addPerson(p);
		}

		for(ClassGroup g : groups)
			manager.saveGroup(g);

		for(Person p : persons)
			manager.savePerson(p);
	}

	/*
	 * Point d'entrée principal de l'application.
	 */
	@RequestMapping("")
	public ModelAndView index() {
		return new ModelAndView("index");
	}


}




/*
		Person p = new Person();
		p.setFirstName("Romain");
		p.setLastName("COLONNA");
		p.setEmail("romain@gmail.com");
		p.setWebsite("romain.com");
		p.setBirthday(new Date());
		p.setPassword("coucou");


		ClassGroup c = new ClassGroup();
		c.setName("M1 ILD 2018/2019");
		c.addPerson(p);

		daoC.save(c);
		daoP.save(p);
 */