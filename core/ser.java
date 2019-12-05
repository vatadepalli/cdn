// ****************** POJO ***************************

package com.app.core;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Student implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private int id;
    private static int count;
    private String name;
    private int age;
    private List<String> courses;
    private LocalDate registrationDate;

    public Student(String name, int age, LocalDate registrationDate) {
        super();
        id = ++count;
        this.name = name;
        this.age = age;
        this.registrationDate = registrationDate;
        courses = new ArrayList<String>();
    }

    public void addCourse(String course) {
        courses.add(course);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Student.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", age=" + age + ", courses=" + courses + ", registrationDate="
                + registrationDate + "]";
    }

}















// ****************** UTILS 1.1 - IOUTILS ***************************

package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import static utils.StudentUtils.populateData;
import com.app.core.Student;

public class IOUtils {

	@SuppressWarnings("unchecked")
	public static List<Student> getStudents() throws  Exception
	{
		List<Student> l1 = null;
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("students.ser")))
		{
			l1=(List<Student>) in.readObject();
		}catch(FileNotFoundException e)
		{
			return populateData();
		}
		return l1;
	}
	
	public static String persistStudents(List<Student> students) throws FileNotFoundException, IOException
	{
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("students.ser")))
		{
			out.writeObject(students);
		}
		return "Student details persisted!!";
	}
}















// ****************** UTILS 1.2 - STUDENT UTILS ***************************

package utils;

import java.util.ArrayList;
import static utils.ValidationUtils.*;
import com.app.core.Student;


public class StudentUtils {

	public static ArrayList<Student> populateData() throws Exception
	{
		ArrayList<Student> l1 = new ArrayList<>();
		l1.add(new Student("Akash", 26, validateDate("2019-07-07")));
		l1.get(0).addCourse(validateCourse("DAC",l1.get(0)));
		l1.get(0).addCourse(validateCourse("DBDA",l1.get(0)));
		l1.add(new Student("Pankaj", 24, validateDate("2019-08-01")));
		l1.get(1).addCourse(validateCourse("DAC",l1.get(1)));
		l1.add(new Student("Bhanu", 25, validateDate("2018-07-07")));
		l1.get(2).addCourse(validateCourse("DAC",l1.get(2)));
		l1.add(new Student("Niraj", 24, validateDate("2017-07-07")));
		l1.get(3).addCourse(validateCourse("DESD",l1.get(3)));
		return l1;
	}
	
}














// ****************** UTILS 1.3 - VALIDATION UTILS ***************************

package utils;

import java.time.LocalDate;

import com.app.core.Student;

import exception.StudentException;

public class ValidationUtils {

	public static LocalDate validateDate(String regDate) throws Exception
	{
		LocalDate date = LocalDate.parse(regDate);
		if(date.isAfter(LocalDate.now()))
			throw new StudentException("Registration must be before current date");
		return date;
	}
	
	public static String validateCourse(String course, Student s) throws Exception
	{
		for(String c : s.getCourses())
		{
			if(course.equalsIgnoreCase(c))
				throw new StudentException("Already enrolled in the given course!!");
		}
		return course;
	}
	
}















// ****************** EXCEPTION ***************************

package exception;

public class StudentException extends Exception {

	public StudentException(String errMsg) {
		super(errMsg);
	}
	
}















// ****************** TEST ***************************

package tester;
import static utils.ValidationUtils.*;
import static utils.IOUtils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.app.core.Student;


public class TestStudent {

	public static void main(String[] args) {
		
		try (Scanner sc = new Scanner(System.in))
		{
			List<Student> students = getStudents();
			boolean exit = false;
			while(!exit)
			{
				try {
					System.out.println("*****MENU*****");
					System.out.println("1.Enter New User");
					System.out.println("2.Enter A new Course");
					System.out.println("3.Course-wise user list");
					System.out.println("4.Show all Students");
					System.out.println("5.Exit");
					System.out.println("Enter ur choice:");
					switch (sc.nextInt()) {
					case 1:
					{
						System.out.println("Enter students details(name,age,reg_date,course)");
						students.add(new Student(sc.next(), sc.nextInt(), validateDate(sc.next())));
						students.get(students.size()-1).addCourse(validateCourse(sc.next(), students.get(students.size()-1)));
						System.out.println("Added Successfully!!");
					}
						break;
					case 2:
					{
						System.out.println("ENter student ID and course in which u want to enroll:");
						int id = sc.nextInt();
						students.get(id-1).addCourse(validateCourse(sc.next(), students.get(id-1)));
						System.out.println("Course Added Successfully!!");
					}
						break;
					case 3:
					{
						System.out.println("Course-wise student list(Enter Course name): ");
						String cName=sc.next();
						List<Student> l1 = new ArrayList<>();
						for(Student s: students)
						{
							for(String c : s.getCourses())
							{
								if(cName.equalsIgnoreCase(c))
									l1.add(s);
							}
						}
						for(Student s : l1)
							System.out.println(s);
						
					}
						break;
					case 4:
						for(Student s: students)
							System.out.println(s);
						break;
					case 5:
						exit = true;
						System.out.println("Thanks");
						System.out.println(persistStudents(students));
						
						break;
						
					default:
						System.out.println("Invalid Input!!");
						break;
					}
					
				}catch (Exception e) {
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
