package internal_management_order;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class Manageuser {
	Koneksi konek = new Koneksi();
	
	Vector<String> header = new Vector<>();
	Vector<Vector<Object>> data = new Vector<>();
	Vector<Object> User;
	JFrame user;
	JTextField usrID,usrEmail, usrPhone, usrFname, usrLname;
	JButton insert, delete, update, submit, cancel;
	JPanel Content, ID, email, fname, lname, isi, phone, address, bttn, insrt, dlt, updt, cncl, sbmt;
	JScrollPane scroll;
	JTable table;
	JLabel userID, userEmail, userPhone, userAddress, userFname, userLname;
	JTextArea usrAddress;
	
	String UserID;
	//
	String getEmail;
	String getPass;
	String getAddress;
	String getPhone;
	String getFname;
	String getLname;

	
	DefaultTableModel dtm;
	
	ResultSetMetaData rsm;
	
	/*public Manageuser(){
		Manage();
	}*/
	public void Manage(){
			
			user = new JFrame();
			user.setSize(1000, 600);
			user.setLocationRelativeTo(null);
			user.setLayout(new BorderLayout());
			user.setTitle("Manage User");
			
			//-------------start table atas-----------------
		
			header = new Vector<>();
			header.add("User ID");
			header.add("Email");
			
			header.add("First Name");
			header.add("Last Name");
			header.add("Phone");
			header.add("Address");
			
			dtm = new DefaultTableModel(header, 0);
			
			try{
				
				konek.koneksi();
				String sql = "Select UserID, UserEmail, UserFname, UserLname, UserPhone, UserAddress from user";
				ResultSet rs=konek.stmt.executeQuery(sql);
				rsm = rs.getMetaData();
			
			while(rs.next()){
			

			User = new Vector();
			for(int i = 1; i<= rsm.getColumnCount(); i++){
				User.add(rs.getObject(i));
				
				}
				dtm.addRow(User);
			}
		}catch(SQLException e){
			e.printStackTrace(); 
		}
			
			
			table = new JTable(dtm);
//			table.setAutoCreateRowSorter(true); //auto sorting
			
			//TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
			//table.setRowSorter(sorter);  cara sorting ke-2
			
			scroll = new JScrollPane(table);
			scroll.setPreferredSize(new Dimension(0, 300));
			
			
			
			//-------------end table atas-----------------
			
			//------center container-------------
				
			Content = new JPanel(new GridLayout(1,2));
				//---------------start input layout---------
				isi = new JPanel(new GridLayout(6,1));
							
						//---------start id----------
						ID = new JPanel(new FlowLayout());
			
						userID = new JLabel();
						userID.setText("User ID :                ");
			
						usrID = new JTextField();
						usrID.setPreferredSize(new Dimension(200, 20));
						usrID.setEnabled(false);
				
						ID.add(userID);
						ID.add(usrID);
						//----end id-------------
						//----start email---------
				
						email = new JPanel(new FlowLayout());
			
						userEmail = new JLabel();
						userEmail.setText("User Email :          ");
			
						usrEmail = new JTextField();
						usrEmail.setPreferredSize(new Dimension(200, 20));
						usrEmail.setEnabled(false);
				
						email.add(userEmail);
						email.add(usrEmail);
						//----end email-----------
						
						//----start Fname---------
						
						fname = new JPanel(new FlowLayout());
			
						userFname = new JLabel();
						userFname.setText("User First Name : ");
			
						usrFname = new JTextField();
						usrFname.setPreferredSize(new Dimension(200, 20));
						usrFname.setEnabled(false);
				
						fname.add(userFname);
						fname.add(usrFname);
						//----end Fname-----------
						
						//----start Lname---------
						
						lname = new JPanel(new FlowLayout());
			
						userLname = new JLabel();
						userLname.setText("User Last Name : ");
			
						usrLname = new JTextField();
						usrLname.setPreferredSize(new Dimension(200, 20));
						usrLname.setEnabled(false);
				
						lname.add(userLname);
						lname.add(usrLname);
						//----end Lname-----------
						
						
						//-------start phone---------
						phone = new JPanel(new FlowLayout());
						
						userPhone = new JLabel();
						userPhone.setText("User Phone :        ");
			
						usrPhone = new JTextField();
						usrPhone.setPreferredSize(new Dimension(200, 20));
						usrPhone.setEnabled(false);
				
						phone.add(userPhone);
						phone.add(usrPhone);
						//--------end phone------------------
						//-------start address---------
						address = new JPanel(new FlowLayout());
						
						userAddress = new JLabel();
						userAddress.setText("User Address :    ");
			
						usrAddress = new JTextArea();
						usrAddress.setPreferredSize(new Dimension(200, 20));
						usrAddress.setEnabled(false);
				
						address.add(userAddress);
						address.add(usrAddress);
						//---------end address-----------------
						
						
				isi.add(ID);
				isi.add(email);
				isi.add(fname);
				isi.add(lname);
				isi.add(phone);
				isi.add(address);
				//---end input layout--------
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						int i = table.getSelectedRow();
						
						usrID.setText(table.getValueAt(i, 0).toString());
						
						//
						update.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								submit.setEnabled(true);
								cancel.setEnabled(true);
								delete.setEnabled(false);
								insert.setEnabled(false);
								
								usrEmail.setEnabled(true);
								usrFname.setEnabled(true);
								usrLname.setEnabled(true);
								usrPhone.setEnabled(true);
								usrAddress.setEnabled(true);
								
								int i = table.getSelectedRow();
								
								usrID.setText(table.getValueAt(i, 0).toString());
								usrEmail.setText(table.getValueAt(i, 1).toString());
								usrFname.setText(table.getValueAt(i, 2).toString());
								usrLname.setText(table.getValueAt(i, 3).toString());
								usrPhone.setText(table.getValueAt(i, 4).toString());
								usrAddress.setText(table.getValueAt(i, 5).toString());
								
								
							
							}
						});
						//
						delete.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
							try{
								usrID.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
							try {
								
								
								
								String sql = "DELETE FROM user WHERE UserID = ?";
								
								PreparedStatement pst = konek.con.prepareStatement(sql);
								pst.setString(1, usrID.getText());

								
								pst.executeUpdate();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								JOptionPane.showMessageDialog(null, "Delete Success");
								
								dtm.setRowCount(0); //untuk fungsi refresh
								try {
									String sql = "Select * from user";
									ResultSet rs=konek.stmt.executeQuery(sql);
									rsm = rs.getMetaData();
									while(rs.next()){
										User = new Vector<>();
										for(int i = 1; i<= rsm.getColumnCount(); i++){
											User.add(rs.getObject(i));
											
										}
											dtm.addRow(User);
									}
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}catch(Exception e1){
								
							}
							}
						});
					}
				});
				
				
				//--------------------------
				bttn = new JPanel(new GridLayout(5,1)); 
				
				insrt = new JPanel(new FlowLayout());
				dlt = new JPanel(new FlowLayout());
				updt = new JPanel(new FlowLayout());
				sbmt = new JPanel(new FlowLayout());
				cncl = new JPanel(new FlowLayout());
				
				insert = new JButton("Insert");
				delete = new JButton("Delete");
				
				update = new JButton("Update");
				
				submit = new JButton("Submit");
				submit.setEnabled(false);
				
				cancel = new JButton("Cancel");
				cancel.setEnabled(false);
				
				insrt.add(insert);
				dlt.add(delete);
				updt.add(update);
				sbmt.add(submit);
				cncl.add(cancel);
				
				bttn.add(insrt);
				bttn.add(dlt);
				bttn.add(updt);
				bttn.add(sbmt);
				bttn.add(cncl);
				//-----------------
			Content.add(isi);
			Content.add(bttn);
			//------------end container------
			
			//---------------------------------------INSERT BUTTON------------------------------
			insert.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					usrID.setText(null);
					usrEmail.setEnabled(true);
					usrFname.setEnabled(true);
					usrLname.setEnabled(true);
					usrPhone.setEnabled(true);
					usrAddress.setEnabled(true);
					
					submit.setEnabled(true);
					cancel.setEnabled(true);
					delete.setEnabled(false);
					update.setEnabled(false);
					
					konek.koneksi();
					try{
						String getID = "Select UserID from user ORDER BY UserID DESC";
						ResultSet resultID = konek.stmt.executeQuery(getID);
						
								
						//---------------------------------------GENERATE USER ID--------------------------------------
									if(resultID.next()){
										String lastID = resultID.getString("UserID");
										Integer idNumber = Integer.parseInt(lastID.substring(2,5));
										idNumber++;
										UserID = "CU" + String.format("%03d", idNumber);
									}else{
										UserID = "CU001";
									}
						
									
					}catch(SQLException e1){
						e1.printStackTrace();	
					}
				}});
	
			//-----------------submit------------------------
			submit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				
					getEmail = usrEmail.getText();
					getFname = usrFname.getText();
					getLname = usrLname.getText();
					getAddress = usrAddress.getText();
					getPhone = usrPhone.getText();
					

					
//---------------------validating insert dan update form----------------------------			
				if(getEmail.isEmpty()){
					JOptionPane.showMessageDialog(null, "Please fill the Email");
				
				}else if(getFname.isEmpty()){
					JOptionPane.showMessageDialog(null, "Please fill First Name");
				
				}else if(getLname.isEmpty()){
					JOptionPane.showMessageDialog(null, "Please fill Last Name");
				
				}else if(getPhone.isEmpty()){
					JOptionPane.showMessageDialog(null, "Please fill the Phone");
					
				}else if(getAddress.isEmpty()){
					JOptionPane.showMessageDialog(null, "Please fill the Address");
					
				}else{
					
					
							if((!getEmail.startsWith("@") && !getEmail.startsWith("."))
								&& (!getEmail.contains("@."))
									&& (getEmail.contains("@")&& getEmail.contains(".")) &&(validasiEmail() == true)
								){
										if(phoneNumeric() == true){//validasi phone number numeric
											if(getPhone.length() > 11){
									 
							//------------insert data to database-----------------

							if(usrID.getText().isEmpty()){
									konek.koneksi();
								try{
									
									String sql = "INSERT INTO user (UserID, UserEmail, UserFname, UserLname, UserPhone, UserAddress) "
											+ "VALUES (?,?,?,?,?,?);";

										PreparedStatement pst = konek.con.prepareStatement(sql);
										pst.setString(1, UserID);
										pst.setString(2, usrEmail.getText());
										pst.setString(3, usrFname.getText());
										pst.setString(4, usrLname.getText());
										pst.setString(5, usrPhone.getText());
										pst.setString(6, usrAddress.getText());
										
										pst.executeUpdate();
										
										JOptionPane.showMessageDialog(null, "Insert Success");
										
										
										
										
									}catch(SQLException e1){
										e1.printStackTrace();
										
									}
								//---------------update data to database---------------
							}else{
								
								try {
									
								
									String sql = "UPDATE user set UserEmail = ?, UserFname = ?, UserLname = ?, UserPhone = ?, UserAddress = ? where UserID = ?";
								
								
								
								PreparedStatement pst = konek.con.prepareStatement(sql);
								pst.setString(1, usrEmail.getText());
								pst.setString(2, usrFname.getText());
								pst.setString(3, usrLname.getText());
								pst.setString(4, usrPhone.getText());
								pst.setString(5, usrAddress.getText());
								pst.setString(6, usrID.getText());

						
									
								pst.executeUpdate();
								
								JOptionPane.showMessageDialog(null, "Update Success");
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
											}else{
												JOptionPane.showMessageDialog(null, "the phone number must be more than 11 digits");
											}
										}else{
											JOptionPane.showMessageDialog(null, "the phone number must be numeric only");
										}
									
					}else{
						JOptionPane.showMessageDialog(null, "Incorrect email format");
					
					}
				}
				
				submit.setEnabled(false);
				cancel.setEnabled(false);
				delete.setEnabled(true);
				update.setEnabled(true);
				insert.setEnabled(true);

				usrID.setText(null);
				usrEmail.setText(null);
				usrFname.setText(null);
				usrLname.setText(null);
				usrPhone.setText(null);
				usrAddress.setText(null);
				
				
				dtm.setRowCount(0); //untuk fungsi refresh
				try {
					String sql = "Select * from user";
					ResultSet rs=konek.stmt.executeQuery(sql);
					rsm = rs.getMetaData();
					while(rs.next()){
						User = new Vector<>();
						for(int i = 1; i<= rsm.getColumnCount(); i++){
							User.add(rs.getObject(i));
							
						}
							dtm.addRow(User);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
				}
			});
			
			cancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					submit.setEnabled(false);
					cancel.setEnabled(false);
					delete.setEnabled(true);
					update.setEnabled(true);
					insert.setEnabled(true);
					
					usrID.setText(null);
					usrEmail.setText(null);
					usrFname.setText(null);
					usrLname.setText(null);
					usrPhone.setText(null);
					usrAddress.setText(null);
					
					usrEmail.setEnabled(false);
					usrFname.setEnabled(false);
					usrLname.setEnabled(false);
					usrPhone.setEnabled(false);
					usrAddress.setEnabled(false);
					
					
				}
			});
			//
				
		user.add(scroll, "North");
		user.add(Content, "Center");
			
		user.setVisible(true);
	}
	
	
	public boolean validasiEmail(){
		int container1 = 0;
		for(int i = 0; i<getEmail.length();i++){
			if(getEmail.charAt(i)=='@'){
				container1 = container1 + 1;			
			}
		}
		if(container1 > 1){
			return false;
		}else{
			return true;
		}
	}
	
	//validasi phone number (numeric only)
	public boolean phoneNumeric(){
		if(getPhone.matches("[0-9]+")){
			return true;
		}else{
			return false;
		}
	}
}


