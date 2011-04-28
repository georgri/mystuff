/**
 * Copyright (c) 2009 ISP RAS.
 * 109004, A. Solzhenitsina, 25, Moscow, Russia.
 * All rights reserved.
 *
 * $Id$
 * Created on 28.01.2010
 *
 */

package schoolschedule.web;

import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import schoolschedule.domain.Channel;
import schoolschedule.domain.Program;
import schoolschedule.service.ProgramManager;
import schoolschedule.web.FullProgramController;
import schoolschedule.web.util.DateWrapper;

/**
 * @author Victor Kuliamin
 *
 */
@Test
public class FullProgramControllerTest
{
  ProgramManager pm;
  
  /*public String convertForEclipseConsole(String s) 
  { 
    try
    {
      //return s;
      return new String(s.getBytes("UTF-8"), "cp1251");
    }
    catch (UnsupportedEncodingException e)
    {
      return null;
    } 
  }*/

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
    FullProgramController controller = new FullProgramController();
    controller.setProgramManager(pm);

    ModelAndView modelAndView = controller.getFullProgram();   
    Assert.assertEquals(modelAndView.getViewName(), "program", "View name should be \'program\'");
    Assert.assertNotNull(modelAndView.getModel(), "Model shouldn't be null");

    //System.out.println(modelAndView.toString());
    
    Map<String, Object> modelMap = modelAndView.getModel();
    Assert.assertNotNull(modelMap);
    
    for(String s : modelMap.keySet()) System.out.println(s);
    
    List<Channel> channels = (List<Channel>)modelMap.get("channels");
    Assert.assertNotNull(channels, "Channels shouldn't be null");
    
    for(Channel c : channels) System.out.println(c);
    
    Map<Channel, List<Program>> programs = (Map<Channel, List<Program>>)modelMap.get("programs");
    Assert.assertNotNull(programs, "Programs shouldn't be null");
    
    for(Channel c : channels)
    {
      for(Program p : programs.get(c))
      {
        if(!p.getTitle().equals("")) System.out.println(p);
      }
    }
  }
  
  @SuppressWarnings("unchecked")
  @Test
  public void testParameterizedHandleRequestView() throws Exception
  {   
    FullProgramController controller = new FullProgramController();
    controller.setProgramManager(pm);

    ModelAndView modelAndView = controller.getFullProgram(new DateWrapper("11-02-2010"));   
    Assert.assertEquals(modelAndView.getViewName(), "program", "View name should be \'program\'");
    Assert.assertNotNull(modelAndView.getModel(), "Model shouldn't be null");

    Map<String, Object> modelMap = modelAndView.getModel();
    Assert.assertNotNull(modelMap);
    
    for(String s : modelMap.keySet()) System.out.println(s);
    
    List<Channel> channels = (List<Channel>)modelMap.get("channels");
    Assert.assertNotNull(channels, "Channels shouldn't be null");
    
    for(Channel c : channels) System.out.println(c);
    
    Map<Channel, List<Program>> programs = (Map<Channel, List<Program>>)modelMap.get("programs");
    Assert.assertNotNull(programs, "Programs shouldn't be null");
    
    for(Channel c : channels)
    {
      for(Program p : programs.get(c))
      {
        if(!p.getTitle().equals("")) System.out.println(p);
      }
    }
  }  
}
