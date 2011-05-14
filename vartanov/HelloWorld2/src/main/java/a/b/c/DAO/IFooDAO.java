package a.b.c.DAO;

import java.util.List;

import a.b.c.domain.Foo;
import a.b.c.domain.IFoo;

public interface IFooDAO {
	public void save(Foo foo);
	public List<IFoo> getList();
}
