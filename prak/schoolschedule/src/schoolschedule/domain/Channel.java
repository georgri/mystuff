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
import java.util.Set;

/**
 * @author Victor Kuliamin
 *
 */
public class Channel implements Serializable
{
  private static final long serialVersionUID = 550815071974851005L;
  
  private int id;
  private String title;
  private String logo;
  private Set<Program> programs;

  public int  getId()       { return id;    }
  public void setId(int id) { this.id = id; }

  public String getTitle()              { return title; }
  public void   setTitle(String title)  { this.title = title; }

  public String getLogo()               { return logo; }
  public void   setLogo(String logo)    { this.logo = logo; }

  public Set<Program> getPrograms()                      { return programs; }
  public void         setPrograms(Set<Program> programs) { this.programs = programs; }

  public String getLogoFile()           { return "img/" + logo; }
  
  public String toString()
  {
    StringBuffer buffer = new StringBuffer();
    buffer.append("Id: " + id + "; ");
    buffer.append("Title: " + title);
    return buffer.toString();
  }
}
