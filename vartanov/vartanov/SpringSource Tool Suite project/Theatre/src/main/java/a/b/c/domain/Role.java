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
@Table(name = "ROLES")
public class Role implements Serializable
{
	public Role(int id, Person actor, Performance performance) {
		super();
		this.id = id;
		this.actor = actor;
		this.performance = performance;
	}
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ROLEID")
	@GeneratedValue
	int id;
	
	@ManyToOne
	@JoinColumn(name = "ACTORID")
	Person actor;
	
	@ManyToOne
	@JoinColumn(name = "PERFORMANCEID")
	Performance performance;
	
	public Role ()
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
	public Person getActor () 
	{
		return actor;
	}
	public void setActor (Person actor) 
	{
		this.actor = actor;
	}
	public Performance getPerformance () 
	{
		return performance;
	}
	public void setPerformance (Performance performance) 
	{
		this.performance = performance;
	}
}
