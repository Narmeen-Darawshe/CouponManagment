package com.util;

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException;
import java.util.*; 
  
public class ConnectionPool { 
  
    private static Connection con = null; 
    private static ConnectionPool instance =null;
    static final int maxNoOfConnections = 10;
    private Set<Connection> inUseConnections =new HashSet<Connection>();
    private static ArrayList<Connection> availableConnections =new ArrayList<Connection>();

    private static final String url = "jdbc:mysql://localhost:3306/devtech?useSSL=false&serverTimezone=UTC"; 
    private static final String user = "root"; 
    private static final String pass = "root-123-toor"; 
    
    
    
 // Initialize and add the connections in pool(availableConnections)
	private ConnectionPool() throws SQLException {
		
            try {
            	
				Class.forName("com.mysql.cj.jdbc.Driver");
			 
            for (int i=0;i<maxNoOfConnections;i++) {
            	
            	con = DriverManager.getConnection(url, user, pass); 
            availableConnections.add( con);
            }
            
       } catch (ClassNotFoundException e) {
			
				e.printStackTrace();
			}
		
	}
    
    
    public static ConnectionPool gesInstance() throws SQLException {
    	if(instance == null)
    		instance=new ConnectionPool();
    	 return instance ;
    }
    		
   
        
 
   //get connection if there is available connection if not wait
    public Connection getConnection() throws SQLException, InterruptedException{ 
    	synchronized(availableConnections) {
    if(availableConnections.size()==0) {
    	availableConnections.wait();
    
    }
    }
    	 Connection con =availableConnections.remove(availableConnections.size()-1);
    	inUseConnections.add(con);
    	 return con;
    
    	
        
    } 
    
    
 //return the connection after using it to the available list   
    public boolean releaseConnection(Connection connection) {
    
    	if (con!= null) {
    		        availableConnections.add(connection);
        return inUseConnections.remove(connection);
        
    	}else
            return false;
        
    	}
    
    
  // restore connection when it available and return it to the list   
    public void restoreConnection(Connection connection) 
	{ 
    	synchronized(availableConnections) {
    		
			availableConnections.add(connection);
			availableConnections.notify();
			
			
		}	
	} 

  
 //close all connection in the list
    	public void closeAllConnections() throws SQLException {
    		for (Connection con : availableConnections) {
    			con.close();
    		}
    	}
    
    
    
}