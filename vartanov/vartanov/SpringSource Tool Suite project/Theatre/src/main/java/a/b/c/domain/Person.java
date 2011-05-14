package a.b.c.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PERSONS")
public class Person implements Serializable
{
	public Person(int id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PERSONID")
	@GeneratedValue
	int id;
	
	@Column(name = "FIRSTNAME")
	String firstName;
	
	@Column(name = "LASTNAME")
	String lastName;
	
	public Person ()
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
	public String getFirstName () 
	{
		return firstName;
	}
	public void setFirstName (String firstName) 
	{
		this.firstName = firstName;
	}
	public String getLastName () 
	{
		return lastName;
	}
	public void setLastName (String lastName) 
	{
		this.lastName = lastName;
	}
}
