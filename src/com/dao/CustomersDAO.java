package com.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.core.Customer;

public interface CustomersDAO {

	
	public boolean isCustomerExisit(String email,String password) throws SQLException; 
	public int getCustomerID(String email, String password) throws SQLException;
	public void addCustomer(Customer customer) throws SQLException;
	public void updateCustomer(Customer customer) throws SQLException;
	public void deleteCustomer(int customerID) throws SQLException;
	public ArrayList<Customer> getAllCustomers() throws SQLException, InterruptedException; 
	public Customer getOneCustomer(int customerID) throws SQLException;
	public boolean isCustomerExisitByID(int customerID) throws SQLException;
	
	
}
