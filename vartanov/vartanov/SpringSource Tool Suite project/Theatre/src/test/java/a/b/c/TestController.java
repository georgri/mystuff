package a.b.c;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import a.b.c.domain.Cost;
import a.b.c.domain.Occupied;
import a.b.c.domain.Performance;
import a.b.c.domain.Person;
import a.b.c.domain.Place;
import a.b.c.domain.Registration;
import a.b.c.domain.Role;
import a.b.c.domain.Show;
import a.b.c.domain.Theatre;
import a.b.c.service.IDBManager;
import a.b.c.service.ITheatreManager;

@Controller
@ContextConfiguration(locations = {"root-context.xml"}) 
@RunWith(SpringJUnit4ClassRunner.class) 
public class TestController 
{
	@Autowired private ITheatreManager          service;
	
	@Autowired private IDBManager <Theatre> 	theatreDBService;
	@Autowired private IDBManager <Performance> performanceDBService;
	@Autowired private IDBManager <Cost> 		costDBService;
	@Autowired private IDBManager <Person> 		personDBService;
	@Autowired private IDBManager <Occupied> 	occupiedDBService;
	@Autowired private IDBManager <Place> 		placeDBService;
	@Autowired private IDBManager <Show> 		showDBService;
	@Autowired private IDBManager <Role> 		roleDBService;
	
	@Autowired private IDBManager <Registration>registrationDBService;
	
	@Test
	public void main ()
	{
		Assert.assertEquals (2 + 2, 4);
	}
	
	// Role tests
	
	@Test
	public void role ()
	{
		int count = roleDBService.getAll ("Role").size ();
		
		Role r = new Role ();
		Person a = personDBService.get (1, "Person");
		r.setActor (a);
		roleDBService.save (r);
		Person a1 = roleDBService.get (r.getId (), "Role").getActor ();
		Assert.assertEquals (a1.getId (), 1);
		service.deleteRole (r);
		
		Assert.assertEquals (count, roleDBService.getAll ("Role").size ());
	}
	
	// Person tests

	@Test
	public void person ()
	{
		int count = personDBService.getAll ("Person").size ();
		
		Person p = new Person ();
		p.setFirstName ("test");
		personDBService.save (p);
		Assert.assertEquals ("test", personDBService.get (p.getId (), "Person").getFirstName ());
		service.deletePerson (p);
		
		Assert.assertEquals (count, personDBService.getAll ("Person").size ());
	}
	
	// Place tests
	
	@Test
	public void place ()
	{
		Place p = new Place ();
		p.setRow (10);
		p.setNumber (10);
		placeDBService.save (p);
		
		Assert.assertEquals (10, placeDBService.get (p.getId (), "Place").getRow ());
		Assert.assertEquals (10, placeDBService.get (p.getId (), "Place").getNumber ());
		
		service.deletePlace (p);
	}
	
	@Test 
	public void getPlacesByShow ()
	{
		Show x = new Show ();
		showDBService.save (x);

        Place y = new Place ();
		y.setRow (0);
		y.setNumber (0);
		placeDBService.save (y);
		
		Occupied r = new Occupied ();
		r.setShow (x);
		r.setPlace (y);
		occupiedDBService.save (r);
		
		Assert.assertEquals (1, service.getPlacesByShow (x.getId ()).size ());
		
		Place a = service.getPlacesByShow (x.getId ()).get (0);
		Assert.assertEquals (a.getId (), y.getId ());
		Assert.assertEquals (a.getRow (), 0);
		Assert.assertEquals (a.getNumber (), 0);
		
		service.deleteOccupied (r);
		service.deleteShow (x);
		service.deletePlace (y);
	}
	
	@Test
	public void isOccupied ()
	{
		Place p = new Place ();
		placeDBService.save (p);
		
		Show s = new Show ();
		showDBService.save (s);
		
		Occupied o = new Occupied ();
		o.setPlace (p);
		o.setShow (s);
		o.setOccupied (true);
		occupiedDBService.save (o);
		
		Assert.assertEquals (1, service.isOccupied (p, s).size ());
		
		Occupied o1 = service.isOccupied (p, s).get (0);
		Assert.assertEquals (true, o1.isOccupied ());
		
		service.deleteOccupied (o);
		service.deleteShow (s);
		service.deletePlace (p);
	}
	
	@Test
	public void getPlacesByTheatre ()
	{
		Theatre t = new Theatre ();
		theatreDBService.save (t);
		
		Place p = new Place ();
		p.setTheatre (t);
		placeDBService.save (p);
		
		Assert.assertEquals (1, service.getPlacesByTheatre (t).size ());
		
		service.deletePlace (p);
		service.deleteTheatre (t);
	}
	
	// Show tests
	
	@Test
	public void show ()
	{
		int count = showDBService.getAll ("Show").size ();
		
		Show s = new Show ();
		Date d = new Date (10);
		s.setDate (d);
		Performance p = performanceDBService.get (5, "Performance");
		s.setPerformance (p);
		showDBService.save (s);
		service.deleteShow (s);
		
		Assert.assertEquals (count, showDBService.getAll ("Show").size ());
	}
	
	@Test
	public void getShowsByPerformance ()
	{
		int count = showDBService.getAll ("Show").size ();
		
		Performance p = new Performance ();
		performanceDBService.save (p);

		Show s = new Show ();
		s.setPerformance (p);
		showDBService.save (s);
		
		Assert.assertEquals (1, service.getShowsByPerformance (p.getId ()).size ());
		
		Show x = service.getShowsByPerformance (p.getId ()).get (0);
		Assert.assertEquals (s.getId (), x.getId ());

		service.deleteShow (s);
		service.deletePerformance (p);
		
		Assert.assertEquals (count, showDBService.getAll ("Show").size ());
	}
	
	// Theatre tests
	
	@Test
	public void theatre ()
	{
		Theatre x = new Theatre ();
        x.setTitle ("_test_theatre_");
        x.setAddress ("_test_address_");
        theatreDBService.save(x);
        
        Theatre y = theatreDBService.get (x.getId (), "Theatre");
		Assert.assertEquals (y.getAddress (), "_test_address_");
		
		service.deleteTheatre (x);
	}
	
	@Test
	public void getTheatreByTitle ()
	{
		Theatre x = new Theatre ();
        x.setTitle ("_test_");
        x.setAddress ("_test_");
        theatreDBService.save (x);

		Assert.assertEquals (1, service.getTheatresByTitle ("_test_").size ());
		
        Theatre z = service.getTheatresByTitle ("_test_").get (0);
        Assert.assertEquals (z.getId (), x.getId ());
		Assert.assertEquals (z.getAddress (), "_test_");

		service.deleteTheatre (x);
	}
	
	// Performance tests
	
	@Test
	public void performance ()
	{
		int count = performanceDBService.getAll ("Performance").size ();
		
		Performance x = new Performance ();
        x.setTitle ("_test_performance_");
        x.setDuration (314159265);
        
        performanceDBService.save (x);
        
        Performance y = performanceDBService.get (x.getId (), "Performance");
		Assert.assertEquals (y.getDuration (), 314159265);
		
		service.deletePerformance (x);

		Assert.assertEquals (count, performanceDBService.getAll ("Performance").size ());
	}
	
	@Test
	public void getPerformanceByTheatre ()
	{
		int countP = performanceDBService.getAll ("Performance").size ();
		int countT = theatreDBService.getAll ("Theatre").size ();
		
		Performance x = new Performance ();
        x.setTitle ("_test_");
        x.setDuration (314159265);
        
		Theatre t = new Theatre ();
        t.setTitle ("_test_");
        theatreDBService.save (t);
        
        x.setTheatre (t);
        performanceDBService.save (x);
        
		Assert.assertEquals (1, service.getPerformancesByTheatre (t).size ());

		Performance z = service.getPerformancesByTheatre (t).get (0);
		Assert.assertEquals (z.getId (), x.getId ());
		Assert.assertEquals (z.getDuration (), 314159265);
		
		service.deletePerformance (x);
        service.deleteTheatre (t);
		
		Assert.assertEquals (countP, performanceDBService.getAll ("Performance").size ());
		Assert.assertEquals (countT, theatreDBService.getAll ("Theatre").size ());
	}
	
	@Test
	public void getPerformanceByTitle ()
	{
		int count = performanceDBService.getAll ("Performance").size ();
		
		Performance x = new Performance ();
        x.setTitle ("_test_");
        x.setDuration (314159265);
        
        performanceDBService.save (x);
        
		Assert.assertEquals (1, service.getPerformancesByTitle ("_test_").size ());
		
		Performance z = service.getPerformancesByTitle ("_test_").get (0);
        Assert.assertEquals (z.getId (), x.getId ());
		Assert.assertEquals (z.getDuration (), 314159265);

		service.deletePerformance (x);

		Assert.assertEquals (count, performanceDBService.getAll ("Performance").size ());
	}
	
	@Test
	public void getPerformancesByDirector () // both methods
	{
		int countP = performanceDBService.getAll ("Performance").size ();
		int countD = personDBService.getAll ("Person").size ();
		
		Performance x = new Performance ();
        x.setTitle ("_test_");
        x.setDuration (314159265);
        
        Person d = new Person ();
		d.setFirstName ("_test_");
		d.setLastName ("_test_");
		personDBService.save (d);
		
        x.setDirector (d);
        performanceDBService.save (x);
		
		Assert.assertEquals (1, service.getPerformancesByDirector (d.getId ()).size ());

		Performance z = service.getPerformancesByDirector (d.getId ()).get (0);
		Assert.assertEquals (z.getId (), x.getId ());
		Assert.assertEquals (z.getDuration (), 314159265);

		Assert.assertEquals (1, service.getPerformancesByDirector ("_test_", "_test_").size ());

		Performance w = service.getPerformancesByDirector ("_test_", "_test_").get (0);
		Assert.assertEquals (w.getId (), x.getId ());
		Assert.assertEquals (w.getDuration (), 314159265);

		service.deletePerformance (x);
		service.deletePerson (d);
		
		Assert.assertEquals (countP, performanceDBService.getAll ("Performance").size ());
		Assert.assertEquals (countD, personDBService.getAll ("Person").size ());
	}
	
	// Cost tests
	
	@Test
	public void cost ()
	{
		Cost x = new Cost ();
        x.setPrice (100);
        costDBService.save (x);
		service.deleteCost (x);
	}
	
	@Test
	public void getCostsByShow ()
	{
		Show s = new Show ();
		showDBService.save (s);
				
		Cost x = new Cost ();
        x.setPrice (100);
        x.setShow (s);
        costDBService.save (x);
		
        Assert.assertEquals (1, service.getCostsByShow (s).size ());        
        
        service.deleteCost (x);
		service.deleteShow (s);
	}
	
	// Occupied tests
	
	@Test
	public void occupied ()
	{
		Occupied o = new Occupied ();
		o.setOccupied (true);
		occupiedDBService.save (o);
		Occupied o1 = occupiedDBService.get (o.getId(), "Occupied");
		Assert.assertEquals (true, o1.isOccupied ());
		service.deleteOccupied (o);
	}
	
	// Person - performance tests
	
	@Test 
	public void getActorsByPerformance ()
	{
		Performance x = new Performance ();
        performanceDBService.save (x);

		Person y = new Person ();
		y.setFirstName ("_test_");
		y.setLastName ("_test_");
		personDBService.save (y);
		
		Role r = new Role ();
		r.setPerformance (x);
		r.setActor (y);
		roleDBService.save (r);
		
		Assert.assertEquals (1, service.getActorsByPerformance (x.getId ()).size ());
		
		Person a = service.getActorsByPerformance (x.getId ()).get (0);
		Assert.assertEquals (a.getId (), y.getId ());
		Assert.assertEquals (a.getFirstName (), "_test_");
		Assert.assertEquals (a.getLastName (), "_test_");
		
		service.deleteRole (r);
		service.deletePerformance (x);
		service.deletePerson (y);
	}
	
	@Test 
	public void getPerformancesByActor ()
	{
		Performance x = new Performance ();
        x.setTitle ("_test_");
        performanceDBService.save (x);

		Person y = new Person ();
		personDBService.save (y);
		
		Role r = new Role ();
		r.setPerformance (x);
		r.setActor (y);
		roleDBService.save (r);
		
		Assert.assertEquals (1, service.getPerformancesByActor (y.getId ()).size ());
		
		Performance p = service.getPerformancesByActor (y.getId ()).get (0);
		Assert.assertEquals (p.getId (), x.getId ());
		Assert.assertEquals (p.getTitle (), "_test_");
		
		service.deleteRole (r);
		service.deletePerformance (x);
		service.deletePerson (y);
	}
	
	// Registration
	
	@Test
	public void registration ()
	{
		int count = registrationDBService.getAll ("Registration").size ();
		
		Registration r = new Registration ();
		r.setLogin ("_test_");
		r.setPassword ("_test_");
		
		registrationDBService.save (r);
		service.deleteRegistration (r);
		
		Assert.assertEquals (count, registrationDBService.getAll ("Registration").size ());
	}
	
	@Test
	public void saveLogin ()
	{
		int count = registrationDBService.getAll ("Registration").size ();
		
		Registration r = new Registration ();
		r.setLogin ("_test_");
		r.setPassword ("_test_");

		Registration x = new Registration ();
		x.setLogin ("_test_");
		x.setPassword ("_new_test_");

		Assert.assertTrue (service.saveLogin (r));
		Assert.assertFalse (service.saveLogin (x));
		
		service.deleteRegistration (r);
		service.deleteRegistration (x);
		
		Assert.assertEquals (count, registrationDBService.getAll ("Registration").size ());
	}
	
	@Test
	public void checkLogin ()
	{
		Registration r = new Registration ();
		r.setLogin ("_test_");
		r.setPassword ("_test_");

		registrationDBService.save (r);

		Assert.assertTrue (service.checkLogin ("_test_", "_test_"));
		
		service.deleteRegistration (r);
	}
	
	@Test
	public void getShowsByDate ()
	{
		Show s1 = new Show ();
		Show s2 = new Show ();
		Show s3 = new Show ();
				
		DateFormat formatter = new SimpleDateFormat ("yyyy.MM.dd.HH.mm.ss");
	    Date date1, date2, date3;
	    
		try 
		{
			date1 = (Date) formatter.parse ("3000.01.01.00.00.00");
			date2 = (Date) formatter.parse ("3020.01.01.00.00.00");
			date3 = (Date) formatter.parse ("3040.01.01.00.00.00");
			

			s1.setDate (date1);
			s2.setDate (date2);
			s3.setDate (date3);
			
			showDBService.save (s1);
			showDBService.save (s2);
			showDBService.save (s3);

			Assert.assertEquals (1, service.getShowsByDate ("3010.01.01", "3030.01.01").size ());
			
			Assert.assertEquals (s2.getId (), service.getShowsByDate ("3010.01.01", "3030.01.01").get (0).getId ());

			service.deleteShow (s1);
			service.deleteShow (s2);
			service.deleteShow (s3);
		} 
		catch (ParseException e) 
		{
			Assert.assertTrue (false);
		}
	}
	
	@Test
	public void getOccupiedsByPlace ()
	{
		Place p = new Place ();
		placeDBService.save (p);
		
		Show s = new Show ();
		showDBService.save (s);
		
		Occupied o = new Occupied ();
		o.setPlace (p);
		o.setShow (s);
		o.setOccupied (true);
		occupiedDBService.save (o);
		
		Assert.assertEquals (1, service.getOccupiedsByPlace (p).size ());
		
		Occupied o1 = service.getOccupiedsByPlace (p).get (0);
		Assert.assertEquals (true, o1.isOccupied ());
		
		service.deleteOccupied (o);
		service.deleteShow (s);
		service.deletePlace (p);
	}
	
	@Test
	public void getOccupiedsByShow ()
	{
		Place p = new Place ();
		placeDBService.save (p);
		
		Show s = new Show ();
		showDBService.save (s);
		
		Occupied o = new Occupied ();
		o.setPlace (p);
		o.setShow (s);
		o.setOccupied (true);
		occupiedDBService.save (o);
		
		Assert.assertEquals (1, service.getOccupiedsByShow (s).size ());
		
		Occupied o1 = service.getOccupiedsByShow (s).get (0);
		Assert.assertEquals (true, o1.isOccupied ());
		
		service.deleteOccupied (o);
		service.deleteShow (s);
		service.deletePlace (p);
	}
	
	@Test
	public void getRolesByPerson ()
	{
		Performance p = new Performance ();
		performanceDBService.save (p);
		
		Person s = new Person ();
		personDBService.save (s);
		
		Role o = new Role ();
		o.setPerformance (p);
		o.setActor (s);
		roleDBService.save (o);
		
		Assert.assertEquals (1, service.getRolesByPerson (s).size ());
		
		Role o1 = service.getRolesByPerson (s).get (0);
		
		service.deleteRole (o);
		service.deletePerson (s);
		service.deletePerformance (p);
	}
	
	@Test
	public void getRolesByPerformance ()
	{
		Performance p = new Performance ();
		performanceDBService.save (p);
		
		Person s = new Person ();
		personDBService.save (s);
		
		Role o = new Role ();
		o.setPerformance (p);
		o.setActor (s);
		roleDBService.save (o);
		
		Assert.assertEquals (1, service.getRolesByPerformance (p).size ());
		
		Role o1 = service.getRolesByPerformance (p).get (0);
		
		service.deleteRole (o);
		service.deletePerson (s);
		service.deletePerformance (p);
	}	
}
