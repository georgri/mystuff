package schoolschedule.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class Lesson implements Serializable
{
	private static final long serialVersionUID = 550815071274851005L;
		  
	private int id;
	private int courseId;
	private int professorId;
	private int audienceId;
	private Date time;
	
	private Set<Student> students;
	  
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("Id: " + id + "; ");
		buffer.append("Course id: " + courseId + "; ");
		buffer.append("Professor id: " + professorId + "; ");
		buffer.append("Audience id: " + audienceId + "; ");
		buffer.append("Time: " + time);
		
		return buffer.toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getProfessorId() {
		return professorId;
	}

	public void setProfessorId(int professorId) {
		this.professorId = professorId;
	}

	public int getAudienceId() {
		return audienceId;
	}

	public void setAudienceId(int audienceId) {
		this.audienceId = audienceId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}
}
