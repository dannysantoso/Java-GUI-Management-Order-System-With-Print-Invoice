package internal_management_order;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class printView {
	
	JFrame viewPrint;
	JPanel printArea, printButton;
	JButton printInvoice;
	JTextArea printData;
	
	public void print(){
	viewPrint = new JFrame();
	viewPrint.setSize(550, 600);
	viewPrint.setLocationRelativeTo(null);
	viewPrint.getContentPane().setLayout(new BorderLayout());
	
	printArea = new JPanel(new FlowLayout());
	
	printData = new JTextArea();
	printData.setPreferredSize(new Dimension(500,500));
	
	
	printButton = new JPanel(new FlowLayout());
	
	
	printInvoice = new JButton("Print");
	
	printArea.add(printData);
	printButton.add(printInvoice);
	
	viewPrint.add(printArea, "North");
	viewPrint.add(printButton, "South");
	
	viewPrint.setVisible(true);
	
	
	}
	
}
