package a.b.c.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "REGISTRATION")
public class Registration implements Serializable
{
	public Registration (int id, String firstName, String lastName) 
	{
		super();
		this.id = id;
		this.login = firstName;
		this.password = lastName;
	}
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "REGISTRATIONID")
	@GeneratedValue
	int id;
	
	@Column(name = "LOGIN")
	String login;
	
	@Column(name = "PASSWORD")
	String password;
	
	public Registration ()
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
	public String getLogin () 
	{
		return login;
	}
	public void setLogin (String login) 
	{
		this.login = login;
	}
	public String getPassword () 
	{
		return password;
	}
	public void setPassword (String password) 
	{
		this.password = password;
	}
}
