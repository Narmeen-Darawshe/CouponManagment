package com.facade;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.core.Category;
import com.core.Company;
import com.core.Coupon;
import com.dao.CompaniesDAO;

import com.dao.CouponsDAO;



public class CompanyFacade extends ClientFacade{

	private CompaniesDAO companiesDAO;
	private CouponsDAO couponsDAO;
	
	
	private int companyID;

	public CompanyFacade() {
		super();
		
	}



	public CompanyFacade(CompaniesDAO companiesDAO, CouponsDAO couponsDAO) {
		super();
		this.companiesDAO = companiesDAO;
		this.couponsDAO = couponsDAO;
	}



	@Override
	public boolean login(String email, String password) throws SQLException {
		
		
		if (companiesDAO.isCompanyExisit(email, password) ==true) {
			
			this.companyID=companiesDAO.getCompanyID(email, password);
			
			return true;
			
		}else
		return false;
	}
	
	
//**************************************************************************add new coupon**********************************************************************	
	public void addCoupon(Coupon newCoupon) throws SQLException {
		

		
		boolean flag =false;
		
	
		ArrayList<Coupon> array = couponsDAO.getCouponsByCompanyID(companyID);
		
	   
	     Date date=Date.valueOf(java.time.LocalDate.now());
	     Iterator<Coupon> itr=array.iterator(); 
	     while(itr.hasNext()){  
	    	    Coupon coupon=(Coupon)itr.next();  

	    	   if(coupon.getTitle().equals(newCoupon.getTitle())) {
	    		   System.out.println("Coupon title already exists.");
	    		   flag=true;
	    	   }  
	     }
	    	if(date.after(newCoupon.getEndDate())== true) { 
	    	 System.out.println("An expired coupon cannot be added !");
	    	 flag = true;   
	    	 
	     }
		
		if (flag ==false) {
			couponsDAO.addCoupon(newCoupon);
		}
		
	     }
	
		 
		 
		 
//**************************************************************************update company**********************************************************************		 
	 public void updateCoupon(Coupon updetedCoupon) throws SQLException{
		 	
			
			boolean flag =false;
			
			
			ArrayList<Coupon> array = couponsDAO.getCouponsByCompanyID(companyID);
		     
		     
		     Iterator<Coupon> itr=array.iterator(); 
		     while(itr.hasNext()){  
		    	    Coupon coupon=(Coupon)itr.next();  
		    	    
		    	   if(coupon.getTitle().equals(updetedCoupon.getTitle())) {
		    		  
		    		   flag=true;
		    		   updetedCoupon.setId(coupon.getId());
		    		   couponsDAO.updateCoupon(updetedCoupon);
		    	   }   
		     }
			
			if (flag ==false) {
				System.out.println("Coupon does not exists.");
			}
			
		 
	 }
	 
	 
	 
//**************************************************************************delete company**********************************************************************	 
	public void deleteCoupon(int couponID) throws SQLException{
				
		boolean flag =false;
		
		
		ArrayList<Coupon> array = couponsDAO.getCouponsByCompanyID(companyID);
	     
	     
	     Iterator<Coupon> itr=array.iterator(); 
	     while(itr.hasNext()){  
	    	    Coupon coupon=(Coupon)itr.next();  
	    	    
	    	   if(coupon.getId()==couponID) {
	    		  couponsDAO.deleteCouponPurchaseByID(couponID);
	    		  couponsDAO.deleteCoupon(couponID);
	    		  System.out.println("Coupon has been deleted");
	    		   flag=true;
	    		  
	    	   }   
	     }
		
		if (flag ==false) {
			System.out.println("Coupon does not exists.");
		
		}

	}

//**************************************************************************print company coupons**********************************************************************
	public ArrayList<Coupon> getCompanyCoupons() throws SQLException{
		 
		 ArrayList<Coupon> array = couponsDAO.getCouponsByCompanyID(companyID);
		 return array;
	 }
	
	
	
//**************************************************************************print company coupons by category**********************************************************************	
	public ArrayList<Coupon> getCompanyCoupons(Category category) throws SQLException{

		 
		 
		 ArrayList<Coupon> array = couponsDAO.getCouponsByCompanyID(companyID);
		 ArrayList<Coupon> couponByCategory = new ArrayList<>();
		 
	     Iterator<Coupon> itr=array.iterator(); 
	     while(itr.hasNext()){  
	    	    Coupon coupon=(Coupon)itr.next();  
	    	    
	    	   if(coupon.getCategory()==category) {
	    		   couponByCategory.add(coupon);
	    		  
	    	   }   
	     }
		 
		 return couponByCategory;
	 }


//**************************************************************************print company coupons up to max price**********************************************************************		
	public ArrayList<Coupon> getCompanyCoupons(double maxPrice) throws SQLException{

			 ArrayList<Coupon> array = couponsDAO.getCouponsByCompanyID(companyID);
		 ArrayList<Coupon> couponByPrice = new ArrayList<>();
		 
	     Iterator<Coupon> itr=array.iterator(); 
	     while(itr.hasNext()){  
	    	    Coupon coupon=(Coupon)itr.next();  
	    	    
	    	   if(coupon.getPrice()<=maxPrice) {
	    		   couponByPrice.add(coupon);
	    		  
	    	   }   
	     }
		 
		 return couponByPrice;
	 }
	
//**************************************************************************get company details**********************************************************************	
	public Company getCompanyDetails() throws SQLException {
		 Company company=companiesDAO.getOneCompany(companyID);
		 return company;
	 }
	
}
