package schoolschedule.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import schoolschedule.domain.Course;
import schoolschedule.domain.Lesson;
import schoolschedule.domain.Student;

public class StudentManager
{
  private SessionFactory sessionFactory;

  public SessionFactory getSessionFactory()                    { return sessionFactory; }
  public void setSessionFactory(SessionFactory sessionFactory) { this.sessionFactory = sessionFactory; }
  
  @SuppressWarnings("unchecked")
  public List<Student> getAll() {
	   Session session = sessionFactory.getCurrentSession();
	   
	   return (List<Student>) session.createQuery("from Student").list();
  }
  
  @SuppressWarnings("unchecked")
  public List<Student> getByLastName(String lastName) {
	Session session = sessionFactory.getCurrentSession();
	   
	return (List<Student>) session.createCriteria(Student.class)
		.add(Restrictions.eq("LastName", lastName)).list();
  }
  
  @SuppressWarnings("unchecked")
  public List<Student> getByStream(Short stream) {
	Session session = sessionFactory.getCurrentSession();
	   
	return (List<Student>) session.createCriteria(Student.class)
		.add(Restrictions.eq("Stream", stream)).list();
  }
  
  @SuppressWarnings("unchecked")
  public List<Student> getByGroup(Short group) {
	Session session = sessionFactory.getCurrentSession();
	   
	return (List<Student>) session.createCriteria(Student.class)
		.add(Restrictions.eq("Group", group)).list();
  }
  
  @SuppressWarnings("unchecked")
  public List<Student> getByCourse(Short course) {
	Session session = sessionFactory.getCurrentSession();
	   
	return (List<Student>) session.createCriteria(Student.class)
		.add(Restrictions.eq("Course", course)).list();
  }
  
  @SuppressWarnings("unchecked")
  public List<Student> getByLessonCourse(Integer courseId) {
	Session session = sessionFactory.getCurrentSession();
	   
	return (List<Student>) session.createCriteria(Student.class)
		.addOrder(Property.forName("LastName").asc())
		.createCriteria("Courses")
		.add(Restrictions.eq("id", courseId))
		.list();
  }
  
  @SuppressWarnings("unchecked")
  public List<Student> getByLesson(Integer lessonId) {
	Session session = sessionFactory.getCurrentSession();
	   
	return (List<Student>) session.createCriteria(Student.class)
		.addOrder(Property.forName("LastName").asc())
		.createCriteria("Lessons")
		.add(Restrictions.eq("id", lessonId))
		.list();
  }
  
  public Student get(Integer id) {
	Session session = sessionFactory.getCurrentSession();
	   
	return (Student) session.get(Student.class, id);
  }
  
  public void saveOrCreate(Student student) {
	  if (isCorrect(student)) {
		    Session session = sessionFactory.getCurrentSession();
		    session.beginTransaction();
		    		    
		    session.save(student);
		    
		    session.getTransaction().commit();		  
	  }
  }
  
  public void delete(Integer studentId) {
    Session session = sessionFactory.getCurrentSession();
    session.beginTransaction();
    		    
    Student student = (Student)session.get(Student.class, studentId);
    
    for (Lesson lesson : student.getLessons()) {
    	lesson.getStudents().remove(student);
    }
    
    for (Course course : student.getCourses()) {
    	course.getStudents().remove(student);
    }
    
    student.getCourses().clear();
    student.getLessons().clear();
    
    session.delete(student);
    
    session.getTransaction().commit();		  
  }
  
  public List<String> whatsWrong(Student student) {
	  List<String> errors = new ArrayList<String>();
	  if (student == null) errors.add("Student object is null");
	  else {
		  if (student.getFirstName() == null || student.getFirstName().isEmpty())
			  errors.add("Student first name is empty");
//		  if (student.getMiddleName() == null || student.getMiddleName().isEmpty())
//			  errors.add("Student middle name is empty");
		  if (student.getLastName() == null || student.getLastName().isEmpty())
			  errors.add("Student last name is empty");
		  if (student.getCourse() == null || student.getCourse() <= 0)
			  errors.add("Student course is empty or not positive");
		  if (student.getStream() == null || student.getStream() <= 0)
			  errors.add("Student stream is empty or not positive");
		  if (student.getGroup() == null || student.getGroup() <= 0)
			  errors.add("Student group is empty or not positive");
	  }
	  return errors;
  }
  
  public boolean isCorrect(Student student) {
	  return whatsWrong(student).isEmpty();
  }
  
  public boolean addLesson(Integer studentId, Integer lessonId) {
	  	boolean result = true;
	    Session session = sessionFactory.getCurrentSession();
	    session.beginTransaction();
	    
	    Lesson lesson = (Lesson)session.get(Lesson.class, lessonId);
	    Student student = (Student)session.get(Student.class, studentId);
	    if (lesson != null && student != null) {	    
	    	lesson.getStudents().add(student);
	    	student.getLessons().add(lesson);
	    	session.saveOrUpdate(lesson);
	    	session.saveOrUpdate(student);
	    } else { 
	    	result = false;
	    }
	    
	    session.getTransaction().commit();
	    
	    return result;
  }

  public boolean addCourse(Integer studentId, Integer courseId) {
	  	boolean result = true;
	    Session session = sessionFactory.getCurrentSession();
	    session.beginTransaction();
	    
	    Course course = (Course)session.get(Course.class, courseId);
	    Student student = (Student)session.get(Student.class, studentId);
	    if (course != null && student != null) {	    
	    	course.getStudents().add(student);
	    	student.getCourses().add(course);
	    	session.saveOrUpdate(course);
	    	session.saveOrUpdate(student);
	    } else {
	    	result = false;
	    }
	    
	    session.getTransaction().commit();
	    
	    return result;
  }
  
  public boolean delLesson(Integer studentId, Integer lessonId) {
	  	boolean result = true;
	    Session session = sessionFactory.getCurrentSession();
	    session.beginTransaction();
	    
	    Lesson lesson = (Lesson)session.get(Lesson.class, lessonId);
	    Student student = (Student)session.get(Student.class, studentId);
	    if (lesson != null && student != null) {	    
	    	lesson.getStudents().remove(student);
	    	student.getLessons().remove(lesson);
	    	session.saveOrUpdate(lesson);
	    	session.saveOrUpdate(student);
	    } else {
	    	result = false;
	    }
	    
	    session.getTransaction().commit();
	    return result;
  }
  
  public boolean delCourse(Integer studentId, Integer courseId) {
	  	boolean result = true;
	    Session session = sessionFactory.getCurrentSession();
	    session.beginTransaction();
	    
	    Course course = (Course)session.get(Course.class, courseId);
	    Student student = (Student)session.get(Student.class, studentId);
	    if (course != null && student != null) {	    
	    	course.getStudents().remove(student);
	    	student.getCourses().remove(course);
	    	session.saveOrUpdate(course);
	    	session.saveOrUpdate(student);
	    } else {
	    	result = false;
	    }
	    
	    session.getTransaction().commit();
	    return result;
  }
}
