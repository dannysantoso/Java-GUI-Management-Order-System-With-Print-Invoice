package internal_management_order;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login{

	JFrame loginFrame;
	Container container;
	JPanel LoginSec,title,EmailSec,PassSec,SubmitSec;
	JLabel logintxt,Email,Pass, blank;
	JTextField EmailInput;
	JPasswordField PassInput;
	JButton submit_button;
	
		
		public void login(){

			loginFrame = new JFrame();
			loginFrame.setSize(400,200);
			loginFrame.setLocationRelativeTo(null);
			loginFrame.setTitle("Login");

			container = loginFrame.getContentPane();
			
			//---------------------login panel------------------------------------------------
			LoginSec = new JPanel(new GridLayout(4,1));
			
			//---------------------judul login panel----------------------------------
			title = new JPanel(new FlowLayout());
			
			logintxt = new JLabel();
			logintxt.setText("Login");
			logintxt.setFont(new Font("Arial", Font.PLAIN ,20));
			
			title.add(logintxt);
		
			//-----------------------------end judul----------------------------------
			
			//---------------------panel email----------------------------------------
			EmailSec = new JPanel(new FlowLayout());
			
			Email = new JLabel();
			Email.setText("Email :");
			
			blank = new JLabel();
			blank.setText("       ");
		
			EmailInput = new JTextField();
			EmailInput.setPreferredSize(new Dimension(100,20));
			
			EmailSec.add(Email);
			EmailSec.add(blank);
			EmailSec.add(EmailInput);
			
			//------------------------end email----------------------------------------
			
			//----------------------panel password-------------------------------------
			PassSec = new JPanel(new FlowLayout());	
			
			Pass = new JLabel();
			Pass.setText("Password :");
		
			PassInput = new JPasswordField();
			PassInput.setPreferredSize(new Dimension(100,20));
			PassInput.setEchoChar('*');
			
			PassSec.add(Pass);
			PassSec.add(PassInput);
			
			//------------------------end password--------------------------------------
			
			//----------------------panel Submit and register---------------------------
			SubmitSec = new JPanel(new FlowLayout()); 
			
			submit_button = new JButton("Submit");
			SubmitSec.add(submit_button);
			
			//------------------------end submit and register--------------------------
			
			
			LoginSec.add(EmailSec);
			LoginSec.add(PassSec);
			
			LoginSec.add(SubmitSec);
			
			loginFrame.add(title, "North");
			loginFrame.add(LoginSec, "Center");
			
			//--------------------------end Login Panel-----------------------------------------
			
			loginFrame.setVisible(true);
			
		}
}