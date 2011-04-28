package schoolschedule.domain;

import java.io.Serializable;
import java.util.Set;

public class Student implements Serializable
{
	private static final long serialVersionUID = 550815071274851005L;
		  
	private int id;
	private String firstName;
	private String middleName;
	private String lastName;
	private Short course;
	private Short stream;
	private Short group;
	
	private Set<Lesson> lessons;
	private Set<Course> courses;
	  
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("Id: " + id + "; ");
		buffer.append("First name: " + firstName + "; ");
		buffer.append("Middle name: " + middleName + "; ");
		buffer.append("Last name: " + lastName + "; ");
		buffer.append("Course: " + course + "; ");
		buffer.append("Stream: " + stream + "; ");
		buffer.append("Group: " + group);
		
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

	public Short getCourse() {
		return course;
	}

	public void setCourse(Short course) {
		this.course = course;
	}

	public Short getStream() {
		return stream;
	}

	public void setStream(Short stream) {
		this.stream = stream;
	}

	public Short getGroup() {
		return group;
	}

	public void setGroup(Short group) {
		this.group = group;
	}

	public Set<Lesson> getLessons() {
		return lessons;
	}

	public void setLessons(Set<Lesson> lessons) {
		this.lessons = lessons;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
}
