/**
 * Copyright (c) 2009 ISP RAS.
 * 109004, A. Solzhenitsina, 25, Moscow, Russia.
 * All rights reserved.
 *
 * $Id$
 * Created on 28.01.2010
 *
 */

package schoolschedule.domain;

//import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import schoolschedule.domain.Channel;
import schoolschedule.domain.Program;
import schoolschedule.service.ProgramManager;


/**
 * @author Victor Kuliamin
 *
 */
@Test
public class ProgramDBTest
{
  ProgramManager pm;
  Channel channel;
  
  public String convertForEclipseConsole(String s) 
  { 
    //try
    //{
      return s;
      //return new String(s.getBytes("UTF-8"), "cp1251");
    //}
    //catch (UnsupportedEncodingException e)
    //{
    //  return null;
    //} 
  }
  
  @BeforeTest
  public void initProgramManager()
  {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    pm = ctx.getBean(ProgramManager.class);
    
    System.out.println("---------------------------------------------");
    System.out.println("Properties in DB test: ");
    Properties prs = System.getProperties();
    for(Object s : prs.keySet())
    {
      System.out.println(s + " : " + prs.get(s));
    }
    System.out.println("---------------------------------------------");
    Locale lc = Locale.getDefault();
    System.out.println("Default locale in DB test: " + lc.toString() + " : " + lc.getDisplayName());
    Charset cs = Charset.defaultCharset();
    
    System.out.println("Default charset in DB test: " + cs.name());
    System.out.println("Russian text: " + "Это русский текст");
    
    /*
    Charset newcs = Charset.forName("UTF-8");
    System.out.println("Setting new charset: " + newcs.name());
    System.setProperty("file.encoding", "UTF-8");
    System.setProperty("sun.jnu.encoding", "UTF-8");
    
    cs = Charset.defaultCharset();
    System.out.println("New encoding : " + System.getProperty("file.encoding"));
    System.out.println("New charset in DB test: " + cs.name());
    System.out.println("Russian text: " + "Это русский текст");
    */
    System.out.println("---------------------------------------------");

  }
  
  @Test(enabled=false)
  public void testCreateAndStoreChannel()
  {
    pm.createAndStoreChannel("ТНТ");
  }

  @Test
  public void testFindExistingChannel()
  {
    Channel res = pm.getChannel("Первый канал");
    
    Assert.assertNotNull(res, "Первый канал не найден");
    Assert.assertEquals(res.getTitle(), "Первый канал", convertForEclipseConsole("Найден канал с другим именем"));
    
    channel = res;
  }
  
  @Test
  public void testFindNonExistingChannel()
  {
    Channel res = pm.getChannel("Второй канал");
    
    Assert.assertNull(res, convertForEclipseConsole("Второй канал найден"));
  }  
  
  @Test
  public void testGetChannels()
  {
    List<Channel> list = pm.getChannels();
    for(Channel c : list) 
    {
      System.out.println(convertForEclipseConsole(c.toString()));
    }
  }

  @Test(dependsOnMethods="testFindExistingChannel")
  public void testGetPrograms()
  {
    List<Program> list = pm.getPrograms(channel);
    System.out.println("The channel " + convertForEclipseConsole(channel.getTitle()) + " has " + list.size() + " programs");
    
    Assert.assertFalse(list.size() == 0, "List of programs for the first channel shouldn't be empty");
    /*for(Program p : list) 
    {
      System.out.println(convertForEclipseConsole(p.toString()));
    }*/
  }
  
  @Test(dependsOnMethods="testFindExistingChannel")
  public void testGetProgramsForDate()
  {
    Calendar cb = Calendar.getInstance();
    cb.set(2010, 1, 2);

    List<Program> list = pm.getDayPrograms(channel, cb);
    DateFormat format = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
    //String el = System.getProperty("line.separator");
    
    for(Program p : list) 
    {
      System.out.println(p.getId() + ": " + convertForEclipseConsole(p.getTitle()) + " has "
          + "duration " + p.getDuration() + ' '  
          + "start time [" + format.format(p.getTime()) + "] " 
          + "end time [" + format.format(p.getEndTime()) + ']');
    }
  }  
}
