package a.b.c.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="foo")
public class Foo implements IFoo, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="fooId")
	@GeneratedValue
	Integer Id; 
	
	@Column(name="str")
	String Str;
	
	public Foo(){
		
	}
	public Foo(Integer id, String str){
		this.Id = id; 
		this.Str = str;
	}
	
	public int getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getStr() {
		return Str;
	}
	public void setStr(String str) {
		Str = str;
	}
}
