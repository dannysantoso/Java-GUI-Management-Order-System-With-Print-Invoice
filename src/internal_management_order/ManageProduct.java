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
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ManageProduct {
	Koneksi konek = new Koneksi();
	
	Image img;	
	
	Vector<String> header = new Vector<>();
	Vector<Vector<Object>> data = new Vector<>();
	Vector<Object> Product;
	JFrame manageProduct;
	JTextField prID,prName, chseImage;
	JButton insert, delete, update, submit, cancel, choose;
	JPanel Content, ID, name, price, isi, gambar, table2, chse, bttn, insrt, dlt, updt, cncl, sbmt;
	JScrollPane scroll;
	JTable table;
	JLabel productID,productName,productPrice, chooseImage, productImage;
	JComboBox<String> yearList,MonthList,DateList,role;
	JPasswordField usrPass;
	JRadioButton Male, Female;
	String genderGet;
	ButtonGroup genderGroup;
	JSpinner prPrice;
	
	String ProductID;
	
	ResultSetMetaData rsm;
	DefaultTableModel dtm;
	
	String fileName;
	
	
	public void Manage(){
			
		manageProduct = new JFrame();
		manageProduct.setSize(1000, 600);
		manageProduct.setLocationRelativeTo(null);
		manageProduct.setLayout(new BorderLayout());
		manageProduct.setTitle("Manage Product");
			
			//-------------start table atas-----------------
			
				
				header = new Vector<>();
				header.add("Product ID");
				header.add("Product Name");
				header.add("Product Price");
				header.add("Product Image");
				dtm = new DefaultTableModel(header, 0);
				
				//-------------------------------------table atas---------------------------------
				try {
					konek.koneksi();
					String sql = "Select ProductID, ProductName, ProductPrice, ProductImage from product";
					ResultSet rs=konek.stmt.executeQuery(sql);
					rsm = rs.getMetaData();
					while(rs.next()){
						Product = new Vector<>();
						for(int i = 1; i<= rsm.getColumnCount(); i++){
							Product.add(rs.getObject(i));
							
						}
							dtm.addRow(Product);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				table = new JTable(dtm);
				
				scroll = new JScrollPane(table);
				scroll.setPreferredSize(new Dimension(0, 350));
				//---------------------MOUSE CLICK EVENT-------------------------
				
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
						
						delete.setEnabled(true);
						update.setEnabled(true);
						
						int price = (int) table.getValueAt(table.getSelectedRow(), 2);
						
						
						int i = table.getSelectedRow();
						prID.setText(table.getValueAt(i, 0).toString());
						prName.setText(table.getValueAt(i, 1).toString());
						prPrice.setValue(Integer.valueOf(price));
						chseImage.setText(table.getValueAt(i, 3).toString());
						
						delete.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
							try{
								prID.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
							try {
								
								
								
								String sql = "DELETE FROM product WHERE ProductID = ?";
								
								PreparedStatement pst = konek.con.prepareStatement(sql);
								pst.setString(1, prID.getText());

								
								pst.executeUpdate();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								JOptionPane.showMessageDialog(null, "Delete Success");
								
								dtm.setRowCount(0); //untuk fungsi refresh
								try {
									String sql = "Select * from product";
									ResultSet rs=konek.stmt.executeQuery(sql);
									rsm = rs.getMetaData();
									while(rs.next()){
										Product = new Vector<>();
										for(int i = 1; i<= rsm.getColumnCount(); i++){
											Product.add(rs.getObject(i));
											
										}
											dtm.addRow(Product);
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
			
			//-------------end table atas-----------------
			
			//------center container-------------
				
			Content = new JPanel(new GridLayout(1,3));
				//-------image layout-----------
				gambar = new JPanel(new FlowLayout());
			
				productImage = new JLabel();
				//getImage();
			
				img = new ImageIcon(this.getClass().getResource("/img/noimage.png")).getImage();
				
				productImage.setIcon(new ImageIcon(img));
			
		
				gambar.add(productImage);
			//---------------end image layout----------
			isi = new JPanel(new GridLayout(4,1));
			
			//---------start id----------
			ID = new JPanel(new FlowLayout());

			productID = new JLabel();
			productID.setText("Product ID :      ");
			
			prID = new JTextField();
			prID.setPreferredSize(new Dimension(200, 25));
			prID.setEnabled(false);
	
			ID.add(productID);
			ID.add(prID);
			//----end id-------------
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

			prPrice = new JSpinner();
			prPrice.setPreferredSize(new Dimension(200, 25));
			prPrice.setEnabled(false);
	
			price.add(productPrice);
			price.add(prPrice);
			//----end price-------
			//-------add, remove, checkout---------
			chse = new JPanel(new FlowLayout()); 
			
			
			chooseImage = new JLabel();
			chooseImage.setText("Product Image :");
			
			
			chseImage = new JTextField();
			chseImage.setPreferredSize(new Dimension(125, 25));
			chseImage.setEnabled(false);
			
			choose = new JButton("Choose");
			
			
			chse.add(chooseImage);
			chse.add(chseImage);
			chse.add(choose);
			//-------------------------------------
	isi.add(ID);
	isi.add(name);
	isi.add(price);
	isi.add(chse);
	//---end input layout--------
				//---------------buttton-----------
				bttn = new JPanel(new GridLayout(5,1)); 
				
				insrt = new JPanel(new FlowLayout());
				dlt = new JPanel(new FlowLayout());
				updt = new JPanel(new FlowLayout());
				sbmt = new JPanel(new FlowLayout());
				cncl = new JPanel(new FlowLayout());
				
				insert = new JButton("Insert");
				
				delete = new JButton("Delete");
				delete.setEnabled(false);
				
				update = new JButton("Update");
				update.setEnabled(false);
				
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
				//-----------------------choose button---------------------
				choose.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						try{
						JFileChooser chs = new JFileChooser();
						
						chs.showOpenDialog(choose);
						
						File getFile = chs.getSelectedFile();
						fileName = getFile.getAbsolutePath();
						chseImage.setText(fileName);
						
						img = new ImageIcon(fileName).getImage();
						
						productImage.setIcon(new ImageIcon(img));
						
						}catch(Exception e1){
							
						}
					}
				});
				//-----------------------update button---------------------
				update.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
					
							submit.setEnabled(true);
							cancel.setEnabled(true);
							insert.setEnabled(false);
							delete.setEnabled(false);
							
							prName.setEnabled(true);
							prPrice.setEnabled(true);							
					
					}
				});
				//---------------------insert button--------------------
				insert.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
					
							prID.setText(null);
							prName.setText(null);
							prPrice.setValue(Integer.valueOf(0));
							chseImage.setText(null);
							
							prName.setEnabled(true);
							prPrice.setEnabled(true);
							
							submit.setEnabled(true);
							cancel.setEnabled(true);
							update.setEnabled(false);
							delete.setEnabled(false);
							insert.setEnabled(false);
							
							konek.koneksi();
							//---------------------------------------GENERATE PRODUCT ID--------------------------------------
							try{
								String getID = "Select ProductID from product ORDER BY ProductID DESC";
								ResultSet resultID = konek.stmt.executeQuery(getID);
								
										
											
											if(resultID.next()){
												String lastID = resultID.getString("ProductID");
												Integer idNumber = Integer.parseInt(lastID.substring(2,5));
												idNumber++;
												ProductID = "PR" + String.format("%03d", idNumber);
											}else{
												ProductID = "PR001";
											}

											
							}catch(SQLException e1){
								e1.printStackTrace();	
							}
					
					}
				});
				
				//---------------------cancel button--------------------
				cancel.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						submit.setEnabled(false);
						cancel.setEnabled(false);
						update.setEnabled(true);
						delete.setEnabled(true);
						insert.setEnabled(true);
						
						prName.setEnabled(false);
						prPrice.setEnabled(false);						
					
					}
				});
				
				//---------------------submit button--------------------
				submit.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						
						String getPrName = prName.getText();
						String getPrPrice = prPrice.getValue().toString();
						int harga = (int) prPrice.getValue();
						
						if(getPrName.isEmpty()){
							JOptionPane.showMessageDialog(null, "Please fill the Product Name");
							
						}else if(getPrPrice.isEmpty()){
							JOptionPane.showMessageDialog(null, "Please fill the Product Price");
						
						}
								//----------------------------------------insert product-------------------------------
								if(prID.getText().isEmpty()){
									try{
										konek.koneksi();
										
										String sql = "INSERT INTO product (ProductID, ProductName, ProductPrice, ProductImage) "
											+ "VALUES (?,?,?,?);";

										PreparedStatement pst = konek.con.prepareStatement(sql);
										pst.setString(1, ProductID);
										pst.setString(2, getPrName);
										pst.setString(3, getPrPrice);
										pst.setString(4, chseImage.getText());
										
										pst.executeUpdate();
										
										JOptionPane.showMessageDialog(null, "Insert Success");
										
										
										
										
									}catch(SQLException e1){
										e1.printStackTrace();
										
									}
								}else{
									//--------------------update--------------------------
									try {
										
										
										String sql = "UPDATE product set ProductName = ?, ProductPrice = ?, ProductImage = ? where ProductID = ?";
									
									
										PreparedStatement pst = konek.con.prepareStatement(sql);
										pst.setString(1, getPrName);
										pst.setString(2, getPrPrice);
										pst.setString(3, chseImage.getText());
										pst.setString(4, prID.getText());
									
										
									pst.executeUpdate();
									
									JOptionPane.showMessageDialog(null, "Update Success");
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									
								}
							
						submit.setEnabled(false);
						cancel.setEnabled(false);
						delete.setEnabled(true);
						update.setEnabled(true);
						insert.setEnabled(true);
						
						prName.setEnabled(false);
						prPrice.setEnabled(false);

						prID.setText(null);
						prName.setText(null);
						prPrice.setValue(Integer.valueOf(0));
						chseImage.setText(null);
						
						
						dtm.setRowCount(0); //untuk fungsi refresh
						try {
							String sql = "Select * from product";
							ResultSet rs=konek.stmt.executeQuery(sql);
							rsm = rs.getMetaData();
							while(rs.next()){
								Product = new Vector<>();
								for(int i = 1; i<= rsm.getColumnCount(); i++){
									Product.add(rs.getObject(i));
									
								}
									dtm.addRow(Product);
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
				});
				
				//-----------------
			Content.add(gambar);
			Content.add(isi);
			Content.add(bttn);
			//------------end container------
				
				
			manageProduct.add(scroll, "North");
			manageProduct.add(Content, "Center");
			
			manageProduct.setVisible(true);
	}
	
}