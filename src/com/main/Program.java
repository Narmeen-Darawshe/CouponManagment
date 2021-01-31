package com.main;

import java.sql.SQLException;

import com.test.Test;

public class Program {

	public static void main(String[] args) throws SQLException, InterruptedException {
		Test testObj = new Test();
		
		testObj.testAll();

	}

}
