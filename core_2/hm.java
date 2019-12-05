// ********************** POJO **********************

package com.app.core;

import java.time.LocalDate;

public class Employee {

	//id, name, department, dob, hireDate and salary 
	private String id,name,dept;
	private LocalDate dob,hireDate;
	private double salary;
	
	public Employee(String id, String name, String dept, LocalDate dob, LocalDate hireDate, double salary) {
		super();
		this.id = id;
		this.name = name;
		this.dept = dept;
		this.dob = dob;
		this.hireDate = hireDate;
		this.salary = salary;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public LocalDate getHireDate() {
		return hireDate;
	}

	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", dept=" + dept + ", dob=" + dob + ", hireDate=" + hireDate
				+ ", salary=" + salary + "]";
	}
}

















// ********************** EXCEPTION **********************

package exception;

public class EmployeeNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmployeeNotFoundException() {
		super("Employee Not Found!!");
	}
	
	public EmployeeNotFoundException(String errMsg)
	{
		super(errMsg);
	}
	
}

















// ********************** UTILS **********************

package utils;

import java.util.HashMap;
import static java.time.LocalDate.*;
import com.app.core.Employee;

public class EmpUtils {

	public static HashMap<String, Employee> populateMap()
	{
		HashMap<String, Employee> emps = new HashMap<String, Employee>();
		emps.put("id-111", new Employee("id-111", "aku", "dev", parse("1993-09-03"), parse("2019-09-03"), 45000));
		emps.put("id-222", new Employee("id-222", "panku", "tester", parse("1996-08-03"), parse("2020-07-03"), 35000));
		emps.put("id-333", new Employee("id-333", "bhanu", "dev", parse("1992-02-03"), parse("2017-05-03"), 50000));
		emps.put("id-444", new Employee("id-444", "niru", "manager", parse("1990-03-03"), parse("2017-11-03"), 60000));
		emps.put("id-555", new Employee("id-555", "loku", "dev", parse("1993-10-03"), parse("2017-09-03"), 55000));
		return emps;
	}
	
}
















// ********************** TESTER **********************

package tester;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import static java.time.LocalDate.*;
import com.app.core.Employee;
import static utils.EmpUtils.*;
import exception.EmployeeNotFoundException;

public class EmpService {

	public static void main(String[] args) {
		boolean exit = false;
		Map<String, Employee> emps = populateMap();
		try(Scanner sc = new Scanner(System.in))
		{
			while(!exit)
			{
				System.out.println("******Menu******");
				System.out.println("1.Add Employee:\n2.View Employee\n"
						+ "3.Display all the employees in ascending order with respect to date of joining\n"
						+ "4.Delete Employee\n"
						+ "5.Exit");
				System.out.println("Enter one Option...");
				try {
					switch (sc.nextInt()) {
					case 1:
					{
						System.out.println("Enter Emp details(id, name, department, dob, hireDate and salary):");
						String id = sc.next();
						if(emps.putIfAbsent(id, new Employee(id, sc.next(), sc.next(), parse(sc.next()), parse(sc.next()), sc.nextDouble())) == null)
						{
							System.out.println("Employee with id="+id+" added successfully!!!");
							break;
						}
						throw new EmployeeNotFoundException("Emp with id="+id+" already exists!!");
					}
					case 2:
					{
						System.out.println("Enter Emp ID: ");
						Employee e = emps.get(sc.next());
						if( e != null )
						{		
							System.out.println(e);
							break;
						}
						throw new EmployeeNotFoundException();
					}
					case 3:
					{
						ArrayList<Employee> l1 = new ArrayList<Employee>(emps.values()) ;
						Collections.sort(l1, new Comparator<Employee>() {

							@Override
							public int compare(Employee o1, Employee o2) {
								return o1.getHireDate().compareTo(o2.getHireDate());
							}
						});
						for(Employee e : l1)
							System.out.println(e);
					}
						break;
					case 4:
					{
						System.out.println("Enter Emp ID:");
						Employee e = emps.remove(sc.next());
						if(e == null)
							throw new EmployeeNotFoundException();
						System.out.println("Employee with id="+e.getId()+" removed successfully!!");
					}
						break;
					case 5:
						exit = true;
						System.out.println("thanks for using this app!!");
						break;
					default:
						System.out.println("Invalid input!!");
						break;
					}
				}catch (Exception e) {
					System.out.println(e.getMessage());
				}
				sc.nextLine();
			}
		}

	}

}
