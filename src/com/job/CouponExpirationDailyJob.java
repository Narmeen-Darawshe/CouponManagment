package com.job;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;


import com.core.Coupon;
import com.dao.CouponsDAO;



public class CouponExpirationDailyJob implements Runnable{

	
	private CouponsDAO couponDAO;
	private boolean quit;
	
	
	
	public CouponExpirationDailyJob(CouponsDAO couponDAO) {
		super();
		this.couponDAO = couponDAO;
	}



	public  void run() 
    {
		
		this.quit=false;
		
			
	
			while(quit==false) {
				
				try {	
					
					delete();
					
					
				} catch (  SQLException e) {
					
					e.printStackTrace();
				}
			}
			
		
		
		
    }
	
	public synchronized void delete() throws SQLException {
		
		ArrayList<Coupon> array = couponDAO.getAllCoupons();
	     
	     
	     Iterator<Coupon> itr=array.iterator(); 
	     while(itr.hasNext()){  
	    	    Coupon coupon=(Coupon)itr.next();  
	    	    
             Date date=Date.valueOf(java.time.LocalDate.now());
				
				//check if Coupon date has expired! 
				if(date.after(coupon.getEndDate())== true) { 
					
					//check if coupon exists in purchase table and delete it
					if(couponDAO.getCouponPurchaseByCouponID(coupon.getId())) {
						
	    		          couponDAO.deleteCouponPurchaseByID(coupon.getId());
	    		  
					}
	    		  couponDAO.deleteCoupon(coupon.getId());
	    		  System.out.println("Coupon has been deleted");
	    		  
	    		  
	    	   }   
	     }
		stop();
	}
	
	
	
	
	public void stop() {
		
		this.quit=true;
	}
	
}
