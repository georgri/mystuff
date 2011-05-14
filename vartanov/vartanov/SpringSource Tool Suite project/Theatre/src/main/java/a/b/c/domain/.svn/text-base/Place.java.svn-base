package a.b.c.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity 
@Table(name = "PLACES")
public class Place implements Serializable
{
	public Place(Theatre t, int row, int number, int type) {
		super();
		this.theatre = t;
		this.row = row;
		this.number = number;
		this.type = type;
	}
	private static final long serialVersionUID = 1L;

	@Id 
	@Column(name = "PLACEID")
	@GeneratedValue
	private int id;
	
	@Column(name = "ROW")
	private int row;
	
	@Column(name = "NUMBER")
	private int number;
	
	@Column(name = "TYPE")
	private int type;
	
	@ManyToOne
	@JoinColumn(name = "THEATREID")
	private Theatre theatre;
	
	public Place ()
	{
		
	}
	
	public Place (int i, int j, int t) 
	{
		row = i;
		number = j;
		type = t;
	}

	public int getId () 
	{
		return id;
	}
	public void setId (int id) 
	{
		this.id = id;
	}
	public int getRow () 
	{
		return row;
	}
	public void setRow (int row) 
	{
		this.row = row;
	}
	public int getNumber () 
	{
		return number;
	}
	public void setNumber (int number) 
	{
		this.number = number;
	}
	public int getType () 
	{
		return type;
	}
	public void setType (int type) 
	{
		this.type = type;
	}

	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}

	public Theatre getTheatre() {
		return theatre;
	}
}
