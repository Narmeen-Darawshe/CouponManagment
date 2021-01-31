package com.facade;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.core.Category;
import com.core.Coupon;
import com.core.Customer;
import com.dao.CouponsDAO;

import com.dao.CustomersDAO;


public class CustomerFacade extends ClientFacade{

	
	
	private CouponsDAO couponsDAO;
	private CustomersDAO customersDAO;
	
	private int customerID;
	
	
	
	
	public CustomerFacade() {
		super();
		
	}
	
	 public CustomerFacade(CouponsDAO couponsDAO, CustomersDAO customersDAO) {
		super();
		this.couponsDAO = couponsDAO;
		this.customersDAO = customersDAO;
	}






	@Override
	public boolean login(String email, String password) throws SQLException {

		
		if (customersDAO.isCustomerExisit(email, password) ==true) {
			
			this.customerID=customersDAO.getCustomerID(email, password);
			
			return true;
			
		}else
		return false;
	}

	
//**************************************************************************purchase a Coupon**********************************************************************		
	public void purchaseCoupon(Coupon coupon) throws SQLException{
		
	
		boolean flag =false;
		
		
		ArrayList<Coupon> array  = couponsDAO.getCouponPurchase(customerID);
	    
	     
	     Iterator<Coupon> itr=array.iterator(); 
	     while(itr.hasNext()){  
	    	    Coupon coupon1=(Coupon)itr.next();  
	    	    
	    	   if(coupon.getId()==coupon1.getId()) {
	    		  
	    		   flag=true;
	    		
	    	   }   
	     }
	     
		if (flag ==false) {
		 coupon=couponsDAO.getOneCoupon(coupon.getId());
			
			
			
			if(coupon.getAmount()>0) {
				
				Date date=Date.valueOf(java.time.LocalDate.now());
				
				
				if(date.after(coupon.getEndDate())== false) {
					
					
					couponsDAO.addCouponPurchase(customerID, coupon.getId());
					int amount =coupon.getAmount()-1;
					coupon.setAmount(amount);
					couponsDAO.updateCoupon(coupon);
					
					
				}else System.out.println("Coupon has expired!");
				
			}else  System.out.println("Coupon does not exists.");
			
		} else System.out.println("Coupon has already been purchased");
		
	}


	
//**************************************************************************get customer coupons**********************************************************************
	public ArrayList<Coupon> getCustomerCoupons() throws SQLException{
		
		
		
		ArrayList<Coupon> array  = couponsDAO.getCouponPurchase(customerID);
		ArrayList<Coupon> couponBycustomerID = new ArrayList<>();
	     
	     Iterator<Coupon> itr=array.iterator(); 
	     while(itr.hasNext()){  
	    	    Coupon coupon=(Coupon)itr.next();  
	    	    
	    	    couponBycustomerID.add(couponsDAO.getOneCoupon(coupon.getId())) ;
	     }
			return couponBycustomerID;
	}
	
	
	
//**************************************************************************get customer coupons by category**********************************************************************	
	public ArrayList<Coupon> getCustomerCoupons(Category category) throws SQLException{
		

		
		ArrayList<Coupon> array  = couponsDAO.getCouponPurchase(customerID);
		ArrayList<Coupon> couponByCategory = new ArrayList<>();
	     
	     Iterator<Coupon> itr=array.iterator(); 
	     while(itr.hasNext()){  
	    	    Coupon coupon=(Coupon)itr.next();  
	    	    coupon=couponsDAO.getOneCoupon(coupon.getId());
	    	    if(coupon.getCategory()==category) {
	    	    couponByCategory.add(coupon) ;
	    	    
	    	    }
	     }
	     
	     
			return couponByCategory;
	}

	
//**************************************************************************get customer coupons up to max price**********************************************************************
	public ArrayList<Coupon> getCustomerCoupons(double maxPrice) throws SQLException{
		

			
			ArrayList<Coupon> array  = couponsDAO.getCouponPurchase(customerID);
			ArrayList<Coupon> couponByPrice = new ArrayList<>();
		     
		     Iterator<Coupon> itr=array.iterator(); 
		     while(itr.hasNext()){  
		    	    Coupon coupon=(Coupon)itr.next();  
		    	    coupon=couponsDAO.getOneCoupon(coupon.getId());
		    	   
		    	    if(coupon.getPrice() <= maxPrice) {
		    	    couponByPrice.add(coupon) ;
		    	    
		    	    }
		     }
		     
		     
				return couponByPrice;
		}
	
	

//**************************************************************************get customer details**********************************************************************
	public Customer getCustomerDetails() throws SQLException {
		Customer customer=customersDAO.getOneCustomer(customerID);
		 return customer;
	 }
}
