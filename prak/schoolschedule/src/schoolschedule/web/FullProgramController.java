/**
 * Copyright (c) 2009 ISP RAS.
 * 109004, A. Solzhenitsina, 25, Moscow, Russia.
 * All rights reserved.
 *
 * $Id$
 * Created on 02.10.2009
 *
 */

package schoolschedule.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import schoolschedule.domain.Channel;
import schoolschedule.domain.Program;
import schoolschedule.service.ProgramManager;
import schoolschedule.web.util.DateWrapper;

@Controller
@RequestMapping("/program")
public class FullProgramController 
{
  protected final Log logger = LogFactory.getLog(getClass());
 
  @Autowired
  private ProgramManager programManager;
  
  private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
  
  private int numberOfDatesToChoose = 7;
  
  private Calendar today = Calendar.getInstance();

  /*
  @InitBinder
  public void initBinder(WebDataBinder binder, WebRequest req) 
  {
    dateFormat.setLenient(false);
    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
  }*/
  
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
  
  @ModelAttribute("wdate")
  public DateWrapper initialDate() 
  {
    return new DateWrapper(getStringToday());
  }
  
  @ModelAttribute("today")
  public String getStringToday()
  {
    return dateFormat.format(today.getTime());
  }
  
  @RequestMapping(method = RequestMethod.GET)
  public ModelAndView getFullProgram() throws ParseException
  {
    return getFullProgram(initialDate());
  }
  
  @RequestMapping(method = RequestMethod.GET, params = "sdate")
  public ModelAndView getFullProgram(@RequestParam("sdate") String sdate) throws ParseException
  {
    return getFullProgram(new DateWrapper(sdate));
  }
  
  @RequestMapping(method = RequestMethod.POST)
  public ModelAndView getFullProgram(@ModelAttribute("wdate") DateWrapper wdate) throws ParseException
  {
    Calendar cld = Calendar.getInstance();
    cld.setLenient(true);
    Date date = dateFormat.parse(wdate.getDate());
    cld.setTime(date);
    
    ModelAndView mv = new ModelAndView("program");
    
    List<Channel> channels = programManager.getChannels();
    int maxrows = 0, r;
    Map<Channel, List<Program>> programs = new HashMap<Channel, List<Program>>();
    List<Program> tpr;
    List<Integer> indexer = new ArrayList<Integer>(); 
    Program stubprg = new Program();
    stubprg.setTitle("");
    
    for(Channel ch : channels)
    {
      tpr = programManager.getDayPrograms(ch, cld);
      r   = tpr.size();
      maxrows = Math.max(maxrows, r);
      programs.put(ch, tpr);
    }
    
    for(Channel ch : channels)
    {
      tpr = programs.get(ch);
      for(int i = tpr.size(); i < maxrows; i++) tpr.add(stubprg);
    }
    
    for(int i = 0; i < maxrows; i++) indexer.add(i);
    
    mv.addObject("channels", channels);
    mv.addObject("indexer" , indexer );
    mv.addObject("programs", programs);
    mv.addObject("wdate", wdate);

    logger.info("Returning program view for date " + wdate.getDate());
    return mv;
  }
  
  public void setProgramManager(ProgramManager p)
  {
    programManager = p;
  }
}
