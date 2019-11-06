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
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class BuyProduct{
	String productNama;
	String productQty;
	String TransactionID;
	
	String date;
	String year;
	String Month;
	
	Image img;
	
    Koneksi konek = new Koneksi();
    
	ResultSet rs;
	//result set metadata: informasi atau data 
	ResultSetMetaData rsm;
	
	//==============form input===========
	JTextField prID,prName,prPrice, userID_text;
	JPanel Content, ID, name, price, quantity, isi, gambar, button, eventDate, userID;
	JScrollPane scroll,scroll2;
	JSpinner qty;
	JLabel productID,productName,productPrice,productqty,productImage, blank, blank2, eventDatelbl, orderDatelbl, userIDlbl;
	JButton add, remove, checkout;
	JComboBox DateList, MonthList, yearList;
	//===================================
	
	String productid;
	
	JFrame Frproduct;
	
	//table product
	Vector<Object> table_row;
	
	//table transaction
	Vector<Object> transaction;
	
	DefaultTableModel dtm;
	DefaultTableModel dtm2;
	
	public void buy(){
		Frproduct = new JFrame();
		Frproduct.setSize(1000, 600);
		Frproduct.setLocationRelativeTo(null);
		Frproduct.setLayout(new BorderLayout());
		Frproduct.setTitle("Buy Product");
		
		//----table header---
		Vector<String> columnNames = new Vector<>();
		columnNames.add("Product Id");
		columnNames.add("Product Name");
		columnNames.add("Product Price");
		columnNames.add("Product Image");
		dtm = new DefaultTableModel(columnNames, 0);
		
		//----------display table tentang product------------
		try {
			konek.koneksi();
			String sql = "Select ProductID, ProductName, ProductPrice, ProductImage from product";
			ResultSet rs=konek.stmt.executeQuery(sql);
			rsm = rs.getMetaData();
			while(rs.next()){
				table_row = new Vector<>();
				for(int i = 1; i<= rsm.getColumnCount(); i++){
					table_row.add(rs.getObject(i));
					
				}
					dtm.addRow(table_row);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JTable table = new JTable(dtm);
		
		
		JScrollPane scrollpane = new JScrollPane(table);
		scrollpane.setPreferredSize(new Dimension(0, 290));
		
		
		//-----------end display-----------------
		
		Content = new JPanel(new GridLayout(1,3));

		//-------image layout-----------
		gambar = new JPanel(new FlowLayout());

		productImage = new JLabel();
		
		
		img = new ImageIcon(this.getClass().getResource("/img/noimage.png")).getImage();
		productImage.setIcon(new ImageIcon(img));
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			try{
				img = new ImageIcon(this.getClass().getResource(table.getValueAt(table.getSelectedRow(), 3).toString())).getImage();
				productImage.setIcon(new ImageIcon(img));
			}catch(Exception e1){
				img = new ImageIcon(table.getValueAt(table.getSelectedRow(), 3).toString()).getImage();
				
				productImage.setIcon(new ImageIcon(img));
			}
			
			}
		});
		
		gambar.add(productImage);
		//----------------end image---------
		
		
		//---------------form input layout--------------
		isi = new JPanel(new GridLayout(7,1));
		
		//---------start id----------
		ID = new JPanel(new FlowLayout());

		productID = new JLabel();
		productID.setText("Product ID :");

		blank2 = new JLabel();
		blank2.setText("      ");
		
		prID = new JTextField();
		prID.setPreferredSize(new Dimension(200, 25));
		prID.setEnabled(false);

		ID.add(productID);
		ID.add(blank2);
		ID.add(prID);
		//----end id-------------
		
		//----id user---------

		userID = new JPanel(new FlowLayout());

		userIDlbl = new JLabel();
		userIDlbl.setText("User ID :             ");

		userID_text = new JTextField();
		userID_text.setPreferredSize(new Dimension(200, 25));
		userID_text.setEnabled(true);

		userID.add(userIDlbl);
		userID.add(userID_text);
		//----end name-----------
		
		//----start name---------

		name = new JPanel(new FlowLayout());

		productName = new JLabel();
		productName.setText("Product Name :");

		prName = new JTextField();
		prName.setPreferredSize(new Dimension(200, 25));
		prName.setEnabled(false);

		name.add(productName);
		name.add(prName);
		//----end name-----------
		//----start price----------
		price = new JPanel(new FlowLayout());

		productPrice = new JLabel();
		productPrice.setText("Product Price :");

		prPrice = new JTextField();
		prPrice.setPreferredSize(new Dimension(200, 25));
		prPrice.setEnabled(false);

		price.add(productPrice);
		price.add(prPrice);
		//----end price-------
		//----start quantity-------

		quantity = new JPanel(new FlowLayout());

		productqty = new JLabel();
		productqty.setText("Qty :");
		
		blank = new JLabel();
		blank.setText("                 ");

		qty = new JSpinner();
		qty.setPreferredSize(new Dimension(200, 25));

		quantity.add(productqty);
		quantity.add(blank);
		quantity.add(qty);
		//------end quantity-----------
		
		//-------------------------event date-------------------------------------
		eventDate = new JPanel(new FlowLayout());
		
		eventDatelbl = new JLabel();
		eventDatelbl.setText("Event Date :   ");
	
		yearList = new JComboBox<String>();
		yearList.addItem("Year");
		for(int i=0;i<5;i++){
			yearList.addItem(Integer.toString(2019+i));
		}
		
		MonthList = new JComboBox<String>();
		MonthList.addItem("Month");
		MonthList.addItem("January");
		MonthList.addItem("February");
		MonthList.addItem("March");
		MonthList.addItem("April");
		MonthList.addItem("May");
		MonthList.addItem("June");
		MonthList.addItem("July");
		MonthList.addItem("August");
		MonthList.addItem("September");
		MonthList.addItem("October");
		MonthList.addItem("November");
		MonthList.addItem("December");
		
		DateList = new JComboBox<String>();
		DateList.addItem("Date");
		for(int i=1;i<32;i++){
			DateList.addItem(Integer.toString(i));
		}
		
		eventDate.add(eventDatelbl);
		eventDate.add(yearList);
		eventDate.add(MonthList);
		eventDate.add(DateList);

		//-------------------------end---------------------------------
		
		
		//-------add, remove, checkout---------
		button = new JPanel(new FlowLayout()); 
		
		add = new JButton("Add to Cart");
		
		remove = new JButton("Remove");
		remove.setEnabled(false);
		
		checkout = new JButton("Checkout");
		
		button.add(add);
		button.add(remove);
		button.add(checkout);
		//-------------------------------------
isi.add(ID);
isi.add(userID);
isi.add(name);
isi.add(price);
isi.add(quantity);
isi.add(eventDate);	
isi.add(button);
//---end input layout--------

try{
	String getID = "Select TransactionID from headertransaction ORDER BY TransactionID DESC";
	ResultSet resultID = konek.stmt.executeQuery(getID);
	
			
				
				if(resultID.next()){
					String lastID = resultID.getString("TransactionID");
					Integer idNumber = Integer.parseInt(lastID.substring(2,5));
					idNumber++;
					TransactionID = "TR" + String.format("%03d", idNumber);
				}else{
					TransactionID = "TR001";
				}

	String sql = "INSERT INTO headertransaction (TransactionID, UserID, TransactionDate) VALUES (?,?,CURRENT_TIME());";

	PreparedStatement pst = konek.con.prepareStatement(sql);
	pst.setString(1, TransactionID);
	pst.setString(2, "CU000");
						
	pst.executeUpdate();

	

}catch(SQLException e1){
	e1.printStackTrace();
	
}


//-------------------MOUSE EVENT---------------------
table.addMouseListener(new MouseAdapter() {
	@Override
	public void mouseClicked(MouseEvent e) {
	
		int i = table.getSelectedRow();
		prID.setText(table.getValueAt(i, 0).toString());
		prName.setText(table.getValueAt(i, 1).toString());
		prPrice.setText(table.getValueAt(i, 2).toString());
	}
});


//----table header---
		Vector<String> headertable = new Vector<>();
		headertable.add("Product Name");
		headertable.add("Quantity");
		dtm2 = new DefaultTableModel(headertable, 0);
			
		

		
		
		//-----------------------add to cart-------------------------
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.isRowSelected(table.getSelectedRow())){
					
					
				year = (String) yearList.getSelectedItem();
				date = (String) DateList.getSelectedItem();
				Month = (String) MonthList.getSelectedItem();
				
				String evDate = year+"-"+month()+"-"+date;
				
					if(!userID_text.getText().isEmpty()){
						if((int) qty.getValue() > 0){
						if(Month.contains("Month") || year.contains("Year") || date.contains("Date")){
							JOptionPane.showMessageDialog(null, "pilih tanggal acara");
						}else{
						

				
					transaction = new Vector<>();
					
					productQty = qty.getValue().toString();
										
					transaction.add(prID.getText());
					transaction.add(productQty);
								
							
					dtm2.addRow(transaction);
									
								
								
				
			
				
			
				
				
				konek.koneksi();
				
				try{			
						
					String sql = "UPDATE headertransaction SET UserID = ?, eventDate = ? WHERE TransactionID = ?";

						PreparedStatement pst = konek.con.prepareStatement(sql);
						pst.setString(1, userID_text.getText());
						pst.setString(2, evDate);
						pst.setString(3, TransactionID);
									
						pst.executeUpdate();
						
						
					String sql1 = "INSERT INTO detailtransaction (TransactionID, ProductID, Qty, status) VALUES (?,?,?,?);";

						PreparedStatement pst1 = konek.con.prepareStatement(sql1);
						pst1.setString(1, TransactionID);
						pst1.setString(2, prID.getText().toString());
						pst1.setString(3, productQty);
						pst1.setString(4, "added");
									
						pst1.executeUpdate();

						

				}catch(SQLException e1){
					e1.printStackTrace();			
				}
				
				
				userID_text.setEnabled(false);
				yearList.setEnabled(false);
				MonthList.setEnabled(false);
				DateList.setEnabled(false);
			
			
					
						
						}
						}else{//if event date
							JOptionPane.showMessageDialog(null, "input quantity");
						}
						
				}else{//if event date
					JOptionPane.showMessageDialog(null, "input customer id");
				}
				
			}else{//if table
				JOptionPane.showMessageDialog(null, "Pilih barang terlebih dahulu");
			}
		}
			
			
	});
		//==========end add================
		
		
		JTable tableproduct = new JTable(dtm2);
		JScrollPane scrollpane2 = new JScrollPane(tableproduct);
		scrollpane2.setPreferredSize(new Dimension(0, 350));
		
		tableproduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				remove.setEnabled(true);
			}
		});
		
		//-----------------------------------------------------remove cart-------------------------
		remove.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					
					try {
						
						
						
						
						productid = tableproduct.getValueAt(tableproduct.getSelectedRow(), 0).toString();
						
						
						String sql = "DELETE FROM detailtransaction WHERE ProductID = ? AND TransactionID = ?";
						
						PreparedStatement pst = konek.con.prepareStatement(sql);
						pst.setString(1, productid);
						pst.setString(2, TransactionID);

						
						pst.executeUpdate();
						
						String sql1 = "Select TransactionID from detailtransaction where TransactionID='"+TransactionID+"' ";
						
						ResultSet rs=konek.stmt.executeQuery(sql1);
						if(!rs.next()){
						
							String sql2 = "DELETE FROM headertransaction WHERE TransactionID = ?";
							
							PreparedStatement pst2 = konek.con.prepareStatement(sql2);
							pst2.setString(1, TransactionID);

							
							pst2.executeUpdate();
							
							Frproduct.dispose();
						}
						
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
						JOptionPane.showMessageDialog(null, "Delete Success");
					
					
					dtm2.removeRow(tableproduct.getSelectedRow());
					
					remove.setEnabled(false);
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "Click the item first");
				}
			}
		});
		
		
		
		//--------------------------------------------------------------check out------------------------------
		checkout.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				konek.koneksi();
				
		try{			
				
			String sql1 = "UPDATE detailtransaction SET status = 'ordered' WHERE TransactionID = ?";

				PreparedStatement pst1 = konek.con.prepareStatement(sql1);
				pst1.setString(1, TransactionID);
							
				pst1.executeUpdate();

				JOptionPane.showMessageDialog(null, "Transaction Success");

		}catch(SQLException e1){
			e1.printStackTrace();			
		}
				
			Frproduct.dispose();
			}
		
		});
		
		
		//-----------end display-----------------
//---
		

		
		Content.add(gambar);
		Content.add(isi);
		Content.add(scrollpane2);
		
		Frproduct.add(scrollpane, "North");
		Frproduct.add(Content, "Center");
			
		Frproduct.setVisible(true);
		
		
		
	}
	
	public int month(){
		String Month = (String) MonthList.getSelectedItem();
		if(Month.contains("Month")){
			return 0;
		}else if(Month.contains("January")){
			return 1;
		}else if(Month.contains("February")){
			return 2;
		}else if(Month.contains("March")){
			return 3;
		}else if(Month.contains("April")){
			return 4;
		}else if(Month.contains("May")){
			return 5;
		}else if(Month.contains("June")){
			return 6;
		}else if(Month.contains("July")){
			return 7;
		}else if(Month.contains("August")){
			return 8;
		}else if(Month.contains("September")){
			return 9;
		}else if(Month.contains("October")){
			return 10;
		}else if(Month.contains("November")){
			return 11;
		}else if(Month.contains("December")){
			return 12;
		}
		return month();
	}
}

