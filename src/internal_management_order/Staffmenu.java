package internal_management_order;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Staffmenu{
	Image img = new ImageIcon(this.getClass().getResource("/img/background.jpg")).getImage();
	
	
	JFrame Staffframe;
	Container c;
	JMenuBar menubar;
	JMenu account_menu, manage_menu, transaction_menu;
	JMenuItem logoff_item, exit_item, user_item, product_item, viewtransaction_item, buy_item;
	JLabel gambar; 
	
	public void Staffmenu() {
		
		Staffframe = new JFrame(); 
		Staffframe.setSize(1200, 700);
		Staffframe.setLocationRelativeTo(null);
		Staffframe.setTitle("Main Form");

		c = Staffframe.getContentPane();
		
		//------------------------menubar----------------------------
		menubar = new JMenuBar();
			
			//----------------------menu Account----------------------
			account_menu = new JMenu("Account");
		
			logoff_item = new JMenuItem("Logoff");
			exit_item = new JMenuItem("Exit"); 		
				
			account_menu.add(logoff_item);				
			account_menu.add(exit_item);
			//-------------------------end account menu---------------------------	

			//----------------------menu Manage-------------------------
			manage_menu = new JMenu("Manage");
			
			user_item = new JMenuItem("User");
			product_item = new JMenuItem("Product"); 	
			
			manage_menu.add(user_item);
			manage_menu.add(product_item);
			
			//-------------------------end manage menu---------------------------
			
			//----------------------menu transaction-------------------------
			transaction_menu = new JMenu("Transaction");
			
			buy_item = new JMenuItem("Buy Product");
			viewtransaction_item = new JMenuItem("View Transaction"); 	
			
			transaction_menu.add(buy_item);
			transaction_menu.add(viewtransaction_item);
			//-------------------------end transaction menu---------------------------
			
		menubar.add(account_menu);
		menubar.add(manage_menu);
		menubar.add(transaction_menu);
		
		//-------------------gambar------------------------
		gambar = new JLabel("New label");
		gambar.setIcon(new ImageIcon(img));
		gambar.setBounds(0, 0, 1200,700);
		Staffframe.getContentPane().add(gambar);
				
		Staffframe.add(gambar);
		//----------------------end gambar---------------------
		
		Staffframe.setJMenuBar(menubar);
		Staffframe.setVisible(true);	
	}
}