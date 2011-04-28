/**
 * Copyright (c) 2009 ISP RAS.
 * 109004, A. Solzhenitsina, 25, Moscow, Russia.
 * All rights reserved.
 *
 * $Id$
 * Created on 10.02.2010
 *
 */

package schoolschedule.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import schoolschedule.domain.Channel;
import schoolschedule.domain.Program;
import schoolschedule.service.ProgramManager;
import schoolschedule.web.util.ChannelProgramWrapper;
import schoolschedule.web.util.DateWrapper;

/**
 * @author Victor Kuliamin
 *
 */
@Controller
@RequestMapping("/channel")
@SessionAttributes("wdate")
public class ChannelEditController
{
  protected final Log logger = LogFactory.getLog(getClass());
  
  @Autowired
  private ProgramManager programManager;
  
  private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
  
  private int numberOfDatesToChoose = 7;
  
  private Calendar today = Calendar.getInstance();
  
  @ModelAttribute("dates")
  public List<String> populateDates() 
  {
    List<String> result = new ArrayList<String>();
    Calendar tmpcl;
    
    for(int i = numberOfDatesToChoose; i >= -numberOfDatesToChoose; i--)
    {
      tmpcl = (Calendar)today.clone();
      tmpcl.setLenient(true);
      tmpcl.add(Calendar.DAY_OF_YEAR, -i);
      
      result.add(dateFormat.format(tmpcl.getTime()));
    }
    
    return result;
  }
  
  @RequestMapping(method = RequestMethod.GET)
  public ModelAndView getProgram(@RequestParam("channelId") String chid, @RequestParam("sdate") String sdate) throws ParseException
  {
    return getProgram(chid, new DateWrapper(sdate));
  }
  
  @RequestMapping(method = RequestMethod.POST)
  public ModelAndView getProgram(@RequestParam("channelId") String chid, @ModelAttribute("wdate") DateWrapper wdate) throws ParseException
  {
    Calendar cld = Calendar.getInstance();
    cld.setLenient(true);
    Date date = dateFormat.parse(wdate.getDate());
    cld.setTime(date);
    
    ModelAndView mv = new ModelAndView("channel");
    
    Channel channel = programManager.getChannelById(Integer.parseInt(chid));
    List<Program> tpr = programManager.getDayPrograms(channel, cld);
    ChannelProgramWrapper pw = new ChannelProgramWrapper(tpr);
    pw.setChannel(channel);
    pw.setCalendar(cld);

    mv.addObject("channel" , channel);
    mv.addObject("programs", pw);
    mv.addObject("wdate", wdate);

    logger.info("Returning channel view for date " + wdate.getDate() + " and channel " + channel.getTitle());
    return mv;
  }
  
  public void setProgramManager(ProgramManager p)
  {
    programManager = p;
  }
  
}
