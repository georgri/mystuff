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
@Table(name = "COSTS")
public class Cost implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "COSTID")
	@GeneratedValue
	int id;
	
	@Column(name = "TYPE")
	int type;
	
	@Column(name = "PRICE")
	private int price;
	
	@ManyToOne//(cascade = CascadeType.ALL)
	@JoinColumn(name = "SHOWID")
	private Show show; 
	
	public Cost ()
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
	public int getType () 
	{
		return type;
	}
	public void setType (int type) 
	{
		this.type = type;
	}
	public void setPrice (int price) 
	{
		this.price = price;
	}
	public int getPrice () 
	{
		return price;
	}
	public void setShow (Show show) 
	{
		this.show = show;
	}
	public Show getShow () 
	{
		return show;
	}
}
