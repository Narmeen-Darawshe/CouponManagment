package com.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.core.Coupon;

public interface CouponsDAO {

	
	public void addCoupon(Coupon coupon) throws SQLException;
	public void updateCoupon(Coupon coupon) throws SQLException;
	public void deleteCoupon(int couponID) throws SQLException;
	public ArrayList<Coupon> getAllCoupons() throws SQLException; 
	public Coupon getOneCoupon(int couponID) throws SQLException;
	public ArrayList<Coupon> getCouponsByCompanyID(int companyID) throws SQLException;
	public void addCouponPurchase(int customerID, int couponID) throws SQLException;
	public ArrayList<Coupon> getCouponPurchase(int customerID) throws SQLException;
	public void deleteCouponPurchase(int customerID, int couponID) throws SQLException;
	public boolean getCouponPurchaseByCouponID(int couponID) throws SQLException;
	public void deleteCouponPurchaseByID( int couponID) throws SQLException;
	public void deleteCouponPurchaseByCustomerID( int customerID) throws SQLException ;
	
}
