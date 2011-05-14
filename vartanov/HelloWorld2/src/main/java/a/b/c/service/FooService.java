package a.b.c.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import a.b.c.DAO.IFooDAO;
import a.b.c.domain.Foo;
import a.b.c.domain.IFoo;

@Service
@Transactional(readOnly = true)
public class FooService implements IFooService {
	@Autowired
	private IFooDAO dao;  
	
	/*
	public void setDao(IFooDAO dao){
		this.dao = dao;
	}
	*/
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveFoo(Foo foo) {
		dao.save(foo);
	}

	public List<IFoo> getList() {
		return dao.getList();
	}
}
