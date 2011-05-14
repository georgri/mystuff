package a.b.c;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import a.b.c.domain.IFoo;
import a.b.c.service.IFooService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private IFooService service;
	

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home() {
		logger.info("Welcome home!");
		/*
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        IFooService service = (IFooService) ctx.getBean("fooService");*/
        
		
		
        List<IFoo> foo = service.getList();
        for(IFoo f : foo){
        	logger.info(f.getStr());	
        }
        
		
		//logger.info(arg0)
		return "home";
	}
	
}

