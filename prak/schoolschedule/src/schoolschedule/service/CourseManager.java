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
import schoolschedule.domain.Student;

public class CourseManager
{
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private ProfessorManager professorManager;
	@Autowired
	private StudentManager studentManager;

  public SessionFactory getSessionFactory()                    { return sessionFactory; }
  public void setSessionFactory(SessionFactory sessionFactory) { this.sessionFactory = sessionFactory; }
  
  @SuppressWarnings("unchecked")
  public List<Course> getAll() {
	   Session session = sessionFactory.getCurrentSession();
	   
	   return (List<Course>) session.createQuery("from Course").list();
  }
  
  @SuppressWarnings("unchecked")
  public List<Course> getByProfessor(Integer professorId) {
	Session session = sessionFactory.getCurrentSession();
	   
	return (List<Course>) session.createCriteria(Course.class)
		.add(Restrictions.eq("professorId", professorId)).list();
  }
  
  @SuppressWarnings("unchecked")
  public List<Course> getByTitle(Short title) {
	Session session = sessionFactory.getCurrentSession();
	   
	return (List<Course>) session.createCriteria(Course.class)
		.add(Restrictions.eq("title", title)).list();
  }
  
  @SuppressWarnings("unchecked")
  public List<Course> getByScope(Short scope) {
	Session session = sessionFactory.getCurrentSession();
	   
	return (List<Course>) session.createCriteria(Course.class)
		.add(Restrictions.eq("Scope", scope)).list();
  }
  
  @SuppressWarnings("unchecked")
  public List<Course> getByIntensity(Integer intensity) {
	Session session = sessionFactory.getCurrentSession();
	   
	return (List<Course>) session.createCriteria(Course.class)
		.add(Restrictions.eq("Intensity", intensity)).list();
  }
  
  @SuppressWarnings("unchecked")
  public List<Course> getByYear(Short year) {
	Session session = sessionFactory.getCurrentSession();
	   
	return (List<Course>) session.createCriteria(Course.class)
		.addOrder(Property.forName("LastName").asc())
		.createCriteria("Courses")
		.add(Restrictions.eq("id", year))
		.list();
  }
  
  @SuppressWarnings("unchecked")
  public List<Lesson> getLessons(Integer courseId) {
	Session session = sessionFactory.getCurrentSession();
	   
	return (List<Lesson>) session.createCriteria(Lesson.class)
		.addOrder(Property.forName("time").asc())
		.add(Restrictions.eq("courseId", courseId))
		.list();
  }
  
  public Course get(String id) {
	Session session = sessionFactory.getCurrentSession();
	   
	return (Course) session.get(Course.class, id);
  }
  
  public void saveOrCreate(Course course) {
	  if (isCorrect(course)) {
		    Session session = sessionFactory.getCurrentSession();
		    session.beginTransaction();
		    		    
		    session.save(course);
		    
		    session.getTransaction().commit();		  
	  }
  }
  
  public void delete(Integer courseId) {
    Session session = sessionFactory.getCurrentSession();
    session.beginTransaction();
    		    
    Course course = (Course)session.get(Course.class, courseId);
    
    for (Student student : course.getStudents()) {
    	student.getCourses().remove(course);
    }
    
    for (Professor professor : course.getProfessors()) {
    	professor.getCourses().remove(course);
    }
    
    session.createQuery("delete Lesson where courseId = :courseId")
         .setInteger("courseId", courseId)
         .executeUpdate();
        
    course.getStudents().clear();
    course.getProfessors().clear();
    
    session.delete(course);
    
    session.getTransaction().commit();		  
  }
  
  public List<String> whatsWrong(Course course) {
	  List<String> errors = new ArrayList<String>();
	  if (course == null) errors.add("Course object is null");
	  else {
		  if (course.getTitle() == null || course.getTitle().isEmpty())
			  errors.add("Course title is empty");
		  if (course.getScope() == null || course.getScope() < 0)
			  errors.add("Course scope is invalid");
		  if (course.getIntensity() == null || course.getIntensity() <= 0)
			  errors.add("Course intensity is empty or not positive");
		  if (course.getYear() == null || course.getYear() <= 0)
			  errors.add("Course year is empty or not positive");
	  }
	  return errors;
  }
  
  public boolean isCorrect(Course course) {
	  return whatsWrong(course).isEmpty();
  }
  
  public boolean addProfessor(Integer courseId, Integer professorId) {
	  return professorManager.addCourse(professorId, courseId);
  }
  
  public boolean delProfessor(Integer courseId, Integer professorId) {
	  return professorManager.delCourse(professorId, courseId);
  }

  public boolean addStudent(Integer courseId, Integer studentId) {
	  return studentManager.addCourse(studentId, courseId);
  }
  
  public boolean delStudent(Integer courseId, Integer studentId) {
	  return studentManager.delCourse(studentId, courseId);
  }
}
