package com.facade;

import java.sql.SQLException;

import com.dao.CompaniesDAO;
import com.dao.CouponsDAO;
import com.dao.CustomersDAO;

public abstract class ClientFacade {

	protected CompaniesDAO companiesDAO;
	protected CustomersDAO customersDAO;
	protected CouponsDAO couponsDAO;
	
	public abstract boolean login(String email, String password) throws SQLException; 
		
	
	
}
