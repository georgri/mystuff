package schoolschedule.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import schoolschedule.domain.Lesson;
import schoolschedule.domain.Professor;
import schoolschedule.domain.Student;

public class LessonManager
{
	@Autowired
	private SessionFactory sessionFactory;

  public SessionFactory getSessionFactory()                    { return sessionFactory; }
  public void setSessionFactory(SessionFactory sessionFactory) { this.sessionFactory = sessionFactory; }
  
  @SuppressWarnings("unchecked")
  public List<Lesson> getAll() {
	   Session session = sessionFactory.getCurrentSession();
	   
	   return (List<Lesson>) session.createQuery("from Lesson").list();
  }
  
  @SuppressWarnings("unchecked")
  public List<Lesson> getByProfessor(Integer professorId) {
	Session session = sessionFactory.getCurrentSession();
	   
	return (List<Lesson>) session.createCriteria(Lesson.class)
		.add(Restrictions.eq("professorId", professorId)).list();
  }
  
  @SuppressWarnings("unchecked")
  public List<Lesson> getByCourse(Integer courseId) {
	Session session = sessionFactory.getCurrentSession();
	   
	return (List<Lesson>) session.createCriteria(Lesson.class)
		.add(Restrictions.eq("courseId", courseId)).list();
  }
  
  @SuppressWarnings("unchecked")
  public List<Lesson> getByAudience(Integer audienceId) {
	Session session = sessionFactory.getCurrentSession();
	   
	return (List<Lesson>) session.createCriteria(Lesson.class)
		.add(Restrictions.eq("Scope", scope)).list();
  }
  
  @SuppressWarnings("unchecked")
  public List<Lesson> getByIntensity(Integer intensity) {
	Session session = sessionFactory.getCurrentSession();
	   
	return (List<Lesson>) session.createCriteria(Lesson.class)
		.add(Restrictions.eq("Intensity", intensity)).list();
  }
  
  @SuppressWarnings("unchecked")
  public List<Lesson> getByYear(Short year) {
	Session session = sessionFactory.getCurrentSession();
	   
	return (List<Lesson>) session.createCriteria(Lesson.class)
		.addOrder(Property.forName("LastName").asc())
		.createCriteria("Lessons")
		.add(Restrictions.eq("id", year))
		.list();
  }
  
  @SuppressWarnings("unchecked")
  public List<Lesson> getLessons(String lessonId) {
	Session session = sessionFactory.getCurrentSession();
	   
	return (List<Lesson>) session.createCriteria(Lesson.class)
		.addOrder(Property.forName("time").asc())
		.add(Restrictions.eq("lessonId", lessonId))
		.list();
  }
  
  public Lesson get(String id) {
	Session session = sessionFactory.getCurrentSession();
	   
	return (Lesson) session.get(Lesson.class, id);
  }
  
  public void saveOrCreate(Lesson lesson) {
	  if (isCorrect(lesson)) {
		    Session session = sessionFactory.getCurrentSession();
		    session.beginTransaction();
		    		    
		    session.save(lesson);
		    
		    session.getTransaction().commit();		  
	  }
  }
  
  public void delete(String lessonId) {
    Session session = sessionFactory.getCurrentSession();
    session.beginTransaction();
    		    
    Lesson lesson = (Lesson)session.get(Lesson.class, lessonId);
    
    for (Student student : lesson.getStudents()) {
    	student.getLessons().remove(lesson);
    }
    
    for (Professor professor : lesson.getProfessors()) {
    	professor.getLessons().remove(lesson);
    }
    
    session.createQuery( "delete Lesson where lessonId = :lessonId" )
         .setString( "lessonId", lessonId )
         .executeUpdate();
        
    lesson.getStudents().clear();
    lesson.getProfessors().clear();
    
    session.delete(lesson);
    
    session.getTransaction().commit();		  
  }
  
  public List<String> whatsWrong(Lesson lesson) {
	  List<String> errors = new ArrayList<String>();
	  if (lesson == null) errors.add("Lesson object is null");
	  else {
		  if (lesson.getTitle() == null || lesson.getTitle().isEmpty())
			  errors.add("Lesson title is empty");
		  if (lesson.getScope() == null || lesson.getScope() < 0)
			  errors.add("Lesson scope is invalid");
		  if (lesson.getIntensity() == null || lesson.getIntensity() <= 0)
			  errors.add("Lesson intensity is empty or not positive");
		  if (lesson.getYear() == null || lesson.getYear() <= 0)
			  errors.add("Lesson year is empty or not positive");
	  }
	  return errors;
  }
  
  public boolean isCorrect(Lesson lesson) {
	  return whatsWrong(lesson).isEmpty();
  }
  
  public boolean addProfessor(String lessonId, String professorId) {
	  return professorManager.addLesson(professorId, lessonId);
  }
  
  public boolean delProfessor(String lessonId, String professorId) {
	  return professorManager.delLesson(professorId, lessonId);
  }

  public boolean addStudent(String lessonId, String studentId) {
	  return studentManager.addLesson(studentId, lessonId);
  }
  
  public boolean delStudent(String lessonId, String studentId) {
	  return studentManager.delLesson(studentId, lessonId);
  }
}
