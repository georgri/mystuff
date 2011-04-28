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

import schoolschedule.domain.Program;

/**
 * @author Victor Kuliamin
 *
 */
public class ProgramWrapper
{
  Program               program;
  ChannelProgramWrapper parent;
  
  public ProgramWrapper() {}
  public ProgramWrapper(ChannelProgramWrapper w, Program p) { program = p; parent = w; }
  
  public String getStartShortTime() { return program.getStartShortTime(); }
  public void setStartShortTime(String st) {  }
  
  public String getEndShortTime()   { return program.getEndShortTime(); }
  public void setEndShortTime(String et) {  }
  
  public String getTitle()   { return program.getTitle(); }
  public void setTitle(String t) {  }
  
  
}
