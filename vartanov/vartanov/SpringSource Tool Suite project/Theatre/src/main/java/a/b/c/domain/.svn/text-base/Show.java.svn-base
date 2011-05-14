package a.b.c.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SHOWS")
public class Show implements Serializable
{
	public Show(int id, Date date) {
		super();
		this.id = id;
		this.date = date;
	}
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "SHOWID")
	@GeneratedValue
	int id;
	
	@Column(name = "DATE")
	Date date;
	
	@ManyToOne
	@JoinColumn(name = "performanceID")
	private Performance performance;
	
	public Show ()
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
	public Date getDate () 
	{
		return date;
	}
	public void setDate (Date date) 
	{
		this.date = date;
	}

	public void setPerformance (Performance performance) 
	{
		this.performance = performance;
	}

	public Performance getPerformance () 
	{
		return performance;
	}
}
