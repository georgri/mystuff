package a.b.c.service;

import java.util.Date;
import java.util.List;

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

public interface ITheatreManager 
{
	public List <Theatre>     getTheatresByTitle (String title);
	
	public List <Performance> getPerformancesByTheatre (Theatre t);
	public List <Performance> getPerformancesByDirector(String string, String string2);
	public List <Performance> getPerformancesByTitle (String title);
	public List <Performance> getPerformancesByDirector (Integer personId);
	public List <Performance> getPerformancesByActor (Integer personId);
	
	public List <Person>      getActorsByPerformance (Integer performanceId);

	public List <Show>        getShowsByPerformance (int id);
	public List <Show>        getShowsByDate (String from, String to);
	
	public List <Place>       getPlacesByShow (Integer showId);
	public List <Place>       getPlacesByTheatre (Theatre t);
	
	public List <Occupied>    isOccupied (Place p, Show s);

	public List <Cost>        getCostsByShow (Show s);

	public List <Occupied>    getOccupiedsByPlace (Place p);
	public List <Occupied>    getOccupiedsByShow (Show s);

	public List <Role>    	  getRolesByPerformance (Performance p);
	public List <Role>    	  getRolesByPerson (Person p);

	public boolean checkLogin (String login, String password);
	public boolean saveLogin  (Registration registration);

	public boolean boolOccupied(Place place, Show sh);
	
	public void deleteCost (Cost x);
	public void deleteOccupied (Occupied x);
	public void deletePerformance (Performance x);
	public void deletePerson (Person x);
	public void deletePlace (Place x);
	public void deleteRole (Role x);
	public void deleteShow (Show x);
	public void deleteTheatre (Theatre x);
	public void deleteRegistration (Registration x);
}
