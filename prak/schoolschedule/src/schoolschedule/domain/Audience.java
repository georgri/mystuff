package schoolschedule.domain;

import java.io.Serializable;

public class Audience implements Serializable
{
	private static final long serialVersionUID = 550815071274851005L;
		  
	private int id;
	private Integer number;
	private int capacity;
	  
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("Id: " + id + "; ");
		buffer.append("Number: " + number + "; ");
		buffer.append("Capacity: " + capacity);
		
		return buffer.toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
}
