package a.b.c.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "THEATRE")
public class Theatre implements ITheatre, Serializable
{
	public Theatre (Integer id, String address, String title, List <Place> places, List <Cost> costs) 
	{
		super();
		this.id = id;
		this.address = address;
		this.title = title;
		//this.places = places;
		//this.costs = costs;
	}

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "THEATREID")
	@GeneratedValue
	Integer id; 
	
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "TITLE")
	private String title;

	/*@OneToMany(mappedBy = "id") 
	private List <Place> places = new ArrayList <Place> ();
	
	@OneToMany(mappedBy = "id")
	private List <Cost> costs = new ArrayList <Cost> ();*/
	
	public Theatre ()
	{
		
	}
	
	public int getId () 
	{
		return id;
	}
	public void setId (Integer id) 
	{
		this.id = id;
	}
	public String getAddress () 
	{
		return address;
	}
	public void setAddress (String address) 
	{
		this.address = address;
	}
	public String getTitle () 
	{
		return title;
	}
	public void setTitle (String title) 
	{
		this.title = title;
	}

	/*public void setPlace (List <Place> places) 
	{
		this.places = places;
	}

	public List <Place> getPlace () 
	{
		return places;
	}

	public void setCosts(List <Cost> costs) 
	{
		this.costs = costs;
	}

	public List <Cost> getCosts() 
	{
		return costs;
	}*/
}
