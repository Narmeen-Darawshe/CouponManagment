package com.test;

import java.sql.Date;
import java.sql.SQLException;

import com.core.*;
import com.dao.CouponsDBDAO;
import com.facade.*;
import com.job.CouponExpirationDailyJob;
import com.login.*;
import com.util.ConnectionPool;

public class Test {

	public Test() {
		super();
		
	}

	public void testAll() throws SQLException{
		
		//start a connection 
		ConnectionPool poolObj = ConnectionPool.gesInstance();
		
		CouponsDBDAO couponRun=new CouponsDBDAO(poolObj);
		CouponExpirationDailyJob myRunnable= new CouponExpirationDailyJob(couponRun);
		 Thread ts = new Thread(myRunnable);
		ts.start();
		
		
		//get login manager instance
		LoginManager manager = LoginManager.getInstance();
		
		//log in as administrator
		ClientFacade admin = manager.login("admin@admin.com", "Admin", ClientType.Administrator,poolObj);
		
		
		AdminFacade Admin =(AdminFacade) admin;
		
		//add new company
		Company company=new Company("Lee","Lee@g","85sd64hg");
		Admin.addCompany(company);
		System.out.println("\n");
		//update company info
		Company company1=new Company(26,"tia","tia@gmail","2556fg");
		Admin.updateCompany(company1);
		
		System.out.println("\n");
		
		//delete Company
		Admin.deleteCompany(31);
		
		System.out.println("\n");
		
		//print all companies
		System.out.println(Admin.getAllCompanies());
		
		System.out.println("\n");
		
		//print one company
		System.out.println(Admin.getOneCompany(4));

		 Customer customer1 =new Customer( "nar", "jdr", "nar@g","1235");
		 
		 System.out.println("\n");
		 
		 //add customer
		 Admin.addCustomer(customer1);
		 
		 System.out.println("\n");
		 
		 //update customer
		 customer1 =new Customer( 1,"sam", "matti", "sam@gmail","1235");
		 Admin.updateCustomer(customer1);
		 
		 System.out.println("\n");
		 
		 //delete customer
		 Admin.deleteCustomer(6);
		 
		 System.out.println("\n");
		 
		 //print all customers
		// System.out.println(Admin.getAllCustomers());
		 
		 System.out.println("\n");
		 
		 //print one customer
		 System.out.println(Admin.getOneCustomer(4));
		 
		 
		 System.out.println("\n");
		 
		 //login as a company
		 ClientFacade Company = manager.login("sam@g", "555", ClientType.Company,poolObj); 
		 
		 CompanyFacade CompanyObj = (CompanyFacade)Company;
		 
		 Coupon coupon = new Coupon( 4, Category.Electricity,"electric" ,"aaa", Date.valueOf("2021-01-12") , Date.valueOf("2021-12-10"), 400, 88, "ff");  
		 
		 System.out.println("\n");
		 
		 //add coupon
		 CompanyObj.addCoupon(coupon);
		 
		 System.out.println("\n");
		 
		 //update coupon 
		 Coupon coupon1 = new Coupon(36, 21, Category.Electricity,"electric" ,"xxxxx", Date.valueOf("2021-01-12") , Date.valueOf("2021-12-10"), 400, 90, "ff"); 
		 CompanyObj.updateCoupon(coupon1);
		 
		 System.out.println("\n");
		 
		 //delete coupon
		 CompanyObj.deleteCoupon(35);
		 
		 System.out.println("\n");
		 
		 //print Company Coupons
		 System.out.println(CompanyObj.getCompanyCoupons());
		 
		 System.out.println("\n");
		 
		 //print coupons by Category
		 System.out.println(CompanyObj.getCompanyCoupons(Category.Restaurant));
		 
		 System.out.println("\n");
		 
		 //print coupons up to max price
		 System.out.println(CompanyObj.getCompanyCoupons(100));
		 
		 System.out.println("\n");
		 
		 //print company details
		 System.out.println(CompanyObj.getCompanyDetails());
		 
		 System.out.println("\n");
		 
		 //login as a customer
		 ClientFacade customer = manager.login("ney@g", "123", ClientType.Customer,poolObj);
		 
		 CustomerFacade customerObj = (CustomerFacade)customer;
		 
		 System.out.println("\n");
		 
		  //purchase coupon by customer
		  customerObj.purchaseCoupon(coupon1);
		 
		  System.out.println("\n");
		  
		  //get a list of customer coupons 
		  System.out.println(customerObj.getCustomerCoupons());
		 
		  System.out.println("\n");
		  
		  //print customer coupons by category
		  System.out.println(customerObj.getCustomerCoupons(Category.Restaurant));
		 
		  System.out.println("\n");
		  
		  //print customer coupons up to max price
		  System.out.println(customerObj.getCustomerCoupons(100));
		 
		 
		  System.out.println("\n");
		  
		  //print customer details
		  System.out.println(customerObj.getCustomerDetails());
	
		 
		myRunnable.stop();
		 poolObj.closeAllConnections();
		 
	}
	


	
}
