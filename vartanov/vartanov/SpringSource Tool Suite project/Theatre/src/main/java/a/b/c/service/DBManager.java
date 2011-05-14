package a.b.c.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import a.b.c.domain.Cost;
import a.b.c.domain.Occupied;
import a.b.c.domain.Performance;
import a.b.c.domain.Person;
import a.b.c.domain.Place;
import a.b.c.domain.Role;
import a.b.c.domain.Show;
import a.b.c.domain.Theatre;

@Service
@Transactional(readOnly = true)
public class DBManager <T> implements IDBManager <T> 
{
	HibernateTemplate ht;
	
	// Session factory
	
	@Autowired 
	public void setSessionFactory (SessionFactory sessionFactory) 
	{
		ht = new HibernateTemplate (sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public T get (int id, String className) 
	{
		return (T) ht.find ("from " + className + " where id=" + id).get (0);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void save (T x)
	{
		ht.saveOrUpdate (x);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List <T> getAll (String className) 
	{
		return ht.find ("from " + className);
	}
}
