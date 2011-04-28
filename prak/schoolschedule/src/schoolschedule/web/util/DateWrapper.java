/**
 * Copyright (c) 2009 ISP RAS.
 * 109004, A. Solzhenitsina, 25, Moscow, Russia.
 * All rights reserved.
 *
 * $Id$
 * Created on 09.02.2010
 *
 */

package schoolschedule.web.util;

/**
 * @author Victor Kuliamin
 *
 */
public class DateWrapper
{
  private String date;

  public DateWrapper() {}
  
  public DateWrapper(String date)
  {
    this.date = date;
  }
  
  public String getDate()          { return date; }
  public void setDate(String date) { this.date = date; }
}
