package internal_management_order;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.print.*;
import javafx.print.Printer;

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
import javax.swing.table.DefaultTableModel;

public class ViewTransaction {
	Koneksi konek = new Koneksi();
	printView pv = new printView();
	
	Vector<String> header = new Vector<>();
	
	Vector<Object> transaction;
	
	Vector<String> header2 = new Vector<>();
	
	Vector<Object> Product;
	
	//-------
	String trID;
	String usID;
	String usName;
	String usEmail;
	String orDate;
	String evDate;
	
	//--
	Vector<String> prID;
	Vector<String> qty;
	Vector<String> price;
	Vector<String> prName;
	//--
	
	
	//------
	
	JFrame viewtrans;
	JScrollPane scroll, scroll2;
	JTable table, table2;
	JPanel button;
	JButton delete, invoice;

	DefaultTableModel dtm;
	DefaultTableModel dtm2;
	
	ResultSetMetaData rsm;
	ResultSetMetaData rsm2;
	
	JScrollPane scrollpane;
	JScrollPane scrollpane2;
	
	String TransactionID;
	
	
	public void view(){
		viewtrans = new JFrame();
		viewtrans.setSize(1000, 600);
		viewtrans.setLocationRelativeTo(null);
		viewtrans.getContentPane().setLayout(new BorderLayout());
		viewtrans.setTitle("View Transaction");
		
		header = new Vector<>();
		header.add("Transaction ID");
		header.add("User ID");
		header.add("User Email");
		header.add("User First Name");
		header.add("User Last Name");
		header.add("Order Date");
		header.add("Event Date");
		dtm = new DefaultTableModel(header, 0);
		
		//-------------------------------------table atas---------------------------------
		try {
			konek.koneksi();
			String sql = "Select DISTINCT ht.TransactionID, ht.UserID, UserEmail, UserFname, UserLname, TransactionDate, eventDate from headertransaction ht, user u, detailtransaction dt where u.UserID = ht.UserID and ht.TransactionID = dt.TransactionID and status = 'ordered'";
			ResultSet rs=konek.stmt.executeQuery(sql);
			rsm = rs.getMetaData();
			while(rs.next()){
				transaction = new Vector<>();
				for(int i = 1; i<= rsm.getColumnCount(); i++){
					transaction.add(rs.getObject(i));
					
				}
					dtm.addRow(transaction);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		table = new JTable(dtm);
		
		scrollpane = new JScrollPane(table);
		scrollpane.setPreferredSize(new Dimension(0, 275));
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TransactionID = table.getValueAt(table.getSelectedRow(), 0).toString();
				//select all data
				try {
					dtm2.setRowCount(0); //ntar hapus kalo bug table product
					konek.koneksi();
					String sql = "Select p.ProductID, ProductName, Qty from detailtransaction dt, product p where p.ProductID = dt.ProductID and TransactionID = '"+TransactionID+"'";
					ResultSet rs=konek.stmt.executeQuery(sql);
					rsm2 = rs.getMetaData();
					while(rs.next()){
						Product = new Vector<>();
						for(int i = 1; i<= rsm2.getColumnCount(); i++){
							Product.add(rs.getObject(i));
							
						}	
						dtm2.addRow(Product);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		
		//------------------------------------table bawah-----------------------------------
		header2 = new Vector<>();
		header2.add("Product ID");
		header2.add("Product Name");
		header2.add("Quantity");
		dtm2 = new DefaultTableModel(header2, 0);
		
	
		
		
		table2 = new JTable(dtm2);
		
		
		scrollpane2 = new JScrollPane(table2);
		scrollpane2.setPreferredSize(new Dimension(0, 275));
		
		
		
		button = new JPanel(new FlowLayout());
		button.setSize(0, 50);
		delete = new JButton("Delete");
		invoice = new JButton("Invoice");
		
		button.add(delete);
		button.add(invoice);
		
		viewtrans.add(scrollpane, "North");
		viewtrans.add(scrollpane2, "Center");
		viewtrans.add(button, "South");
					
		viewtrans.setVisible(true);
	
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				int i = table.getSelectedRow();
				trID = table.getValueAt(i, 0).toString();
				usID = table.getValueAt(i, 1).toString();
				usEmail = table.getValueAt(i, 2).toString();
				usName = table.getValueAt(i, 3).toString() + " " +table.getValueAt(i, 4).toString();
				orDate = table.getValueAt(i, 5).toString();
				evDate = table.getValueAt(i, 6).toString();
				
				try {
					konek.koneksi();
					String sql = "Select p.ProductID, ProductName, Qty, ProductPrice from detailtransaction dt, product p where p.ProductID = dt.ProductID and TransactionID = '"+trID+"'";
					ResultSet rs=konek.stmt.executeQuery(sql);
					
						prID = new Vector();
						prName = new Vector();
						qty = new Vector();
						price = new Vector();
					while(rs.next()){
						prID.add(rs.getString("p.ProductID"));
						prName.add(rs.getString("ProductName"));
						qty.add(rs.getString("Qty"));
						price.add(rs.getString("ProductPrice"));
					}
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
			}
		});
		
		
		invoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(usName == null){
					JOptionPane.showMessageDialog(null, "Click the transaction first");
				}else{
				pv.print();
				Vector<Integer> jumlahvec = new Vector<Integer>();
				pv.printData.setText(pv.printData.getText() + "\n\n\n\n\n\n\n");
				pv.printData.setText(pv.printData.getText() + "Name :" + usName + "\n\n");
				pv.printData.setText(pv.printData.getText() + "Email :" + usEmail + "\n\n");
				pv.printData.setText(pv.printData.getText() + "Transaction ID :" + trID + "\t\t\t Order Date : " + orDate +"\n\n\n");
				pv.printData.setText(pv.printData.getText() + "Product ID \t Product Name \t \t Price \t Qty \t Total Price \n\n");
			for(int i=0; i<prID.size();i++){
				pv.printData.setText(pv.printData.getText() + prID.get(i) + "\t");
				pv.printData.setText(pv.printData.getText() + prName.get(i) + "\t\t");
				pv.printData.setText(pv.printData.getText() + price.get(i)+"\t");
				int jumlah = Integer.parseInt(qty.get(i)) * Integer.parseInt(price.get(i));
				jumlahvec.add(jumlah);
				pv.printData.setText(pv.printData.getText() + qty.get(i)+ "\t");
				pv.printData.setText(pv.printData.getText() + jumlah);
				pv.printData.setText(pv.printData.getText() + "\n\n");
			}
			int total = 0;
			for(int i=0; i<jumlahvec.size(); i++){
				total = total + jumlahvec.get(i);
			}
			pv.printData.setText(pv.printData.getText() + "\t Total \t\t\t\t" + total);
				
				
			pv.printInvoice.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try{
						pv.printData.print();
						
						konek.koneksi();
						
						try{			
								
							String sql1 = "UPDATE detailtransaction SET status = 'done' WHERE TransactionID = ?";

								PreparedStatement pst1 = konek.con.prepareStatement(sql1);
								pst1.setString(1, TransactionID);
											
								pst1.executeUpdate();

						}catch(SQLException e1){
							e1.printStackTrace();			
						}
						
						
					}catch(java.awt.print.PrinterException e1){
						System.err.format("No Printer Found", e1.getMessage());
					}
				}
			});
				
				
				}
			}
		});
		
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{	
					try {
						TransactionID = table.getValueAt(table.getSelectedRow(), 0).toString();
						

						String sql = "DELETE FROM detailtransaction WHERE TransactionID = ?";
						
						PreparedStatement pst = konek.con.prepareStatement(sql);
						pst.setString(1, TransactionID);

						
						pst.executeUpdate();
						
						
												
							String sql2 = "DELETE FROM headertransaction WHERE TransactionID = ?";
							
							PreparedStatement pst2 = konek.con.prepareStatement(sql2);
							pst2.setString(1, TransactionID);

							
							pst2.executeUpdate();
							
						
						
						
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
						JOptionPane.showMessageDialog(null, "Delete Success");
					
					
					dtm.removeRow(table.getSelectedRow());
					dtm2.setRowCount(0);
					
					
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "Click the item first");
				}
				
			}
		});
			
		
		
	}

}
