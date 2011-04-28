package schoolschedule.service;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import schoolschedule.domain.Channel;
import schoolschedule.domain.Program;

public class ProgramManager
{
  private SessionFactory sessionFactory;

  public SessionFactory getSessionFactory()                    { return sessionFactory; }
  public void setSessionFactory(SessionFactory sessionFactory) { this.sessionFactory = sessionFactory; }
/*
  public static void main(String[] args)
  {
    ProgramManager mgr = new ProgramManager();
    
    if (args[0].equals("store")) 
    {
      mgr.createAndStoreChannel("СТС");
    }
    HibernateUtil.getSessionFactory().close();
  }*/
  
  public void createAndStoreChannel(String title) 
  {
    Session session = sessionFactory.getCurrentSession();
    session.beginTransaction();
    
    Channel theChannel = new Channel();
    theChannel.setTitle(title);
    
    session.save(theChannel);
    
    session.getTransaction().commit();
  }
  
  
  @SuppressWarnings("unchecked")
  public List<Channel> getChannels()
  {
    Session session = sessionFactory.getCurrentSession();
    
    return (List<Channel>) session.createQuery("from Channel").list();
  }
  
  public Channel getChannel(String title)
  {
    Session session = sessionFactory.getCurrentSession();
    
    List<?> res = session.createCriteria(Channel.class)
                         .add(Restrictions.eq("Title", title)).list();
    
    if(res.size() == 0) return null;
    else                return (Channel)res.get(0);
  }
  
  @SuppressWarnings("unchecked")
  public List<Program> getPrograms(Channel c)
  {
    Session session = sessionFactory.getCurrentSession();
    
    Criteria criteria = session.createCriteria(Program.class)
                               .addOrder(Property.forName("Time").asc())
                               .createCriteria("Channel")
                               .add(Restrictions.eq("id", c.getId()));
    
    return (List<Program>)criteria.list();
  }

  @SuppressWarnings("unchecked")
  public List<Program> getDayPrograms(Channel c, Calendar d)
  {
    Session session = sessionFactory.getCurrentSession();
    Calendar lo = (Calendar)d.clone(), hi = (Calendar)d.clone();
    lo.set(d.get(Calendar.YEAR), d.get(Calendar.MONTH), d.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
    hi.set(d.get(Calendar.YEAR), d.get(Calendar.MONTH), d.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
    hi.add(Calendar.DAY_OF_MONTH, 1);
    
    Criteria criteria = session.createCriteria(Program.class)
                               .addOrder(Property.forName("Time").asc())
                               .add(Restrictions.between("Time", lo.getTime(), hi.getTime()))
                               .createCriteria("Channel")
                               .add(Restrictions.eq("id", c.getId()));
    
    return (List<Program>)criteria.list();
  }
  /**
   * @return
   */
  public Channel getChannelById(int id)
  {
    Session session = sessionFactory.getCurrentSession();
    
    List<?> res = session.createCriteria(Channel.class)
                         .add(Restrictions.eq("id", id)).list();
    
    if(res.size() == 0) return null;
    else                return (Channel)res.get(0);
  }
}
