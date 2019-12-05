// ****************** POJO **********************

package com.app.core;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Volunteer {

	private int id;
	private String name;
	private List<String> hobbies;
	private boolean available;
	private LocalDate dob;
	
	public Volunteer(int id, String name, boolean available, LocalDate dob) {
		super();
		this.id = id;
		this.name = name;
		this.available = available;
		this.dob = dob;
		hobbies = new ArrayList<>();
	}
	
	public void addHobbies(String hobby)
	{
		hobbies.add(hobby);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getHobbies() {
		return hobbies;
	}

	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "Volunteer [id=" + id + ", name=" + name + ", hobbies=" + hobbies + ", available=" + available + ", dob="
				+ dob + "]";
	}
	
	
	
}























// ****************** EXCEPTION **********************

package exception;

public class VolunteerException extends Exception {

	public VolunteerException(String errMsg) {
		super(errMsg);
	}
	
}






















// ****************** UTILS 1.1 - IO UTILS **********************

package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import static utils.VolunteerData.populateData;
import com.app.core.Volunteer;

public class IOUtils {

	@SuppressWarnings("unchecked")
	public static HashMap<Integer, Volunteer> readData() throws Exception 
	{
		HashMap<Integer, Volunteer> hm = new HashMap<Integer, Volunteer>();
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("volunteers.txt")))
		{
			hm = (HashMap<Integer, Volunteer>) in.readObject();
		}catch(FileNotFoundException e)
		{
			return populateData(hm);
		}
		return hm;
	}
	
	public static String writeData(HashMap<Integer, Volunteer> hm) throws IOException
	{
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("volunteers.txt")))
		{
			out.writeObject(hm);
		} 
		return "Data Persisted!!";
	}
}




















// ****************** UTILS 1.2 - Validation Rules **********************

package utils;

import java.time.LocalDate;
import java.util.HashMap;
import static java.time.LocalDate.*;
import com.app.core.Volunteer;

import exception.VolunteerException;

public class ValidationRules {

	public static String validateName(String name,HashMap<Integer, Volunteer> hm) throws Exception
	{
		for(Volunteer v : hm.values())
			if(v.getName().equalsIgnoreCase(name))
				throw new VolunteerException("Volunteer with same name already exists!!");
		return name;
	}
	
	public static LocalDate validateDob(String dob,HashMap<Integer, Volunteer> hm) throws Exception
	{
		LocalDate dt = parse(dob);
		for(Volunteer v : hm.values())
			if(v.getDob().equals(dob))
				throw new VolunteerException("Volunteer with same name already exists!!");
		if(dt.isBefore(parse("1980-01-01")))
			throw new VolunteerException("DOB should be after 1979-12-31");
		return dt;
	}
	
}





















// ****************** UTILS 1.3 - Volunteer Data **********************

package utils;

import java.util.HashMap;
import static utils.ValidationRules.*;
import com.app.core.Volunteer;

public class VolunteerData {

	public static HashMap<Integer, Volunteer> populateData(HashMap<Integer, Volunteer> hm) throws Exception
	{
		hm.put(111, new Volunteer(111, validateName("Akash", hm), true, validateDob("1993-09-03", hm)));
		hm.put(222, new Volunteer(222, validateName("Natasha", hm), true, validateDob("1993-04-30", hm)));
		hm.put(333, new Volunteer(333, validateName("Rajesh", hm), false, validateDob("1983-10-10", hm)));
		hm.put(444, new Volunteer(444, validateName("Madhu", hm), true, validateDob("1988-04-14", hm)));
		hm.put(555, new Volunteer(555, validateName("Shefali", hm), false, validateDob("1996-08-28", hm)));
		hm.put(666, new Volunteer(666, validateName("Somesh", hm), false, validateDob("1992-03-23", hm)));
		hm.put(777, new Volunteer(777, validateName("Lukesh", hm), true, validateDob("1995-06-06", hm)));
		hm.put(888, new Volunteer(888, validateName("Mahesh", hm), true, validateDob("1993-07-03", hm)));
		hm.put(999, new Volunteer(999, validateName("Ayush", hm), false, validateDob("1995-07-05", hm)));
		hm.put(1111, new Volunteer(1111, validateName("Aakash", hm), true, validateDob("1993-09-28", hm)));
		hm.get(111).addHobbies("Cricket");
		hm.get(222).addHobbies("Football");
		hm.get(333).addHobbies("Tennis");
		hm.get(444).addHobbies("Painting");
		hm.get(111).addHobbies("Photography");
		hm.get(555).addHobbies("Golf");
		hm.get(666).addHobbies("Chess");
		hm.get(777).addHobbies("Cricket");
		hm.get(888).addHobbies("Photography");
		hm.get(999).addHobbies("Soccer");
		return hm;
	}
	
}



















// ****************** TESTER **********************


package tester;
import static utils.ValidationRules.*;

import java.util.HashMap;
import java.util.Scanner;

import com.app.core.Volunteer;

import exception.VolunteerException;

import static utils.IOUtils.*;


public class TestVolunteer {

	public static void main(String[] args) {
		
		try(Scanner sc = new Scanner(System.in))
		{
			HashMap<Integer, Volunteer> volunteers = readData();
			boolean exit = false;
			while(!exit)
			{
				try {
					System.out.println("***MENU****");
					System.out.println("1.Update Volunteer status\n"
							+ "2.Volunteer with same hobbies...\n"
							+ "3.Exit");
					System.out.println("Enter ur choice:");
					switch (sc.nextInt()) {
					case 1:
					{
						System.out.println("Enter volunter ID and status:");
						int id = sc.nextInt();
						boolean status = sc.nextBoolean();
						if(volunteers.get(id)==null)
							throw new VolunteerException("No Volunteers found");
						volunteers.get(id).setAvailable(status);
						System.out.println(volunteers.get(id));
					}
						break;
					case 2:
					{
						for(Volunteer v: volunteers.values())
						{
							
						}
					}
						break;
					case 3:
					{
						exit = true;
						writeData(volunteers);
						System.out.println("Thanks...");
					}
						break;
					default:
						System.out.println("Invalid Input");
						break;
					}
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				sc.nextLine();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
