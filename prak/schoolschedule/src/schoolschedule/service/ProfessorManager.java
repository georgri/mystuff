package schoolschedule.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import schoolschedule.domain.Course;
import schoolschedule.domain.Lesson;
import schoolschedule.domain.Professor;

public class ProfessorManager
{
	@Autowired
	private SessionFactory sessionFactory;

  public SessionFactory getSessionFactory()                    { return sessionFactory; }
  public void setSessionFactory(SessionFactory sessionFactory) { this.sessionFactory = sessionFactory; }
  
  @SuppressWarnings("unchecked")
  public List<Professor> getAll() {
	   Session session = sessionFactory.getCurrentSession();
	   
	   return (List<Professor>) session.createQuery("from Professor").list();
  }
  
  @SuppressWarnings("unchecked")
  public List<Professor> getByLastName(String lastName) {
	Session session = sessionFactory.getCurrentSession();
	   
	return (List<Professor>) session.createCriteria(Professor.class)
		.add(Restrictions.eq("LastName", lastName)).list();
  }
  
  @SuppressWarnings("unchecked")
  public List<Professor> getByCourse(Integer courseId) {
	Session session = sessionFactory.getCurrentSession();
	   
	return (List<Professor>) session.createCriteria(Professor.class)
		.addOrder(Property.forName("LastName").asc())
		.createCriteria("Courses")
		.add(Restrictions.eq("id", courseId))
		.list();
  }
  
  @SuppressWarnings("unchecked")
  public List<Lesson> getLessons(Integer professorId) {
	Session session = sessionFactory.getCurrentSession();
	   
	return (List<Lesson>) session.createCriteria(Lesson.class)
		.addOrder(Property.forName("time").asc())
		.add(Restrictions.eq("professorId", professorId))
		.list();
  }
  
  public Professor get(String id) {
	Session session = sessionFactory.getCurrentSession();
	   
	return (Professor) session.get(Professor.class, id);
  }
  
  public void saveOrCreate(Professor professor) {
	  if (isCorrect(professor)) {
		    Session session = sessionFactory.getCurrentSession();
		    session.beginTransaction();
		    		    
		    session.save(professor);
		    
		    session.getTransaction().commit();		  
	  }
  }
  
  public void delete(Integer professorId) {
    Session session = sessionFactory.getCurrentSession();
    session.beginTransaction();
    		    
    Professor professor = (Professor)session.get(Professor.class, professorId);
    
    for (Course course : professor.getCourses()) {
    	course.getProfessors().remove(professor);
    }
    
    professor.getCourses().clear();
    
    session.delete(professor);
    
    session.getTransaction().commit();		  
  }
  
  public List<String> whatsWrong(Professor professor) {
	  List<String> errors = new ArrayList<String>();
	  if (professor == null) errors.add("Professor object is null");
	  else {
		  if (professor.getFirstName() == null || professor.getFirstName().isEmpty())
			  errors.add("Professor first name is empty");
//		  if (professor.getMiddleName() == null || professor.getMiddleName().isEmpty())
//			  errors.add("Professor middle name is empty");
		  if (professor.getLastName() == null || professor.getLastName().isEmpty())
			  errors.add("Professor last name is empty");
	  }
	  return errors;
  }
  
  public boolean isCorrect(Professor professor) {
	  return whatsWrong(professor).isEmpty();
  }

  public boolean addCourse(Integer professorId, Integer courseId) {
	  	boolean result = true;
	    Session session = sessionFactory.getCurrentSession();
	    session.beginTransaction();
	    
	    Course course = (Course)session.get(Course.class, courseId);
	    Professor professor = (Professor)session.get(Professor.class, professorId);
	    if (course != null && professor != null) {	    
	    	course.getProfessors().add(professor);
	    	professor.getCourses().add(course);
	    	session.saveOrUpdate(course);
	    	session.saveOrUpdate(professor);
	    } else {
	    	result = false;
	    }
	    
	    session.getTransaction().commit();
	    
	    return result;
  }
  
  public boolean delCourse(Integer professorId, Integer courseId) {
	  	boolean result = true;
	    Session session = sessionFactory.getCurrentSession();
	    session.beginTransaction();
	    
	    Course course = (Course)session.get(Course.class, courseId);
	    Professor professor = (Professor)session.get(Professor.class, professorId);
	    if (course != null && professor != null) {	    
	    	course.getProfessors().remove(professor);
	    	professor.getCourses().remove(course);
	    	session.saveOrUpdate(course);
	    	session.saveOrUpdate(professor);
	    } else {
	    	result = false;
	    }
	    
	    session.getTransaction().commit();
	    return result;
  }
}
