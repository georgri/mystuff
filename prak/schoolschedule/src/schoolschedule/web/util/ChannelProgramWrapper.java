/**
 * Copyright (c) 2009 ISP RAS.
 * 109004, A. Solzhenitsina, 25, Moscow, Russia.
 * All rights reserved.
 *
 * $Id$
 * Created on 15.02.2010
 *
 */

package schoolschedule.web.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import schoolschedule.domain.Channel;
import schoolschedule.domain.Program;

/**
 * @author Victor Kuliamin
 *
 */
public class ChannelProgramWrapper
{
  List<Integer>        indexer  = new ArrayList<Integer>();
  List<ProgramWrapper> programs = new ArrayList<ProgramWrapper>();
  Channel              channel;
  Calendar             calendar;
  
  public ChannelProgramWrapper() {}
  public ChannelProgramWrapper(List<Program> prgs)
  {
    if(prgs != null)
    {
      for(int i = 0; i < prgs.size(); i++)
      {
        indexer.add(i);
        programs.add(new ProgramWrapper(this, prgs.get(i)));
      }
    }
  }
  
  public List<Integer>      getIndexer()  { return indexer;  }
  public List<ProgramWrapper> getItems()  { return programs;  }
  
  public String getStartShortTime(int i)         { return programs.get(i).getStartShortTime(); }
  public void setStartShortTime(int i, String s) { programs.get(i).setStartShortTime(s); }

  public String getEndShortTime(int i)    { return programs.get(i).getEndShortTime(); }
  public void setEndShortTime(int i, String s) { programs.get(i).setEndShortTime(s); }
  
  public Channel getChannel()             { return channel;  }
  public void setChannel(Channel channel) { this.channel = channel; }
  
  public Calendar getCalendar()           { return calendar;  }
  public void setCalendar(Calendar calendar) { this.calendar = calendar; }
  
}
