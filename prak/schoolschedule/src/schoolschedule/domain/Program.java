/**
 * Copyright (c) 2009 ISP RAS.
 * 109004, A. Solzhenitsina, 25, Moscow, Russia.
 * All rights reserved.
 *
 * $Id$
 * Created on 27.01.2010
 *
 */

package schoolschedule.domain;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Victor Kuliamin
 *
 */
public class Program implements Serializable
{
  private static final long serialVersionUID = 7484714313555728037L;

  private int id;
  private String title;
  private Channel channel;
  private String kind;
  private Date time;
  private int duration;
  private int reccurence;

  public int  getId()                  { return id;    }
  public void setId(int id)            { this.id = id; }

  public String getTitle()             { return title; }
  public void   setTitle(String title) { this.title = title; }

  public Channel getChannel()                { return channel; }
  public void    setChannel(Channel channel) { this.channel = channel; }

  public String getKind()            { return kind; }
  public void   setKind(String kind) { this.kind = kind; }

  public Date   getTime()            { return time; }
  public void   setTime(Date time)   { this.time = time; }
  
  public int  getDuration()          { return duration; }
  public void setDuration(int dur)   { duration = dur;  }

  public int  getReccurence()        { return reccurence; }
  public void setReccurence(int rec) { reccurence = rec;  }
  
  @SuppressWarnings("deprecation")
  public Date getEndTime()            
  { 
    Date stime = getTime(); 
    Calendar cld = Calendar.getInstance();
    
    cld.set(stime.getYear()+1900, stime.getMonth(), stime.getDate(), stime.getHours(), stime.getMinutes(), stime.getSeconds());
    cld.add(Calendar.MINUTE, getDuration());
    
    return cld.getTime(); 
  }
  
  public String getInterval()
  {
    if(getTitle().length() == 0) return "";
    
    DateFormat format = new SimpleDateFormat("HH:mm");
    return format.format(getTime()) + " - " + format.format(getEndTime()); 
  }
  
  public String getStartShortTime()
  {
    if(getTitle().length() == 0) return "";
    
    DateFormat format = new SimpleDateFormat("HH:mm");
    return format.format(getTime()); 
  }

  public String getEndShortTime()
  {
    if(getTitle().length() == 0) return "";
    
    DateFormat format = new SimpleDateFormat("HH:mm");
    return format.format(getEndTime()); 
  }
  
  public String toString()
  {
    if(title == null || "".equals(title)) return "";
    
    StringBuffer buffer = new StringBuffer();
    buffer.append("Id: " + id + "; ");
    buffer.append("Channel: "  + channel.getTitle() + "; ");
    buffer.append("Title: "    + title + "; ");
    buffer.append("Kind: "     + kind + "; ");
    buffer.append("Time: "     + time + "; ");
    buffer.append("Duration: " + duration + "; ");
    buffer.append("Reccurence: " + reccurenceToString(reccurence));
    
    return buffer.toString();
  }
  
  public String reccurenceToString(int rec)
  {
    switch(rec)
    {
      case 1: return "On Monday";
      case 2: return "On Tuesday";
      case 3: return "On Wednesday";
      case 4: return "On Thursday";
      case 5: return "On Friday";
      case 6: return "On Saturday";
      case 7: return "On Sundays";
      default: return "Once";
    }
  }
}
