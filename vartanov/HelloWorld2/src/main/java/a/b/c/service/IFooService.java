package a.b.c.service;

import java.util.List;

import a.b.c.domain.Foo;
import a.b.c.domain.IFoo;

public interface IFooService {
	public void saveFoo(Foo foo);
	public List<IFoo> getList();
}
