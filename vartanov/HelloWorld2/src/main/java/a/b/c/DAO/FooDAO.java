package a.b.c.DAO;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import a.b.c.domain.Foo;
import a.b.c.domain.IFoo;

@Repository
public class FooDAO  implements IFooDAO{
	HibernateTemplate hibernateTemplate;
	
	 @Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
	         hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	public void save(Foo foo) {
		hibernateTemplate.save(foo);
	}

	@SuppressWarnings("unchecked")
	public List<IFoo> getList() {
		return hibernateTemplate.find("from Foo");
	}

}
