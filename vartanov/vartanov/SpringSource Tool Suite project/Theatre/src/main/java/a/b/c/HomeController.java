package a.b.c;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import a.b.c.domain.Cost;
import a.b.c.domain.Occupied;
import a.b.c.domain.Performance;
import a.b.c.domain.Person;
import a.b.c.domain.Place;
import a.b.c.domain.PlaceType;
import a.b.c.domain.Registration;
import a.b.c.domain.Role;
import a.b.c.domain.Show;
import a.b.c.domain.Theatre;
import a.b.c.service.IDBManager;
import a.b.c.service.ITheatreManager;

@Controller
public class HomeController 
{
	private static final Logger logger = LoggerFactory.getLogger (HomeController.class);
	
	@Autowired private ITheatreManager service; 
	
	@Autowired private IDBManager <Theatre> 	theatreDBService;
	@Autowired private IDBManager <Performance> performanceDBService;
	@Autowired private IDBManager <Person> 		personDBService;
	@Autowired private IDBManager <Place> 		placeDBService;
	@Autowired private IDBManager <Show> 		showDBService;
	@Autowired private IDBManager <Occupied>	occupiedDBService;
	@Autowired private IDBManager <Role>		roleDBService;
	@Autowired private IDBManager <Cost>		costDBService;
	
	static public String log = "admin";

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home (Map <String, Object> map) 
	{
		logger.info ("Enzet Theatres.");
		map.put ("log", log);

		return "home";
	}
	
	@RequestMapping("/theatres")
	public String theatres (Map <String, Object> map) 
	{
		map.put ("AllTheatres", theatreDBService.getAll ("Theatre"));
		map.put ("toperformance", -1);
		map.put ("log", log);

		return "theatres"; // theatres.jsp
	}
	
	@RequestMapping("/theatre/{theatreId}")
	public String theatre (Map <String, Object> map, @PathVariable("theatreId") Integer theatreId) 
	{
		Theatre t = theatreDBService.get (theatreId, "Theatre");
		
		map.put ("t", t);
		map.put ("performances", service.getPerformancesByTheatre (t));
		map.put ("log", log);
		
		return "theatre"; // theatre.jsp
	}
	
	@RequestMapping("/performances")
	public String performances (Map <String, Object> map) 
	{
		map.put ("performances", performanceDBService.getAll ("Performance"));
		map.put ("log", log);

		return "performances"; // performances.jsp
	}
	
	@RequestMapping("/performance/{performanceId}")
	public String performance (Map <String, Object> map, @PathVariable("performanceId") Integer performanceId) 
	{
		Performance t = performanceDBService.get (performanceId, "Performance");
		List <Person> actors = service.getActorsByPerformance (performanceId);
		List <Show> shows = service.getShowsByPerformance (performanceId);
		
		map.put ("performance", t);
		map.put ("actors", actors);
		map.put ("shows", shows);
		map.put ("log", log);
		
		return "performance"; // performance.jsp
	}
	
	@RequestMapping("/show/{showId}")
	public String show (Map <String, Object> map, @PathVariable("showId") Integer showId) 
	{
		Show sh = showDBService.get (showId, "Show");
		Performance p = sh.getPerformance ();
		
		List <Cost> cc = service.getCostsByShow (sh);
		
		Cost cb = new Cost ();
		Cost cs = new Cost ();
		Cost cd = new Cost ();
		
		List <Place> pl = service.getPlacesByShow (sh.getId ());
		
		List <Place> b = new ArrayList <Place> ();
		List <Place> s = new ArrayList <Place> ();
		List <Place> d = new ArrayList <Place> ();
		
		List <Place> bb = new ArrayList <Place> ();
		List <Place> bs = new ArrayList <Place> ();
		List <Place> bd = new ArrayList <Place> ();
		
		for (Place place : pl)
		{
			if (place.getType () == 1) 
				if (service.boolOccupied (place, sh)) bb.add (place); else b.add (place);
			if (place.getType () == 0) 
				if (service.boolOccupied (place, sh)) bs.add (place); else s.add (place);
			if (place.getType () == 2) 
				if (service.boolOccupied (place, sh)) bd.add (place); else d.add (place);
		}
		
		for (Cost c : cc)
		{
			if (c.getType () == 1) cb = c;
			if (c.getType () == 0) cs = c;
			if (c.getType () == 2) cd = c;
		}
		
		map.put ("performance", p);
		map.put ("show", sh);
		map.put ("log", log);
		map.put ("b", b); 
		map.put ("s", s); 
		map.put ("d", d); 		
		map.put ("bb", bb); 
		map.put ("bs", bs); 
		map.put ("bd", bd);
		
		map.put ("cb", cb); 
		map.put ("cs", cs); 
		map.put ("cd", cd); 
		
		return "show";
	}
	
	@RequestMapping("/style.css")
	public String CSS(Map <String, Object> map) 
	{
		return "style";
	}

	@RequestMapping("/persons")
	public String persons (Map <String, Object> map) 
	{
		map.put ("dtoperformance", -1);
		map.put ("atoperformance", -1);
		map.put ("persons", personDBService.getAll ("Person"));
		map.put ("log", log);

		return "persons"; // persons.jsp
	}
	
	@RequestMapping("/person/{personId}")
	public String person (Map <String, Object> map, @PathVariable("personId") Integer personId) 
	{
		Person p = personDBService.get (personId, "Person");
		
		List <Performance> dperformances = service.getPerformancesByDirector (personId);
		List <Performance> aperformances = service.getPerformancesByActor (personId);
		
		map.put ("person", p);
		map.put ("dperformances", dperformances);
		map.put ("aperformances", aperformances);
		map.put ("log", log);
		
		return "person"; // person.jsp
	}
	
	// Theatre
	
	@RequestMapping(value = "/updatetheatre")
	public String updatetheatre (Map <String, Object> map) 
	{
		map.put("theatre", new Theatre());
		map.put ("log", log);

		return "updatetheatre";
	}
	
	@RequestMapping(value = "addtheatre", method = RequestMethod.POST)
	public String addtheatre (Map <String, Object> map, @ModelAttribute("theatre") Theatre theatre, BindingResult result) 
	{
		System.out.println ("=" + theatre.getTitle () + "=");
		System.out.println ("=" + theatre.getAddress () + "=");
		
		if (theatre.getTitle ().equals (""))
		{
			map.put ("log", log);
			map.put ("msg", "Has no title.");
			return "fail";
		}
		if (theatre.getAddress ().equals (""))
		{
			map.put ("log", log);
			map.put ("msg", "Has no address.");
			return "fail";
		}
		
		theatreDBService.save (theatre);

		placeDBService.save (new Place (theatre, 1, 1, 0));
		placeDBService.save (new Place (theatre, 1, 2, 0));
		placeDBService.save (new Place (theatre, 2, 2, 1));
		placeDBService.save (new Place (theatre, 2, 1, 2));
		
		theatreDBService.save (theatre);
		map.put ("log", log);

		return "ok";
	}
	
	@RequestMapping(value = "addtheatre/{theatreId}", method = RequestMethod.POST)
	public String addtheatre (Map <String, Object> map, @ModelAttribute("theatre") Theatre theatre, BindingResult result, @PathVariable("theatreId") Integer theatreId) 
	{
		theatre.setId (theatreId);
		theatreDBService.save (theatre);
		map.put ("log", log);

		return "ok";
	}
	
	@RequestMapping("/deletetheatre/{theatreId}")
	public String deletetheatre (Map <String, Object> map, @PathVariable("theatreId") Integer theatreId) 
	{
		Theatre t = theatreDBService.get (theatreId, "Theatre");
		
		service.deleteTheatre (t);
		map.put ("log", log);
		
		return "ok";
	}
	
	@RequestMapping("/changetheatre/{theatreId}")
	public String changetheatre (Map <String, Object> map, @PathVariable("theatreId") Integer theatreId) 
	{
		Theatre t = theatreDBService.get (theatreId, "Theatre");
		t.setId (theatreId);
		
		map.put ("theatre", t);
		map.put ("log", log);
		
		return "changetheatre";
	}
	
	@RequestMapping(value = "tochangetheatre", method = RequestMethod.GET)
	public String tochangetheatre (Map <String, Object> map, @RequestParam("theatreId") String theatreId, @RequestParam("title") String title, @RequestParam("address") String address) 
	{
		if (title.equals (""))
		{
			map.put ("log", log);
			map.put ("msg", "Has no title.");
			return "fail";
		}
		if (address.equals (""))
		{
			map.put ("log", log);
			map.put ("msg", "Has no address.");
			return "fail";
		}
		Theatre t = theatreDBService.get (Integer.parseInt (theatreId), "Theatre");
		t.setTitle (title);
		t.setAddress (address);
		theatreDBService.save (t);
		
		return "ok";
	}

	// Performance
	
	@RequestMapping(value = "/updateperformance")
	public String updateperformance (Map <String, Object> map) 
	{
		map.put ("performance", new Performance ());
		map.put ("log", log);

		return "updateperformance"; // updateperformance.jsp
	}
	
	@RequestMapping(value = "/addperformance", method = RequestMethod.POST)
	public String addperformance (Map <String, Object> map, @ModelAttribute("performance") Performance performance, BindingResult result) 
	{
		if (performance.getTitle ().equals (""))
		{
			map.put ("log", log);
			map.put ("msg", "Has no title.");
			return "fail";
		}
		if (performance.getDuration () <= 0)
		{
			map.put ("log", log);
			map.put ("msg", "Duration incorrect.");
			return "fail";
		}
		performanceDBService.save (performance);
		map.put ("log", log);

		return "ok";
	}
	
	@RequestMapping("/deleteperformance/{performanceId}")
	public String deleteperformance (Map <String, Object> map, @PathVariable("performanceId") Integer performanceId) 
	{
		Performance p = performanceDBService.get (performanceId, "Performance");

		service.deletePerformance (p);
		map.put ("log", log);
		
		return "ok";
	}
	
	@RequestMapping("/changeperformance/{performanceId}")
	public String changeperformance (Map <String, Object> map, @PathVariable("performanceId") Integer performanceId) 
	{
		Performance t = performanceDBService.get (performanceId, "Performance");
		t.setId (performanceId);
		
		map.put ("performance", t);
		map.put ("log", log);
		
		return "changeperformance";
	}
	
	@RequestMapping(value = "tochangeperformance", method = RequestMethod.GET)
	public String tochangeperformance (Map <String, Object> map, @RequestParam("performanceId") String performanceId, @RequestParam("title") String title, @RequestParam("duration") String duration) 
	{
		if (title.equals (""))
		{
			map.put ("log", log);
			map.put ("msg", "Has no title.");
			return "fail";
		}
		if (!duration.matches ("[1-9]+[0-9]*"))
		{
			map.put ("log", log);
			map.put ("msg", "Error duration.");
			return "fail";
		}
		Performance t = performanceDBService.get (Integer.parseInt (performanceId), "Performance");
		t.setTitle (title);
		t.setDuration (Integer.parseInt (duration));
		performanceDBService.save (t);
		
		return "ok";
	}

	// Registration
	
	@RequestMapping("/registration")
	public String registration (Map <String, Object> map)
	{
		map.put ("registration", new Registration ());
		map.put ("log", log);
		
		return "registration";
	}
	
	@RequestMapping("/newregistration")
	public String registration (Map <String, Object> map, @ModelAttribute("registration") Registration registration, BindingResult result)
	{
		if (service.saveLogin (registration))
		{
			log = registration.getLogin ();
			map.put ("log", log);
			return "ok";
		}
		return "errorregistration";
	}
	
	@RequestMapping("/errorregistration")
	public String errorregistration (Map <String, Object> map)
	{
		map.put ("registration", new Registration ());
		map.put ("log", log);
		
		return "errorregistration";
	}
	
	// Login
	
	@RequestMapping("/login")
	public String login (Map <String, Object> map)
	{
		map.put ("registration", new Registration ());
		map.put ("log", log);
		
		return "login";
	}
	
	@RequestMapping("/checklogin")
	public String login (Map <String, Object> map, @ModelAttribute("registration") Registration registration, BindingResult result)
	{
		if (service.checkLogin (registration.getLogin (), registration.getPassword ())) 
		{
			log = registration.getLogin ();
			map.put ("log", log);
			
			return "ok";
		}
		return "errorlogin";
	}
	
	@RequestMapping("/errorlogin")
	public String errorlogin (Map <String, Object> map)
	{
		map.put ("registration", new Registration ());
		map.put ("log", log);
		
		return "errorlogin";
	}
	
	@RequestMapping("/logout")
	public String logout (Map <String, Object> map)
	{
		log = "guest";
		map.put ("log", log);
		
		return "ok";
	}
	
	// Buy
	
	@RequestMapping("/buy/{placeId}/{showId}")
	public String buy (Map <String, Object> map, @PathVariable("placeId") Integer placeId, @PathVariable("showId") Integer showId) 
	{
		Place p = placeDBService.get (placeId, "Place");
		Show s = showDBService.get (showId, "Show");
	
		int price = -1;
		
		List <Cost> cc = service.getCostsByShow (s);
		for (Cost c : cc)
			if (c.getType () == p.getType ()) price = c.getPrice ();		
		
		map.put ("place", p);
		map.put ("show", s);
		map.put ("price", price);
		map.put ("log", log);
		
		return "buy"; // performances.jsp
	}	
	
	@RequestMapping("/ticket/{placeId}/{showId}")
	public String ticket (Map <String, Object> map, @PathVariable("placeId") Integer placeId, @PathVariable("showId") Integer showId) 
	{
		Place p = placeDBService.get (placeId, "Place");
		Show s = showDBService.get (showId, "Show");
		
		Occupied o = service.isOccupied (p, s).get (0);
		o.setOccupied (true);
		occupiedDBService.save (o);
		map.put ("log", log);
		
		return "ok"; // performances.jsp
	}
	
	// Director to performance
	
	@RequestMapping("/directortoperformance/{performanceId}")
	public String directortoperformance (Map <String, Object> map, @PathVariable("performanceId") Integer performanceId)
	{
		map.put ("persons", personDBService.getAll ("Person"));
		map.put ("dtoperformance", performanceId);
		map.put ("atoperformance", -1);
		map.put ("log", log);
		
		return "persons";
	}
	
	@RequestMapping("/setdirectortoperformance/{performanceId}/{personId}")
	public String setdirectortoperformance (Map <String, Object> map, @PathVariable("performanceId") Integer performanceId, @PathVariable("personId") Integer personId)
	{
		Performance p = performanceDBService.get (performanceId, "Performance");
		Person d = personDBService.get (personId, "Person");
		
		p.setDirector (d);
		performanceDBService.save (p);

		map.put ("log", log);
		
		return "ok";
	}
	
	// Theatre to performance
	
	@RequestMapping("/theatretoperformance/{performanceId}")
	public String theatretoperformance (Map <String, Object> map, @PathVariable("performanceId") Integer performanceId)
	{
		map.put ("AllTheatres", theatreDBService.getAll ("Theatre"));
		map.put ("toperformance", performanceId);
		map.put ("log", log);
		
		return "theatres";
	}
	
	@RequestMapping("/settheatretoperformance/{performanceId}/{theatreId}")
	public String settheatretoperformance (Map <String, Object> map, @PathVariable("performanceId") Integer performanceId, @PathVariable("theatreId") Integer theatreId)
	{
		Performance p = performanceDBService.get (performanceId, "Performance");
		Theatre t = theatreDBService.get (theatreId, "Theatre");
		
		p.setTheatre (t);
		performanceDBService.save (p);

		List <Show> ss = service.getShowsByPerformance (p.getId ());
		
		for (Show s : ss)
		{
			List <Place> pp = service.getPlacesByTheatre (t); 
			
			for (Place pl : pp)
			{
				Occupied o = new Occupied ();
				o.setPlace (pl);
				o.setShow (s);
				o.setOccupied (false);
				occupiedDBService.save (o);
			}
		}
		
		map.put ("log", log);
		
		return "ok";
	}
	
	// Actor to performance
	
	@RequestMapping("/actortoperformance/{performanceId}")
	public String actortoperformance (Map <String, Object> map, @PathVariable("performanceId") Integer performanceId)
	{
		map.put ("persons", personDBService.getAll ("Person"));
		map.put ("atoperformance", performanceId);
		map.put ("dtoperformance", -1);
		map.put ("log", log);
		
		return "persons";
	}
	
	@RequestMapping("/setactortoperformance/{performanceId}/{personId}")
	public String setactortoperformance (Map <String, Object> map, @PathVariable("performanceId") Integer performanceId, @PathVariable("personId") Integer personId)
	{
		Performance p = performanceDBService.get (performanceId, "Performance");
		Person a = personDBService.get (personId, "Person");
		
		Role r = new Role ();
		r.setActor (a);
		r.setPerformance (p);
		roleDBService.save (r);

		map.put ("log", log);
		
		return "ok";
	}
	
	// Show to performance
	
	@RequestMapping("/showtoperformance/{performanceId}")
	public String showtoperformance (Map <String, Object> map, @PathVariable("performanceId") Integer performanceId)
	{
		Performance p = performanceDBService.get (performanceId, "Performance");
		
		if (p.getTheatre () == null)
		{
			map.put ("log", log);
			map.put ("msg", "Can not add a show. The theater is not specified.");
			return "fail";
		}
		else
		{
			Show s = new Show ();
			
			s.setPerformance (p);
			
			map.put ("show1", s);
			map.put ("log", log);
			return "newshow";
		}
	}
	
	@RequestMapping(value = "addshow", method = RequestMethod.GET)
	public String addshow (Map <String, Object> map, @RequestParam("date") String date, @RequestParam("performanceId") String performanceId) 
	{
		DateFormat formatter = new SimpleDateFormat ("yyyy.MM.dd.HH.mm.ss");
	    Date date1;
	    
		try 
		{
			date1 = (Date) formatter.parse (date);
		} 
		catch (ParseException e) 
		{
			map.put ("msg", "Invalid date format.");
			return "fail";
		}
		
		Show s = new Show ();
		Performance p = performanceDBService.get (Integer.parseInt (performanceId), "Performance");
		
		Theatre t = theatreDBService.get (p.getTheatre ().getId (), "Theatre");
		
		List <Place> pp = service.getPlacesByTheatre (t); 
		
		s.setPerformance (p);
		s.setDate (date1);
		showDBService.save (s);
		
		for (Place pl : pp)
		{
			Occupied o = new Occupied ();
			o.setPlace (pl);
			o.setShow (s);
			o.setOccupied (false);
			occupiedDBService.save (o);
		}
		
		map.put ("log", log);

		return "ok";
	}
	
	// Add person
	
	@RequestMapping(value = "/newperson")
	public String newperson (Map <String, Object> map) 
	{
		map.put ("person", new Person ());
		map.put ("log", log);

		return "newperson";
	}
	
	@RequestMapping(value = "addperson", method = RequestMethod.POST)
	public String newperson (Map <String, Object> map, @ModelAttribute("person") Person person, BindingResult result) 
	{
		personDBService.save (person);
		map.put ("log", log);

		return "ok";
	}
	
	@RequestMapping("/deleteperson/{personId}")
	public String deleteperson (Map <String, Object> map, @PathVariable("personId") Integer personId) 
	{
		Person p = personDBService.get (personId, "Person");
		
		service.deletePerson (p);
		map.put ("log", log);
		
		return "ok";
	}
	
	// Delete show
	
	@RequestMapping("/deleteshow/{showId}")
	public String deleteshow (Map <String, Object> map, @PathVariable("showId") Integer showId) 
	{
		Show s = showDBService.get (showId, "Show");
		
		service.deleteShow (s);
		map.put ("log", log);
		
		return "ok";
	}
	
	// Person
	
	@RequestMapping("/changeperson/{personId}")
	public String changeperson (Map <String, Object> map, @PathVariable("personId") Integer personId) 
	{
		Person t = personDBService.get (personId, "Person");
		t.setId (personId);
		
		map.put ("person", t);
		map.put ("log", log);
		
		return "changeperson";
	}
	
	@RequestMapping(value = "tochangeperson", method = RequestMethod.GET)
	public String tochangeperson (Map <String, Object> map, @RequestParam("personId") String personId, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) 
	{
		if (firstName.equals (""))
		{
			map.put ("log", log);
			map.put ("msg", "Has no first name.");
			return "fail";
		}
		if (lastName.equals (""))
		{
			map.put ("log", log);
			map.put ("msg", "Has no last name.");
			return "fail";
		}

		Person t = personDBService.get (Integer.parseInt (personId), "Person");
		t.setFirstName (firstName);
		t.setLastName (lastName);
		personDBService.save (t);
		map.put ("log", log);
		
		return "ok";
	}
	
	// Price to show
	
	@RequestMapping (value = "pricetoshow/{type}/{showId}")
	public String pricetoshow (Map <String, Object> map, @PathVariable("type") Integer type, @PathVariable("showId") Integer showId)
	{
		map.put ("showId", showId);
		map.put ("type", type);
		map.put ("log", log);
		
		return "price";
	}
	
	@RequestMapping(value = "setpricetoshow", method = RequestMethod.GET)
	public String setpricetoshow (Map <String, Object> map, @RequestParam("showId") String showId, @RequestParam("type") String type, @RequestParam("price") String price) 
	{
		if (!price.matches ("[1-9]+[0-9]*"))
		{
			map.put ("log", log);
			map.put ("msg", "Error price.");
			return "fail";
		}
		
		Cost c = new Cost ();
		if (type.equals ("0")) c.setType (0);
		if (type.equals ("1")) c.setType (1);
		if (type.equals ("2")) c.setType (2);
		
		c.setPrice (Integer.parseInt (price));
		
		Show s = showDBService.get (Integer.parseInt (showId), "Show");
		c.setShow (s);
		
		costDBService.save (c);
		
		map.put ("log", log);
		
		return "ok";
	}
	
	@RequestMapping (value = "date")
	public String date (Map <String, Object> map)
	{
		map.put ("log", log);
		
		return "date";
	}
	
	@RequestMapping(value = "showdatelist", method = RequestMethod.GET)
	public String showdatelist (Map <String, Object> map, @RequestParam("from") String date1, @RequestParam("to") String date2) 
	{
		DateFormat formatter = new SimpleDateFormat ("yyyy.MM.dd");
	    Date d1, d2;
	    
		/*if (!date1.matches ("[0-9][0-9][0-9][0-9]\\.[0-1][0-9]\\.[0-3][0-9]") ||
		!date1.matches ("[0-9][0-9][0-9][0-9]\\.[0-1][0-9]\\.[0-3][0-9]"))*/
		try 
		{
			d1 = (Date) formatter.parse (date1);
			d2 = (Date) formatter.parse (date2);

			if (d1 != null && d2 != null)
				if (!d2.after (d1))
				{
					map.put ("msg", "Second date is not after first date.");
					map.put ("log", log);
					return "fail";
				}
			
			map.put ("log", log);
			map.put ("shows", service.getShowsByDate (date1, date2));
			
			return "shows";
		} 
		catch (ParseException e) 
		{
			map.put ("msg", "Invalid date format.");
			map.put ("log", log);
			return "fail";
		}
		
	}
}
