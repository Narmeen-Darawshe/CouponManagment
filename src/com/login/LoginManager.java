package com.login;

import java.sql.SQLException;

import com.dao.CompaniesDBDAO;
import com.dao.CouponsDBDAO;
import com.dao.CustomersDBDAO;
import com.facade.AdminFacade;
import com.facade.ClientFacade;
import com.facade.CompanyFacade;
import com.facade.CustomerFacade;
import com.util.ConnectionPool;

public class LoginManager {

	
	private static LoginManager instance ;

	private LoginManager() { }
	
	 public static LoginManager getInstance( ) {
		 if(instance==null)
			 instance=new LoginManager();
	      return instance;
	 }
	 
	 
	 public ClientFacade login(String email, String password, ClientType clientType, ConnectionPool poolObj) throws SQLException{
		 
		      CompaniesDBDAO companyDbObj=new CompaniesDBDAO(poolObj);
	  		  CouponsDBDAO couponeDbObj = new CouponsDBDAO(poolObj);
	  	      CustomersDBDAO customerDbObj =new CustomersDBDAO(poolObj);
	  	      
	  	      
		 switch (clientType)   {
		 
	      case Administrator:
	    	  
	    	 
	  		
	    	  AdminFacade admin=new AdminFacade(companyDbObj,couponeDbObj,customerDbObj);
	    	  
	            if(admin.login(email, password)) {
	            	return admin;
	            }
	            
	             break;
	             
	      case Company:
	    	  
	    	
	  		  
	    	  ClientFacade company =new CompanyFacade(companyDbObj, couponeDbObj);
	    	  
	    	  if(company.login(email, password)) {
	            	return company;
	            }
	             break;
	             
	             
	      case Customer:
	    	  
	    	  ClientFacade customer = new CustomerFacade(couponeDbObj, customerDbObj);
	    	  
	    	  if(customer.login(email, password)) {
	            	return customer;
	            }
	             break;
	      default:
	    	  return null;


	 }
		return null; 
	 }
	
}
