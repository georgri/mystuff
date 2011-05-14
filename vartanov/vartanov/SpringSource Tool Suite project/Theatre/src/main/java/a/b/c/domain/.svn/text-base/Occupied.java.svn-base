package a.b.c.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "OCCUPIEDS")
public class Occupied implements Serializable
{
	public Occupied(int id, Place place, Show show, boolean isOccupied) {
		super();
		this.id = id;
		this.place = place;
		this.show = show;
		this.isOccupied = isOccupied;
	}
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "OCCUPIEDID")
	@GeneratedValue
	int id;
	
	@ManyToOne//(cascade = CascadeType.ALL)
	@JoinColumn(name = "PLACEID")
	Place place;
	
	@ManyToOne//(cascade = CascadeType.ALL)
	@JoinColumn(name = "SHOWID")
	Show show;
	
	@Column(name = "ISOCCUPIED")
	boolean isOccupied;
	
	public Occupied ()
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
	public Place getPlace () 
	{
		return place;
	}
	public void setPlace (Place place) 
	{
		this.place = place;
	}
	public Show getShow () 
	{
		return show;
	}
	public void setShow (Show show) 
	{
		this.show = show;
	}
	public boolean isOccupied () 
	{
		return isOccupied;
	}
	public void setOccupied (boolean isOccupied) 
	{
		this.isOccupied = isOccupied;
	}
}
