package practice;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Window {
	private JFrame frame;
	private JPanel p1;
	private Container contentPane;
	private JTextField textField;
	private JLabel label;
	
	Window(String title){
		this.frame = new JFrame(title);
		this.frame.setSize(500, 500);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLocationRelativeTo(null);
		this.contentPane = this.frame.getContentPane();
		
		this.p1 = new JPanel();
		// this.p1.setBackground(Color.DARK_GRAY);
		this.p1.setLayout(new BorderLayout());
		
		this.textField = new JTextField(1);
		
		this.label = new JLabel();
		this.label.setText("Objectの入力");
		
		this.p1.add(this.label, BorderLayout.NORTH);
		this.p1.add(this.textField, BorderLayout.CENTER);
		this.contentPane.add(this.p1, BorderLayout.NORTH);
		
		// 最後に書かないと未完成の状態で表示されちゃう
		frame.setVisible(true);
	}
	
	public void paintComponent(Graphics g) {
		this.p1.paintComponents(g);
		g.drawString("TEST", 400, 400);
		System.out.println("Check");
	}
}