package internal_management_order;

import java.sql.*;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class Main {
	
	Login log =new Login();
	
	Staffmenu staff = new Staffmenu();
	
	Koneksi konek = new Koneksi();
	BuyProduct b = new BuyProduct();
	Manageuser m = new Manageuser();
	ManageProduct p = new ManageProduct();
	ViewTransaction vt = new ViewTransaction();
	

	
	String UserID;
	String adminID_login;
	String TransactionID;
	
	public Main(){
		main();
	}
	
	public void main(){
		log.login();
		log.submit_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String username = log.EmailInput.getText();
				String password = log.PassInput.getText();
				
				if(username.isEmpty()){
					JOptionPane.showMessageDialog(null, "You must fill the email field");
				}else if (password.isEmpty()){
					JOptionPane.showMessageDialog(null, "You must fill the password field");
				}else{
					
				konek.koneksi();
				String sql = "Select adminEmail, adminPassword from admin where adminEmail='"+log.EmailInput.getText()+"' "
						+ "and adminPassword='"+log.PassInput.getText().toString()+"'";
				
				
				String getID = "Select adminID from admin where adminEmail = '"+username+"'";
				
				try {
					ResultSet rs=konek.stmt.executeQuery(sql);
					if(rs.next()){
						ResultSet resultID = konek.stmt.executeQuery(getID);
					
						resultID.next();
					
						adminID_login = resultID.getString("adminID");
					
					
					

						log.loginFrame.dispose();
						
						staff.Staffmenu();
								
				//---------------------staff menu system----------------------------
								
								staff.logoff_item.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										staff.Staffframe.dispose();
										main();
									}
								});
								
								staff.exit_item.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										staff.Staffframe.dispose();
									}
								});
								
								staff.user_item.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										m.Manage();
									}
								});
								
								staff.viewtransaction_item.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										vt.view();
									}
								});
								
								staff.product_item.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										p.Manage();
									}
								});
								
								staff.buy_item.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										b.buy();
									}
								});
				//---------------------end staff menu system----------------------------
							}else{
								JOptionPane.showMessageDialog(null, "Invalid Email and Password");
								
							}
						}catch (SQLException e1){
						e1.printStackTrace();
						}

					}
					
				}
		});

				
	}
		
	public static void main(String[] args) {
		new Main();

	}
	/*public boolean validasiEmail(){
		int container1 = 0;
		int container2 = 0;
		for(int i = 0; i<email.length();i++){
			if(email.charAt(i)=='@'){
				container1 = container1 + 1;
			}else if(email.charAt(i)=='.'){
				container2 = container2 + 1;			
			}
		}
		if(container1 > 1 || container2 > 1){
			return false;
		}else{
			return true;
		}
	}
	
	//validasi password (alphanumeric only)
	public boolean passwordAlphanumeric(){
		if(pass.matches("[A-Za-z0-9]+")){
			return true;
		}else{
			return false;
		}
	}
	//validasi phone number (numeric only)
	public boolean phoneNumeric(){
		if(phone.matches("[0-9]+")){
			return true;
		}else{
			return false;
		}
	}*/
}
	/* cara kedua validasi phone number (numeric only)
	public boolean numeric() {
		for (int i = 0; i < phone.length(); i++) {
		      if (!Character.isDigit(phone.charAt(i)))
		        return false;
		    }
		    return true;
		  }
}*/