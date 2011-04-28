/**
 * Copyright (c) 2009 ISP RAS.
 * 109004, A. Solzhenitsina, 25, Moscow, Russia.
 * All rights reserved.
 *
 * $Id$
 * Created on 15.02.2010
 *
 */

package schoolschedule.web;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import schoolschedule.domain.Channel;
import schoolschedule.domain.Program;
import schoolschedule.service.ProgramManager;
import schoolschedule.web.ChannelEditController;

/**
 * @author Victor Kuliamin
 *
 */
public class ChannelEditControllerTest
{
  ProgramManager pm;
  
  @BeforeTest
  public void initProgramManager()
  {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    pm = ctx.getBean(ProgramManager.class);
  }
  
  @SuppressWarnings("unchecked")
  @Test
  public void testHandleRequestView() throws Exception
  {   
    ChannelEditController controller = new ChannelEditController();
    controller.setProgramManager(pm);

    ModelAndView modelAndView = controller.getProgram("1", "11-02-2010");   
    Assert.assertEquals(modelAndView.getViewName(), "channel", "View name should be \'program\'");
    Assert.assertNotNull(modelAndView.getModel(), "Model shouldn't be null");

    //System.out.println(modelAndView.toString());
    
    ModelMap modelMap = (ModelMap)modelAndView.getModel();
    //Map<String, Object> modelMap = modelAndView.getModel();
    Assert.assertNotNull(modelMap);
    
    for(String s : modelMap.keySet()) System.out.println(s);
    
    Channel channel = (Channel)modelMap.get("channel");
    Assert.assertNotNull(channel, "Channel shouldn't be null");
    
    System.out.println(channel);
    
    List<Program> programs = (List<Program>)modelMap.get("programs");
    
    for(Program p : programs)
    {
      if(!p.getTitle().equals("")) System.out.println(p);
    }
  }
  
}