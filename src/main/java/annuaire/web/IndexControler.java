package annuaire.web;

import annuaire.model.ClassGroup;
import annuaire.model.Person;
import annuaire.services.IClassGroupDAO;
import annuaire.services.IPersonDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;


@Controller
@RequestMapping("/")
public class IndexControler {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	IPersonDAO daoP;

	@Autowired
	IClassGroupDAO daoC;


	/*
	 * Point d'entrée principal de l'application.
	 */
	@RequestMapping("")
	public ModelAndView index() {
		/*
		Person p = new Person();
		p.setFirstName("ROmain");
		p.setLastName("colo");
		p.setEmail("mail@d.d");
		p.setWebsite("r.com");
		p.setBirthday(new Date());

		daoP.save(p);

		ClassGroup c = new ClassGroup();
		c.setName("mon groupe");

		daoC.save(c);*/

		//ArrayList<Person> persons = (ArrayList<Person>) daoP.findAll();
		return new ModelAndView("index"/*, "persons", persons*/);
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