package a.b.c.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import a.b.c.domain.Cost;
import a.b.c.domain.Occupied;
import a.b.c.domain.Performance;
import a.b.c.domain.Person;
import a.b.c.domain.Place;
import a.b.c.domain.Registration;
import a.b.c.domain.Role; 
import a.b.c.domain.Show;
import a.b.c.domain.Theatre;

@Service
@Transactional(readOnly = true)
public class TheatreManager implements ITheatreManager 
{
	HibernateTemplate ht;
	
	@Autowired private IDBManager <Performance> performanceDBService;
	@Autowired private IDBManager <Registration>registrationDBService;

	// Session factory
	
	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) 
	{
		ht = new HibernateTemplate(sessionFactory);
	}

	// Theatre

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List <Theatre> getTheatresByTitle (String title) 
	{
		return ht.find ("from Theatre where title='" + title + "'");
	}

	// Performance

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List <Performance> getPerformancesByTheatre (Theatre t)
	{
		return ht.find 
			("from Performance where theatre.id='" + t.getId () + "'");
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List <Performance> getPerformancesByDirector (String firstName, String lastName)
	{
		return ht.find 
			("from Performance where director.firstName='" + firstName + "' AND director.lastName='" + lastName + "'");
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List <Performance> getPerformancesByActor (String firstName, String lastName)
	{
		Person p = (Person) ht.find ("from Person where firstName='" + firstName + "' AND lastName='" + lastName + "'").get (0);
		int personId = p.getId (); 
		List <Role> r = ht.find ("from Role where personid=" + personId);
		List <Performance> ret = new ArrayList <Performance> ();
		
		for (Role rr : r)
			ret.addAll (ht.find ("from Performance where performanceid=" + rr.getPerformance ().getId ()));
		
		return (List <Performance>) ret;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Performance getPerformance (int performanceId) 
	{
		return (Performance) ht.find ("from Performance where performanceid=" + performanceId).get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List <Performance> getPerformancesByTitle (String title) 
	{
		return ht.find ("from Performance where title='" + title + "'");
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List <Performance> getPerformancesByDirector (Integer personId) 
	{
		return ht.find ("from Performance where directorid=" + personId);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List <Performance> getPerformancesByActor (Integer personId) 
	{
		List <Role> rr = ht.find ("from Role where actorid=" + personId);
		List <Performance> pp = new ArrayList <Performance> ();
		
		for (Role r : rr)
			pp.add (performanceDBService.get (r.getPerformance ().getId (), "Performance"));
		
		return pp;
	}

	// Person

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List <Person> getActorsByPerformance (Integer performanceId) 
	{
		List <Role> r = ht.find ("from Role where performanceid=" + performanceId);
		List <Person> ret = new ArrayList <Person> ();
		
		for (Role rr : r)
			ret.addAll (ht.find ("from Person where personid=" + rr.getActor ().getId ()));
		
		return ret;
	}
	
	// Show
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List <Show> getShowsByPerformance (int id)
	{
		return ht.find ("from Show where performanceid=" + id);
	}
	
	// Place
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List <Place> getPlacesByShow (Integer showId) 
	{
		List <Occupied> r = ht.find ("from Occupied where showid=" + showId);
		List <Place> ret = new ArrayList <Place> ();
		
		for (Occupied rr : r)
			ret.addAll (ht.find ("from Place where placeid=" + rr.getPlace ().getId ()));
		
		return ret;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List <Place> getPlacesByTheatre (Theatre t) 
	{
		return ht.find ("from Place where theatreid=" + t.getId ());
	}
	
	// Occupied
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List <Occupied> isOccupied (Place p, Show s)
	{
		return ht.find ("from Occupied where showid=" + s.getId () + " and placeid=" + p.getId ());
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean boolOccupied (Place p, Show s)
	{
		return isOccupied (p, s).get (0).isOccupied ();
	}
	
	// Registration

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean checkLogin (String login, String password) 
	{
		List <Registration> rr = ht.find ("from Registration where login='" + login + "' and password='" + password + "'");
		
		return (rr.size () == 1);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean saveLogin (Registration r) 
	{
		List <Registration> rr = ht.find ("from Registration where login='" + r.getLogin () + "'");
		
		if (rr.size () == 1) return false;
		
		registrationDBService.save (r);
		return true;
	}

	// Cost
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List <Cost> getCostsByShow (Show s)
	{
		return ht.find ("from Cost where showid=" + s.getId ());
	}

	// Date
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List <Show> getShowsByDate (String from, String to)
	{
		return ht.find ("from Show where date BETWEEN '" + from + "' AND '" + to + "'");
	}
	
	// Deleting
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteCost (Cost x)
	{
		ht.flush ();
		ht.clear ();
		ht.delete (x);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteOccupied (Occupied x)
	{
		ht.flush ();
		ht.clear ();
		ht.delete (x);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deletePerformance (Performance x)
	{
		List <Show> ss = getShowsByPerformance (x.getId ());
		for (Show s : ss)
		{
			deleteShow (s);
		}
		
		List <Role> rr = getRolesByPerformance (x);
		for (Role r : rr)
		{
			deleteRole (r);
		}
		
		ht.flush ();
		ht.clear ();
		ht.delete (x);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deletePerson (Person x)
	{
		List <Role> rr = getRolesByPerson (x);
		for (Role r : rr)
		{
			deleteRole (r);
		}
		
		List <Performance> pp = getPerformancesByDirector (x.getId ());
		for (Performance p : pp)
		{
			p.setDirector (null);
			performanceDBService.save (p);
		}
		
		ht.flush ();
		ht.clear ();
		ht.delete (x);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deletePlace (Place x)
	{
		List <Occupied> oo = getOccupiedsByPlace (x);
		for (Occupied o : oo)
		{
			deleteOccupied (o);
		}
		
		ht.flush ();
		ht.clear ();
		ht.delete (x);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteRole (Role x)
	{
		ht.flush ();
		ht.clear ();
		ht.delete (x);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteShow (Show x)
	{
		List <Cost> cc = getCostsByShow (x);
		for (Cost c : cc)
		{
			deleteCost (c);
		}
		
		List <Occupied> oo = getOccupiedsByShow (x);
		for (Occupied o : oo)
		{
			deleteOccupied (o);
		}
		
		ht.flush ();
		ht.clear ();
		ht.delete (x);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteTheatre (Theatre x)
	{
		List <Place> pp = getPlacesByTheatre (x);
		for (Place p : pp)
		{
			deletePlace (p);
		}
		
		List <Performance> rr = getPerformancesByTheatre (x);
		for (Performance r : rr)
		{
			r.setTheatre (null);
			performanceDBService.save (r);
		}
		
		ht.flush ();
		ht.clear ();
		ht.delete (x);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteRegistration (Registration x)
	{
		ht.flush ();
		ht.clear ();
		ht.delete (x);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List <Occupied> getOccupiedsByPlace (Place p) 
	{
		return ht.find ("from Occupied where placeid=" + p.getId ());
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List <Occupied> getOccupiedsByShow (Show s) 
	{
		return ht.find ("from Occupied where showid=" + s.getId ());
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List <Role> getRolesByPerformance (Performance p) 
	{
		return ht.find ("from Role where performanceid=" + p.getId ());
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List <Role> getRolesByPerson (Person p) 
	{
		return ht.find ("from Role where actorid=" + p.getId ());
	}
}
