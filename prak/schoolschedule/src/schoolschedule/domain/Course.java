package schoolschedule.domain;

import java.io.Serializable;
import java.util.Set;

import schoolschedule.domain.dictionaries.Scopes;

public class Course implements Serializable
{
	private static final long serialVersionUID = 550815071274851005L;
		  
	private int id;
	private String title;
	private Short scope;
	private Integer intensity;
	private Short year;
	
	private Set<Student> students;
	private Set<Professor> professors;
	  
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("Id: " + id + "; ");
		buffer.append("Title: " + title + "; ");
		buffer.append("Scope: " + Scopes.get(scope) + "; ");
		buffer.append("Hours per week: " + intensity + "; ");
		buffer.append("Year: " + year + "; ");
		buffer.append("Professors: ");
		for (Professor professor : professors) {
			buffer.append(professor.getLastName() + "; ");
		}
		buffer.append("Students: ");
		for (Student student : students) {
			buffer.append(student.getLastName() + "; ");
		}
		
		return buffer.toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Short getScope() {
		return scope;
	}

	public void setScope(Short scope) {
		this.scope = scope;
	}

	public Integer getIntensity() {
		return intensity;
	}

	public void setIntensity(Integer intensity) {
		this.intensity = intensity;
	}

	public Short getYear() {
		return year;
	}

	public void setYear(Short year) {
		this.year = year;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Set<Professor> getProfessors() {
		return professors;
	}

	public void setProfessors(Set<Professor> professors) {
		this.professors = professors;
	}
}
