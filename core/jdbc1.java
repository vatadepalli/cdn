// *************** POJOS *********************

package pojos;

import java.sql.*;

public class Stock {

    private int id;
    private String name, company;
    private double price;
    private int quantity;
    private Date closeDate;

    public Stock() {
        System.out.println("in Stock ctor...");
    }

    public Stock(String name, String company, double price, int quantity, Date closeDate) {
        super();
        this.name = name;
        this.company = company;
        this.price = price;
        this.quantity = quantity;
        this.closeDate = closeDate;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    @Override
    public String toString() {
        return "Stock [id=" + id + ", name=" + name + ", company=" + company + ", price=" + price + ", quantity="
                + quantity + ", closeDate=" + closeDate + "]";
    }

}



















// ******************* DAO INTERFACE ***********************

package dao;

import java.sql.SQLException;

import pojos.Stock;

public interface IStockDao {

	public void cleanUp() throws SQLException;
	
	String deleteStock(String compName) throws Exception;
	
	String addNewStock(Stock s) throws Exception;
	
	String updateStock(int id, double newPrice) throws Exception;
}



















// ******************* DAO IMPLEMENTATION ***********************

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import exception.StockNotFoundException;

import static utils.DBUtils.*;
import pojos.Stock;

public class StockDaoImpl implements IStockDao {

	private Connection cn;
	private PreparedStatement pst1,pst2,pst3;
	
	public StockDaoImpl() throws SQLException, ClassNotFoundException {
		cn = connectDB();
		pst1 = cn.prepareStatement("delete from stocks where company=?");
		pst2 = cn.prepareStatement("insert into stocks values(default,?,?,?,?,?)");
		pst3 = cn.prepareStatement("update stocks set price=? where id=?");
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
	public String deleteStock(String compName) throws Exception {
		pst1.setString(1, compName);
		int rst = pst1.executeUpdate();
		if(rst == 0)
			throw new StockNotFoundException();
		return "Stock deleted successfully!";
	}

	@Override
	public String addNewStock(Stock s) throws Exception {
		pst2.setString(1, s.getName());
		pst2.setString(2, s.getCompany());
		pst2.setDouble(3, s.getPrice());
		pst2.setInt(4, s.getQuantity());
		pst2.setDate(5, s.getCloseDate());
		int rst = pst2.executeUpdate();
		if(rst == 0)
			throw new StockNotFoundException("Stock NOT inserted!!");
		return "Stock inserted Successfully!!";
	}

	@Override
	public String updateStock(int id, double newPrice) throws Exception {
		pst3.setDouble(1, newPrice);
		pst3.setInt(2, id);
		int rst = pst3.executeUpdate();
		if(rst == 0)
			throw new StockNotFoundException();
		return "Stock Updated Successfully!!";
	}

}



















// ************* EXCEPTION *********************

package exception;

public class StockNotFoundException extends Exception{

	public StockNotFoundException() {
		super("Stock Not Found!!");
	}
	
	public StockNotFoundException(String errMsg)
	{
		super(errMsg);
	}
}



















// ******************* DB UTILS *******************

package utils;

import java.sql.*;

public class DBUtils {

	private static Connection cn;
	
	public static Connection connectDB() throws ClassNotFoundException, SQLException
	{
		if(cn == null)
		{
			String url = "jdbc:mysql://localhost:3306/hib_java?autoReconnect=true&useSSL=false";
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn = DriverManager.getConnection(url,"root","root1234");
		}
		return cn;
	}
	
}




















// *************************** TESTER ****************************

package tester;

import java.util.Scanner;
import dao.IStockDao;
import dao.StockDaoImpl;
import pojos.Stock;
import static java.sql.Date.*;

public class TestStock {

	public static void main(String[] args) {
		
		boolean exit = false;
		try(Scanner sc = new Scanner(System.in))
		{
			IStockDao dao = new StockDaoImpl();
			while(!exit)
			{
				try {
					System.out.println("****MENU*****");
					System.out.println("1.Delete stock\n2.Add new stock\n3.Update stock price\n4.Exit");
					System.out.println("Enter ur choice:");
					switch (sc.nextInt()) {
					case 1:
					{
						System.out.println("Enter comp name:");
						System.out.println(dao.deleteStock(sc.next()));
					}
						break;
					case 2:
					{
						System.out.println("Enter Stock Dtls(Name,CompanyName,price,quantity,closeDate)");
						System.out.println(dao.addNewStock(new Stock(sc.next(), sc.next(), sc.nextDouble(), sc.nextInt(), valueOf(sc.next()))));
					}
						break;
					case 3:
					{
						System.out.println("Enter ID and stock price:");
						System.out.println(dao.updateStock(sc.nextInt(), sc.nextDouble()));
					}
						break;
					case 4:
						exit = true;
						dao.cleanUp();
						System.out.println("Thanks...");
						break;
					default:
						System.out.println("Invalid Input!! plz try again");
						break;
					}
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				sc.nextLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
