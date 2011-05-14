package a.b.c;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import a.b.c.domain.Foo;
import a.b.c.service.IFooService;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        IFooService service = (IFooService) ctx.getBean("fooService");


        for(int i = 0; i < 10; i++){
            Foo foo = new Foo();
            foo.setStr(String.valueOf(Math.random()));
            service.saveFoo(foo);
        }
	}

}
