package com.core;

import java.util.*;

public class Company {

   private int id;
   private String name;
   private String email;
   private String password;
   private ArrayList <Coupon> coupons = new ArrayList <> ();
   
public Company() {
	super();
	
}


public Company(int id, String name, String email) {
	super();
	this.id = id;
	this.name = name;
	this.email = email;
}





public Company(int id, String name, String email, String password) {
	super();
	this.id = id;
	this.name = name;
	this.email = email;
	this.password = password;
}


public Company(String name, String email, String password) {
	super();
	this.name = name;
	this.email = email;
	this.password = password;
}


public Company(int id, String name, String email, String password, ArrayList<Coupon> coupons) {
	super();
	this.id = id;
	this.name = name;
	this.email = email;
	this.password = password;
	this.coupons = coupons;
}




public int getId() {
	return id;
}




public void setId(int id) {
	this.id = id;
}




public String getName() {
	return name;
}




public void setName(String name) {
	this.name = name;
}




public String getEmail() {
	return email;
}




public void setEmail(String email) {
	this.email = email;
}




public String getPassword() {
	return password;
}




public void setPassword(String password) {
	this.password = password;
}




public ArrayList<Coupon> getCoupons() {
	return coupons;
}




public void setCoupons(ArrayList<Coupon> coupons) {
	this.coupons = coupons;
}




@Override
public String toString() {
	return "Company [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", coupons=" + coupons + "]";
}
   
   
   
	
}
