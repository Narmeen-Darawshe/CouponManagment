package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



import com.core.Company;
import com.util.ConnectionPool;

public class CompaniesDBDAO implements CompaniesDAO{
	
	 private ConnectionPool connection;
	 private Connection con;
	 
	 
	public CompaniesDBDAO() {
		
	}


	public CompaniesDBDAO(ConnectionPool connection) {
		super();
		this.connection =  connection;
	}


	public boolean isCompanyExisit( String email,String password) throws SQLException{
		
		boolean flag = false;
		
		
		try {
			 con  =  connection.getConnection(); 
		
		String query  = "select * from devtech.companies where EMAIL= ? AND PASSWORD =?"; 
        PreparedStatement statement = con.prepareStatement(query); 
  
  
        statement.setString(1, email);
        statement.setString(2, password); 
       
        ResultSet rs = statement.executeQuery(); 
         
  
        while (rs.next()) { 
            flag = true; 
          rs.getString("email");
         
        } 
  
    
        
		}catch (Exception e) {
			
			System.out.println("Company dose not Exisit");
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
	
	
	public boolean isCompanyExisitByName(String name, String password) throws SQLException{
		
		boolean flag = false;
		try {
			
			con  =  connection.getConnection(); 

		String query  = "select * from devtech.companies where NAME= ? OR PASSWORD =?"; 
        PreparedStatement statement = con.prepareStatement(query); 
  
        statement.setString(1, name); 
        statement.setString(2, password); 
       
        ResultSet rs = statement.executeQuery(); 
         
  
        while (rs.next()) { 
            flag = true; 
        
         
        } 
            }catch (Exception e) {
			
			System.out.println("Company dose not Exisit");
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
	
	
	
	
	public int getCompanyID(String email, String password) throws SQLException{
		
		int id = 0;
		try {
		con  =connection.getConnection(); 

		String query  = "select * from devtech.companies where EMAIL= ? AND PASSWORD =?"; 
        PreparedStatement statement = con.prepareStatement(query); 
  
        statement.setString(1, email); 
        statement.setString(2, password); 
       
        ResultSet rs = statement.executeQuery(); 
        
        while (rs.next()) { 
         id =rs.getInt("id");
       
        }
		 }catch (Exception e) {
				
				System.out.println("Company dose not Exisit");
				e.printStackTrace();
			}finally {
				
				connection.releaseConnection(con);
				con=null;
			}
		 return id;
	}
	
	
	public void addCompany(Company company) throws SQLException{
		
		try {
		con  = connection.getConnection(); 

		String query  = "INSERT INTO `devtech`.`companies` ( `NAME`, `EMAIL`, `PASSWORD`) VALUES ( ? , ? , ? ); \r\n"; 
		
        PreparedStatement statement = con.prepareStatement(query); 
  
        statement.setString(1, company.getName()); 
        statement.setString(2, company.getEmail()); 
        statement.setString(3, company.getPassword());
        
        statement.executeUpdate();

        
        System.out.println("new company has been added! ");
		}catch (Exception e) {
			
			System.out.println("Can not add Company!");
			e.printStackTrace();
		}finally {
			
			connection.releaseConnection(con);
			con=null;
		}
       
		
	}


	public void updateCompany(Company company) throws SQLException{
		
		try {
		con  = connection.getConnection(); 

		String query  = "UPDATE `devtech`.`companies` SET `EMAIL` = ?, `PASSWORD` = ? WHERE (`ID` = ?); \r\n";
		
		PreparedStatement statement = con.prepareStatement(query); 
		
		   
	        statement.setString(1, company.getEmail()); 
	        statement.setString(2, company.getPassword());
	        statement.setInt(3, company.getId());
		
		   statement.executeUpdate();
		   
		   System.out.println(" company has been updated! ");
         }catch (Exception e) {
			
			System.out.println("Can not update Company!");
			e.printStackTrace();
		}finally {
			
			connection.releaseConnection(con);
			con=null;
		}
       
	}
	
	
	public void deleteCompany(int companyID) throws SQLException{
		
		try {
		con  = connection.getConnection(); 

		String query  = "DELETE FROM `devtech`.`companies` WHERE (`ID` = ?); \r\n";
		PreparedStatement statement = con.prepareStatement(query); 
		
		statement.setInt(1, companyID);
		statement.executeUpdate();
		
		 System.out.println(" company has been deleted! ");
        }catch (Exception e) {
			
			System.out.println("Can not delete Company!");
			e.printStackTrace();
		}finally {
			
			connection.releaseConnection(con);
			con=null;
		}
		
	}
	
	public ArrayList<Company> getAllCompanies() throws SQLException{
		
		ArrayList<Company> array = new ArrayList<>();
		
		try {
		con  = connection.getConnection(); 
		
		String query  = "select * from devtech.companies"; 
        PreparedStatement statement  = con.prepareStatement(query); 
  
        ResultSet rs = statement.executeQuery(); 
       
        while (rs.next()) { 
            
        	int id =rs.getInt("id");
        	String  name = rs.getString("name");
        	String  email = rs.getString("email");
        	
        	Company company=new Company(id, name, email);
        	array.add(company);
        	
        }
        
    }catch (Exception e) {
		
		System.out.println("Can not reach Company!");
		e.printStackTrace();
	}finally {
		
		connection.releaseConnection(con);
		con=null;
	}
		return array; 
		
	}
	
	
	
	public Company getOneCompany(int companyID) throws SQLException {
	
	Company company=new Company();
		try {
	con  = connection.getConnection(); 

		
	String query  = "select * from devtech.companies where ID = ?"; 
    PreparedStatement statement = con.prepareStatement(query); 

	statement.setInt(1, companyID); 
	
    ResultSet rs = statement.executeQuery(); 
    while (rs.next()) { 
    	
    company.setId(rs.getInt("id"));	
    company.setName(rs.getString("name")); 
	company.setEmail(rs.getString("email"));
	
	 
    }
        }catch (Exception e) {
			
			System.out.println("Can not reach Company!");
			e.printStackTrace();
		}finally {
			
			connection.releaseConnection(con);
			con=null;
		}
	return company;
	}

}
