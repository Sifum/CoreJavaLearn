package com.javacore.stream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

public class SerialCloneTest {
	public static void main(String args[])
	{
		Employeee harry = new Employeee("Harry Hacker", 35000, 1987, 10, 1);
		Employeee harry2 = (Employeee) harry.clone();
		
		harry.raiseSalary(10);
		
		System.out.println(harry);
		System.out.println(harry2);
	}
}

class SerialCloneable implements Cloneable, Serializable
{
	public Object clone()
	{
		try
		{
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(bout);
			out.writeObject(this);
			out.close();
			
			ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
			ObjectInputStream in = new ObjectInputStream(bin);
			Object ret = in.readObject();
			in.close();
			
			return ret;
			
		}
		catch(Exception e)
		{
			return null;
		}
	}
}


class Employeee extends SerialCloneable
{
	private String name;
	private double salary;
	private Date hireDay;
	
	public Employeee(String n, double s, int year, int month, int day)
	{
		name = n;
		salary = s;
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
		hireDay = calendar.getTime();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public Date getHireDay() {
		return hireDay;
	}
	public void setHireDay(Date hireDay) {
		this.hireDay = hireDay;
	}
	
	public void raiseSalary(double byPercent)
	{
		double raise = salary * byPercent / 100;
		salary += raise;
	}
	
	public String toString()
	{
		return getClass().getName() + "{name=" + name + ",salary=" + salary + ",hireDay=" + hireDay + "}";
	}
}