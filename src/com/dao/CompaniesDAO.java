package com.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.core.Company;  

public interface CompaniesDAO {

	public boolean isCompanyExisit(String email, String password) throws SQLException; 
	public int getCompanyID(String name, String password) throws SQLException;
	public boolean isCompanyExisitByName(String name, String password) throws SQLException; 
	public void addCompany(Company company) throws SQLException;
	public void updateCompany(Company company) throws SQLException;
	public void deleteCompany(int companyID) throws SQLException;
	public ArrayList<Company> getAllCompanies() throws SQLException; 
	public Company getOneCompany(int companyID) throws SQLException;
	
	
}
