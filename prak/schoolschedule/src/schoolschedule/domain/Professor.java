package schoolschedule.domain;

import java.io.Serializable;
import java.util.Set;

public class Professor implements Serializable
{
	private static final long serialVersionUID = 550815071274851005L;
		  
	private int id;
	private String firstName;
	private String middleName;
	private String lastName;
	private Set<Course> courses;
	  
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("Id: " + id + "; ");
		buffer.append("First name: " + firstName + "; ");
		buffer.append("Middle name: " + middleName + "; ");
		buffer.append("Last name: " + lastName);
		
		return buffer.toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
}
