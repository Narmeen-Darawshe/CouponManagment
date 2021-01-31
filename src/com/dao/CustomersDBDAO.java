package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.core.Customer;
import com.util.ConnectionPool;

public class CustomersDBDAO implements CustomersDAO{

	private ConnectionPool connection;
	private Connection con;
	
	 
	 
	public CustomersDBDAO() {
		super();
		
	}

	public CustomersDBDAO(ConnectionPool connection) {
		super();
		this.connection = connection;
	}

	public boolean isCustomerExisit(String email,String password) throws SQLException{
		
		
		boolean flag = false; 
		try {
			con  = connection.getConnection();
			
		String query  = "select * from devtech.customers where EMAIL= ? AND PASSWORD =?"; 
        PreparedStatement statement = con.prepareStatement(query); 
  
        statement.setString(1, email); 
        statement.setString(2, password); 
       
        ResultSet rs = statement.executeQuery(); 
  
        while (rs.next()) { 
            flag = true; 
          rs.getString("email");
         
        }
		}catch (Exception e) {
			
			System.out.println("Customer dose not Exisit");
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
	
	public boolean isCustomerExisitByID(int customerID) throws SQLException{
		
		boolean flag = false; 
		 try {
				con  = connection.getConnection();
		String query  = "select * from devtech.customers WHERE `ID` = "+customerID+"; \r\n"; 
        PreparedStatement statement = con.prepareStatement(query); 
  
        ResultSet rs = statement.executeQuery(); 
        
  
        while (rs.next()) { 
            flag = true; 
          rs.getString("email");
         
        } 
        
		 }catch (Exception e) {
				
				System.out.println("Customer dose not Exisit");
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
	
	
	public void addCustomer(Customer customer) throws SQLException{
		
		 try {
				con  = connection.getConnection();
				
	String query  = "INSERT INTO `devtech`.`customers` (`FIRST_NAME`, `LAST_NAME`, `EMAIL`, `PASSWORD`) VALUES ( ? , ? , ? , ? ); \r\n"; 
		
        PreparedStatement statement = con.prepareStatement(query); 
  
        statement.setString(1, customer.getFirstName()); 
        statement.setString(2, customer.getLastName()); 
        statement.setString(3, customer.getEmail());
        statement.setString(4, customer.getPassword());
        
        statement.executeUpdate();
		
        System.out.println("new customer has been added!");
		 }catch (Exception e) {
				
				System.out.println("can not add a new Customer");
				e.printStackTrace();
			}finally {
				
				connection.releaseConnection(con);
				con=null;
			}
		
	}
	
	
	
	public void updateCustomer(Customer customer) throws SQLException{
		
		 try {
				con  = connection.getConnection();
				
		 String query  = "UPDATE `devtech`.`customers` SET `FIRST_NAME` = ?, `LAST_NAME` = ? , `EMAIL` = ? , `PASSWORD` = ? WHERE `ID` = "+customer.getId()+"; \r\n";
		
		PreparedStatement statement = con.prepareStatement(query); 
		
        statement.setString(1, customer.getFirstName()); 
        statement.setString(2, customer.getLastName()); 
        statement.setString(3, customer.getEmail());
        statement.setString(4, customer.getPassword());
	   
		
		   statement.executeUpdate();
		   
		   System.out.println(" Customer has been updated! ");
		 }catch (Exception e) {
				
				System.out.println("Can not update customer!");
				e.printStackTrace();
			}finally {
				
				connection.releaseConnection(con);
				con=null;
			}
		
	}
	
	
	public void deleteCustomer(int customerID) throws SQLException{
		
		 try {
				con  = connection.getConnection();
				
		String query  = "DELETE FROM `devtech`.`customers` WHERE (`ID` = ?); \r\n";
		PreparedStatement statement = con.prepareStatement(query); 
		
		statement.setInt(1, customerID);
		statement.executeUpdate();
		
		System.out.println(" Customer has been deleted! ");
		
	}catch (Exception e) {
		
		System.out.println("Customer dose not Exisit");
		e.printStackTrace();
	}finally {
		
		connection.releaseConnection(con);
		con=null;
	}
		
	}
	
	
	public ArrayList<Customer> getAllCustomers() throws SQLException{
               
		ArrayList<Customer> array = new ArrayList<>();
		
		try {
		 
		con  = connection.getConnection();
		String query  = "SELECT * FROM devtech.customers"; 
		
		
        PreparedStatement statement  = con.prepareStatement(query); 
  
        ResultSet rs = statement.executeQuery(); 
       
        while (rs.next()) { 
            
        	int id =rs.getInt("id");
        	String  firstName = rs.getString("first_name");
        	String  lastName = rs.getString("last_name");
        	String  email = rs.getString("email");
        	
        	Customer customer=new Customer(id, firstName, lastName, email);
        	array.add(customer);
        	
        }
        
    }catch (Exception e) {
		
		System.out.println("Can not reach customers!");
		e.printStackTrace();
	}finally {
		
		connection.releaseConnection(con);
		con=null;
	}
		return array; 
		
	}
	
	
	
	public Customer getOneCustomer(int customerID) throws SQLException{
		
		Customer customer=new Customer();
		try {
			con  = connection.getConnection(); 

		String query  = "select * from devtech.customers where ID = ?"; 
		
	    PreparedStatement statement = con.prepareStatement(query); 

		statement.setInt(1, customerID); 
	  
	    ResultSet rs = statement.executeQuery(); 
	    
	    
	    while (rs.next()) { 
	    customer.setId(rs.getInt("id"));
	    customer.setFirstName(rs.getString("first_name"));
    	customer.setLastName(rs.getString("last_name"));
    	customer.setEmail(rs.getString("email")); 
		

	    }
		}catch (Exception e) {
			
			System.out.println("Customer dose not Exisit");
			e.printStackTrace();
		}finally {
			
			connection.releaseConnection(con);
			con=null;
		}
		return customer;
		
		
		
	}

	@Override
	public int getCustomerID(String email, String password) throws SQLException {
		
		int id = 0;
		try {
		con  = connection.getConnection(); 

		String query  = "select * from devtech.customers where EMAIL= ? AND PASSWORD =?"; 
        PreparedStatement statement = con.prepareStatement(query); 
  
        statement.setString(1, email); 
        statement.setString(2, password); 
       
        ResultSet rs = statement.executeQuery(); 
        
        while (rs.next()) { 
         id =rs.getInt("id");
       
        }
		 }catch (Exception e) {
				
				System.out.println("Customer dose not Exisit");
				e.printStackTrace();
			}finally {
				
				connection.releaseConnection(con);
				con=null;
			}
		 return id;
	}
	
	}
	
	

