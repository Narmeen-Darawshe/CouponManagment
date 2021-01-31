package com.facade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.core.Company;
import com.core.Coupon;
import com.core.Customer;
import com.dao.*;

 
public class AdminFacade extends ClientFacade {
	
	private CompaniesDAO companiesDAO;
	private CouponsDAO couponsDAO;
	private CustomersDAO customersDAO;
	
	
	public AdminFacade() {
		super();
		
	}
	
	
	
	
	public AdminFacade(CompaniesDAO companiesDAO, CouponsDAO couponsDAO, CustomersDAO customersDAO) {
		super();
		this.companiesDAO = companiesDAO;
		this.couponsDAO = couponsDAO;
		this.customersDAO = customersDAO;
	}




	@Override
	public boolean login(String email, String password) {
		
		
		
		if (email.equals("admin@admin.com") & password.equals("Admin")) {
			
			return true;
			
		}else return false;
		
	}

	
//**************************************************************************add new company**********************************************************************	
 public void addCompany(Company company) throws SQLException {
	 
	  	 if (companiesDAO.isCompanyExisit(company.getEmail(), company.getPassword()) ==false & companiesDAO.isCompanyExisitByName(company.getName(), company.getPassword())== false) {
	  		 
		companiesDAO.addCompany(company); 
	 }else System.out.println("Company already exists");
	
 }

 
//**************************************************************************update company info**********************************************************************
 public void updateCompany(Company company) throws SQLException {
	 
	if ( companiesDAO.isCompanyExisitByName(company.getName(), company.getPassword())==true) {
	 companiesDAO.updateCompany(company);
	 
	}

 }
 
 
//**************************************************************************delete company**********************************************************************
 public void deleteCompany(int companyID) throws SQLException {
	 
	 int couponID;

     ArrayList<Coupon> array = couponsDAO.getCouponsByCompanyID(companyID);
     
     
     Iterator<Coupon> itr=array.iterator(); 
     while(itr.hasNext()){  
    	    Coupon coupon=(Coupon)itr.next();  
    	   couponID=coupon.getId(); 
    	    couponsDAO.deleteCouponPurchaseByID(couponID);
    	    couponsDAO.deleteCoupon(couponID);
    	    
    	  }  
     companiesDAO.deleteCompany(companyID);
     
 
 }
 
//**************************************************************************print all companies info**********************************************************************	 
 public ArrayList<Company> getAllCompanies() throws SQLException{


	 ArrayList<Company> array = companiesDAO.getAllCompanies();
	 return array;
 }
 
 
//**************************************************************************print one company info**********************************************************************	 
 public Company getOneCompany(int companyID) throws SQLException {
	 Company company=companiesDAO.getOneCompany(companyID);
	 return company;
 }
 
 
 
//**************************************************************************add a new customer**********************************************************************	 
 public void addCustomer(Customer customer) throws SQLException{
	 
	
	 if (customersDAO.isCustomerExisit(customer.getEmail(), customer.getPassword()) ==false) {
  		 
		 customersDAO.addCustomer(customer); 
		 }else System.out.println("Customer already exists");
		
	 }
	 
 
 
//**************************************************************************update customer**********************************************************************	 
 public void updateCustomer(Customer customer) throws SQLException{

	 

   if (customersDAO.isCustomerExisitByID(customer.getId())==true ) {
  		 
		 customersDAO.updateCustomer(customer);
		 
		 
		 }else System.out.println("Customer does not exists");
		
	 }
	 
 
//**************************************************************************delete customer**********************************************************************	 
 public void deleteCustomer(int customerID) throws SQLException{
	 

	 if (customersDAO.isCustomerExisitByID(customerID)==true ) {
  		 
		
		  couponsDAO.deleteCouponPurchaseByCustomerID(customerID);
		  customersDAO.deleteCustomer(customerID);
		 
		 }else System.out.println("Customer does not exists");
		
	 }
	
//**************************************************************************print all customers**********************************************************************	 
 public ArrayList<Customer> getAllCustomers() throws SQLException, InterruptedException {
	 
	 customersDAO=new CustomersDBDAO();
	 ArrayList<Customer> array = customersDAO.getAllCustomers();
	 return array;
	 
	 
	 
 }
//**************************************************************************print one customer*******************************************************	 
 public Customer getOneCustomer(int customerID) throws SQLException{
	
	 
	 Customer customer=new Customer();
	 if (customersDAO.isCustomerExisitByID(customerID)==true ) {
  		 
			
		  
		 customer= customersDAO.getOneCustomer(customerID);
		 return customer;
		 }else System.out.println("Customer does not exists");
	 
	return null;
	 
	 
		
	 }
 
 
}
