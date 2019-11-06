package internal_management_order;

import java.sql.*;
import com.mysql.jdbc.Driver; 


public class Koneksi {
	Connection con;
	Statement stmt;
	
	public void koneksi(){

		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/internal_management_order", "root", "");
			stmt = con.createStatement();
		}catch(Exception e){
			
		}
		
	}
}