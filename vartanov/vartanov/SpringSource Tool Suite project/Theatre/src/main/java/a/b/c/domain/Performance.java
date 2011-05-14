package a.b.c.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;;

@Entity
@Table(name = "PERFORMANCES")
public class Performance implements Serializable
{
	public Performance(int id, String title, Person director, Theatre theatre,
			int duration, List<Show> shows) {
		super();
		this.id = id;
		this.title = title;
		this.director = director;
		this.theatre = theatre;
		this.duration = duration;
		//this.shows = shows;
	}
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PERFORMANCEID")
	@GeneratedValue
	private int id;
	
	@Column(name = "TITLE")
	private String title;
	
	@ManyToOne
	@JoinColumn(name = "DIRECTORID")
	private Person director;
	
	@ManyToOne
	@JoinColumn(name = "THEATREID")
	private Theatre theatre;
	
	@Column(name = "DURATION")
	private int duration;
	
	/*@OneToMany(mappedBy = "id")
	private List <Show> shows = new ArrayList <Show> (); */
	
	public Performance () 
	{
		
	}
	
	public int getId () 
	{
		return id;
	}
	public void setId (int id) 
	{
		this.id = id;
	}
	public String getTitle () 
	{
		return title;
	}
	public void setTitle (String title) 
	{
		this.title = title;
	}
	public Person getDirector () 
	{
		return director;
	}
	public void setDirector (Person director) 
	{
		this.director = director;
	}
	public Theatre getTheatre () 
	{
		return theatre;
	}
	public void setTheatre (Theatre theatre) 
	{
		this.theatre = theatre;
	}
	public int getDuration () 
	{
		return duration;
	}
	public void setDuration (int duration) 
	{
		this.duration = duration;
	}
	/*public void setShows (List <Show> shows) 
	{
		this.shows = shows;
	}
	public List <Show> getShows () 
	{
		return shows;
	}*/
}
