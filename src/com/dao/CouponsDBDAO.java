package com.dao;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.util.ConnectionPool;
import com.core.*;
public class CouponsDBDAO implements CouponsDAO {
	
	private ConnectionPool connection;
	private Connection con;
	
	


	public CouponsDBDAO(ConnectionPool connection) {
		super();
		this.connection = connection;
	}
	

	public CouponsDBDAO() {
		super();
		// TODO Auto-generated constructor stub
	}





	public void addCoupon(Coupon coupon) throws SQLException {
		
		
		try {
			 con  = connection.getConnection(); 
			 
    String query  = "INSERT INTO `devtech`.`coupons` ( `COMPANY_ID`, `CATEGORY_ID`, `TITLE`, `DESCRIPTION`, `START_DATE`, `END_DATE`, `AMOUNT`, `PRICE`, `IMAGE`) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?); \r\n"; 
		
        PreparedStatement statement = con.prepareStatement(query); 
  
        statement.setInt(1, coupon.getCompanyID()); 
        statement.setInt(2,((coupon.getCategory()).ordinal()) ); 
        statement.setString(3, coupon.getTitle());
        statement.setString(4, coupon.getDescription());
        statement.setDate(5, (Date) coupon.getStartDate());
        statement.setDate(6, (Date) coupon.getEndDate());
        statement.setInt(7, coupon.getAmount());
        statement.setDouble(8, coupon.getPrice());
        statement.setString(9, coupon.getImage());
        
        
        statement.executeUpdate();
        System.out.println("new coupon has been added! ");
		}catch (Exception e) {
			
			System.out.println("Can not add coupon!");
			e.printStackTrace();
		}finally {
			
			connection.releaseConnection(con);
			con=null;
		}
		
		
	}
	
	

    public void updateCoupon(Coupon coupon) throws SQLException{
		
    	
    	try {
   		 con  = connection.getConnection(); 
   		 
		   String query  = "UPDATE `devtech`.`coupons` SET `CATEGORY_ID` = ?, `TITLE` = ?, `DESCRIPTION` = ?, `START_DATE` = ?, `END_DATE` = ?, `AMOUNT` = ?, `PRICE` = ?, `IMAGE` = ? WHERE (`ID` = ?); \r\n";
			
			PreparedStatement statement = con.prepareStatement(query); 
			
		       
		        statement.setInt(1, coupon.getCategory().ordinal()); 
		        statement.setString(2, coupon.getTitle());
		        statement.setString(3, coupon.getDescription());
		        statement.setDate(4, (Date) coupon.getStartDate());
		        statement.setDate(5, (Date) coupon.getEndDate());
		        statement.setInt(6, coupon.getAmount());
		        statement.setDouble(7, coupon.getPrice());
		        statement.setString(8, coupon.getImage());
		        statement.setInt(9, coupon.getId());
		        
		        
		        
			   statement.executeUpdate();
			
			   System.out.println(" coupon has been updated! ");
		}catch (Exception e) {
			
			System.out.println("Can not update coupon!");
			e.printStackTrace();
		}finally {
			
			connection.releaseConnection(con);
			con=null;
		}
		
	}
	

    
	public void deleteCoupon(int couponID) throws SQLException{
		
		try {
			 con  = connection.getConnection(); 
			 
		String query  = "DELETE FROM `devtech`.`coupons` WHERE (`ID` = ?); \r\n";
		PreparedStatement statement = con.prepareStatement(query); 
		
		statement.setInt(1, couponID);
		statement.executeUpdate();
		
		System.out.println(" coupon has been deleted! ");
		}catch (Exception e) {
			
			System.out.println("Can not delete Company!");
			e.printStackTrace();
		}finally {
			
			connection.releaseConnection(con);
			con=null;
		}
		
	}
	
	
	public ArrayList<Coupon> getAllCoupons()throws SQLException {
		
		ArrayList<Coupon> array = new ArrayList<>();
			
		try {
			 con  = connection.getConnection(); 
		
		 
		  	String query  = "SELECT * FROM devtech.coupons;";  	
	        PreparedStatement statement  = con.prepareStatement(query); 
	  
	        ResultSet rs = statement.executeQuery(); 
	       
	        while (rs.next()) { 
	        	
	        	int id =rs.getInt("id");
	        	int  company_id = rs.getInt("company_id");
	        	int  gategory_id = rs.getInt("category_id");
	        	String  title = rs.getString("title");
	        	String  description = rs.getString("description");
	        	Date startDate = rs.getDate("start_date");
	        	Date endDate = rs.getDate("end_date");
	        	int amount = rs.getInt("amount");
	        	double price = rs.getDouble("price");
	        	String image = rs.getString("image");
	        	
	        	
	        	Coupon coupon=new Coupon(id, company_id,  Category.values()[gategory_id],title, description, startDate, endDate, amount, price, image);	
	        	array.add(coupon);
	        	
	        }
	        
		}catch (Exception e) {
			
			System.out.println("Can not get coupons!");
			e.printStackTrace();
		}finally {
			
			connection.releaseConnection(con);
			con=null;
		}
			
	        
			return array; 
		
	}
	
	public Coupon getOneCoupon(int couponID)throws SQLException{
		
		Coupon coupon = null;
		try {
			 con  = connection.getConnection(); 
			 
       String query  = "select * from devtech.coupons where ID = ?"; 
		
	    PreparedStatement statement = con.prepareStatement(query); 

		statement.setInt(1, couponID); 
	
	    ResultSet rs = statement.executeQuery(); 
	
	  	
	    while (rs.next()) { 
	    	
        	int id =rs.getInt("id");
        	int  company_id = rs.getInt("company_id");
        	int  gategory_id = rs.getInt("category_id");
        	String  title = rs.getString("title");
        	String  description = rs.getString("description");
        	Date startDate = rs.getDate("start_date");
        	Date endDate = rs.getDate("end_date");
        	int amount = rs.getInt("amount");
        	double price = rs.getDouble("price");
        	String image = rs.getString("image");
        	
        	
        	 coupon=new Coupon( id, company_id,Category.values()[gategory_id] ,title, description, startDate, endDate, amount, price, image);	
        	
        	
        }
		}catch (Exception e) {
			
			System.out.println("Can not get coupon!");
			e.printStackTrace();
		}finally {
			
			connection.releaseConnection(con);
			con=null;
		}
	return coupon;
		
		
	}
	
	public ArrayList<Coupon> getCouponsByCompanyID(int companyID)throws SQLException {
		
		
		ArrayList<Coupon> array = new ArrayList<>();
		
		try {
			 con  = connection.getConnection(); 
			 
		
		
		String query  = "select * from devtech.coupons where COMPANY_ID = ? "; 
	       
			
		    PreparedStatement statement = con.prepareStatement(query); 

			statement.setInt(1, companyID); 
		  
		    ResultSet rs = statement.executeQuery(); 
		    
		    
		    
          while (rs.next()) { 
	        	
	        	int id =rs.getInt("id");
	        	int  company_id = rs.getInt("company_id");
	        	int  gategory_id = rs.getInt("category_id");
	        	String  title = rs.getString("title");
	        	String  description = rs.getString("description");
	        	Date startDate = rs.getDate("start_date");
	        	Date endDate = rs.getDate("end_date");
	        	int amount = rs.getInt("amount");
	        	double price = rs.getDouble("price");
	        	String image = rs.getString("image");
	        	
	        	
	        	Coupon coupon=new Coupon( id, company_id,Category.values()[gategory_id] ,title, description, startDate, endDate, amount, price, image);	
	        	array.add(coupon);
	        	
	        }
	}catch (Exception e) {
			
			System.out.println("Can not get coupons!");
			e.printStackTrace();
		}finally {
			connection.releaseConnection(con);
			con=null;
		}
          
		return array;
			
	       
			
		}
	
	
	
	
	
	
	public void addCouponPurchase(int customerID, int couponID) throws SQLException{
		
		try {
			 con  = connection.getConnection(); 
			 
		 String query  = "INSERT INTO `devtech`.`customers_vs_coupons` (`CUSTOMER_ID`, `COUPON_ID`) VALUES (?, ?);"; 
		   
		  PreparedStatement statement = con.prepareStatement(query); 

			statement.setInt(1, customerID); 
			statement.setInt(2, couponID);
		  
			statement.executeUpdate();
		 
			System.out.println("coupon has been purchased ");
			
      	}catch (Exception e) {
			
			System.out.println("Coupon has not been purchased!");
			e.printStackTrace();
		}finally {
			
			connection.releaseConnection(con);;
			con=null;
		}
		
		
	}
	
	
	public ArrayList<Coupon> getCouponPurchase(int customerID) throws SQLException{
		
		ArrayList<Coupon> array = new ArrayList<>();
		
		try {
			 con  = connection.getConnection(); 
			 
		
		
		String query  = "SELECT * FROM devtech.customers_vs_coupons WHERE CUSTOMER_ID = ?; \r\n";
		
		 PreparedStatement statement = con.prepareStatement(query); 

			statement.setInt(1, customerID); 
			
			ResultSet rs = statement.executeQuery(); 
		    
		    
			 
	          while (rs.next()) {
	        	  
	        	   Coupon coupon =new Coupon();
	        	    coupon.setId(rs.getInt("coupon_id"));
	        	  array.add(coupon);
	          }
	          
				
      	}catch (Exception e) {
			
			System.out.println("Can not get purchase!");
			e.printStackTrace();
		}finally {
			
			connection.releaseConnection(con);
			con=null;
		}
		
			return array;
			
		
		
	}
	
	
public boolean getCouponPurchaseByCouponID(int couponID) throws SQLException{
		
		boolean flag=false;
		
		try {
			 con  = connection.getConnection(); 
			 
		
		
		String query  = "SELECT * FROM devtech.customers_vs_coupons WHERE COUPON_ID = ?; \r\n";
		
		 PreparedStatement statement = con.prepareStatement(query); 

			statement.setInt(1, couponID); 
			
			ResultSet rs = statement.executeQuery(); 
		    
		    
			 
	          while (rs.next()) {
	        	  
	        	  flag=true;
	        	   
	        	  
	          }
	          
				
      	}catch (Exception e) {
			
			e.printStackTrace();
		}finally {
			
			connection.releaseConnection(con);
			con=null;
		}
		  if (flag == true) { 
	            return true; 
	        } 
	        else
	            return false; 
			
			
		} 
	



	public void deleteCouponPurchase(int customerID, int couponID) throws SQLException {
		
	
		try {
			 con  = connection.getConnection(); 
			 
		String query  = "DELETE FROM `devtech`.`customers_vs_coupons` WHERE (`CUSTOMER_ID` = ?) and (`COUPON_ID` = ?); \r\n";
		PreparedStatement statement = con.prepareStatement(query); 
		
		statement.setInt(1, customerID);
		statement.setInt(2, couponID);
		statement.executeUpdate();
		
		
		
      	}catch (Exception e) {
			
			System.out.println("can not delete coupon purchase!");
			e.printStackTrace();
		}finally {
			
			connection.releaseConnection(con);
			con=null;
		}
		
	}
	
public void deleteCouponPurchaseByID( int couponID) throws SQLException {
		
	try {
		 con  = connection.getConnection(); 
		 
		String query  = "DELETE FROM `devtech`.`customers_vs_coupons` WHERE  `COUPON_ID` = ?; \r\n";
		
		PreparedStatement statement = con.prepareStatement(query); 
	
		statement.setInt(1, couponID);
		statement.executeUpdate();
		
		
		
  	}catch (Exception e) {
		
		System.out.println("can not delete coupon purchase!");
		e.printStackTrace();
	}finally {
		
		connection.releaseConnection(con);
		con=null;
	}
	
	}

public void deleteCouponPurchaseByCustomerID( int customerID)throws SQLException{
	
	try {
		 con  = connection.getConnection(); 
		 
	String query  = "DELETE FROM `devtech`.`customers_vs_coupons` WHERE  `CUSTOMER_ID` = ?; \r\n";
	
	PreparedStatement statement = con.prepareStatement(query); 

	statement.setInt(1, customerID);
	statement.executeUpdate();
	
}catch (Exception e) {
	
	System.out.println("can not delete coupon purchase!");
	e.printStackTrace();
}finally {
	
	connection.releaseConnection(con);
	con=null;
}
}
}
