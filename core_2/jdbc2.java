// ********************** POJOS  ******************************

package pojos;

public class Student {

	private int id;
	private String name,email,address,phoneNo,courseName;
	private double marks;
	
	public Student() {
		System.out.println("in pojo ctor");
	}
	
	public Student(int id, String name, String email, String address, String phoneNo, String courseName, double marks) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.address = address;
		this.phoneNo = phoneNo;
		this.courseName = courseName;
		this.marks = marks;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public double getMarks() {
		return marks;
	}

	public void setMarks(double marks) {
		this.marks = marks;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", email=" + email + ", address=" + address + ", phoneNo="
				+ phoneNo + ", courseName=" + courseName + ", marks=" + marks + "]";
	}
	
	
	
}



















// ********************* DAO INTERFACE  ***********************

package dao;

import java.sql.SQLException;
import java.util.List;

import pojos.Student;

public interface IStudentDao {
	
	public void cleanUp() throws SQLException;
	
	List<Student> getStudentDetails(String courseName) throws Exception;
	
	String updateStudentDetails(int id, double newMarks, String newPhoneNo) throws Exception;
	
	String removeStudent(String email) throws Exception;

}



















// ********************* DAO IMPLEMENTATION  ******************

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import customException.StudentException;
import pojos.Student;
import static utils.DBUtils.*;

public class StudentDao implements IStudentDao {

	private Connection cn;
	private PreparedStatement pst1,pst2,pst3;
	
	public StudentDao() throws ClassNotFoundException, SQLException {
		cn=connectDB();
		pst1 = cn.prepareStatement("select * from students_dac where course_name = ?");
		pst2 = cn.prepareStatement("update students_dac set marks=?, phone_no=? where id = ?");
		pst3 = cn.prepareStatement("delete from students_dac where email=?");
	}
	
	public void cleanUp() throws SQLException
	{
		if(pst1 != null)
			pst1.close();
		if(pst2 != null)
			pst2.close();
		if(pst3 != null)
			pst3.close();
		
		if(cn != null)
			cn.close();
	}
	
	@Override
	public List<Student> getStudentDetails(String courseName) throws Exception {
		List<Student> students = new ArrayList<>();
		pst1.setString(1, courseName);
		try(ResultSet rst = pst1.executeQuery())
		{
			while (rst.next()) {
				students.add(new Student(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), courseName, rst.getDouble(7)));
			}
		}
		if(students.isEmpty())
			throw new StudentException();
		return students;
	}

	@Override
	public String updateStudentDetails(int id, double newMarks, String newPhoneNo) throws Exception {
		pst2.setDouble(1, newMarks);
		pst2.setString(2, newPhoneNo);
		pst2.setInt(3, id);
		int rst = pst2.executeUpdate();
		if(rst == 0)
			throw new StudentException();
		return "Student (with id="+id+") Marks and Phone number updated successfully!!";
	}

	@Override
	public String removeStudent(String email) throws Exception {
		pst3.setString(1, email);
		int rst = pst3.executeUpdate();
		if(rst == 0)
			throw new StudentException();
		return "Student (with id="+email+") removed successfully!!";
	}

	
	
}


















// ************************* EXCEPTION  *********************

package customException;

public class StudentException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8471571172804593371L;

	public StudentException() {
		super("Student not Found!!!");
	}
	
	public StudentException(String errMsg)
	{
		super(errMsg);
	}
	
}


















// ************************* UTILS  *********************

package utils;

import java.sql.*;

public class DBUtils {

	public static Connection connectDB() throws ClassNotFoundException, SQLException
	{
		String url = "jdbc:mysql://localhost:3306/hib_java";
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(url,"root","root1234");
	}
	
}


















// ************************* TESTER  *********************

package tester;

import java.util.List;
import java.util.Scanner;

import dao.IStudentDao;
import dao.StudentDao;
import pojos.Student;

public class TestStudent {

	public static void main(String[] args) {

		boolean exit = false;
		try (Scanner sc = new Scanner(System.in)) {
			IStudentDao dao = new StudentDao();
			while (!exit) {
				System.out.println("*****MENU*****");
				System.out.println("1.Display All students by the specified course name\n"
						+ "2.Update Student details (update marks & phone number)\n" + "3.Cancel Student admission(via email).\n"
						+ "4.Exit");
				System.out.println("Enter any one option:");
				try {
					switch (sc.nextInt()) {
					case 1:
					{
						System.out.println("Enter Course Name: ");
						List<Student> students = dao.getStudentDetails(sc.next());
						for(Student s : students)
							System.out.println(s);
					}
						break;
					case 2:
					{
						System.out.println("Enter Student ID , new Marks and new Phone num to be updated...");
						System.out.println(dao.updateStudentDetails(sc.nextInt(), sc.nextDouble(), sc.next()));
					}
						break;
					case 3:
					{
						System.out.println("Enter student email to cancel admission:");
						System.out.println(dao.removeStudent(sc.next()));
					}
						break;
					case 4:
						exit = true;
						dao.cleanUp();
						System.out.println("Thanks for using our App!!!");
						break;
					default:
						System.out.println("Invalid Input!! Plz try again...");
						break;
					}
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
				}
				sc.nextLine();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
