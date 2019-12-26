package practice.view;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class JPanelOperator {
	
	private JPanel panel;
	
	public JPanelOperator(JPanel panel) {
		this.panel = panel;
	}

	public JLabel createLabel(String message, int x, int y, int width, int height) {
		 JLabel label = new JLabel();
		label.setForeground(Color.WHITE);
		label.setText(message);
		label.setBounds(x, y, width, height);
		this.panel.add(label);
		return label;
	}
	
	public JTextField createTextField(String message, int lines, int x, int y, int width, int height, ActionListener al) {
		JTextField textField = new JTextField(message, lines);
		textField.setBounds(x, y, width, height);
		textField.addActionListener(al);
		this.panel.add(textField);
		return textField;
	}
	
	public JTextField createTextField(int x, int y, int width, int height, ActionListener al) {
		JTextField textField = new JTextField();
		textField.setBounds(x, y, width, height);
		textField.addActionListener(al);
		this.panel.add(textField);
		return textField;
	}
	
	public JTextField createTextField(int x, int y, int width, int height) {
		JTextField textField = new JTextField();
		textField.setBounds(x, y, width, height);
		this.panel.add(textField);
		return textField;
	}
	
	public JScrollPane createScrollPane(JList<String> list, int x, int y, int width, int height) {
		JScrollPane scrollPane = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(x, y, width, height);
		this.panel.add(scrollPane);
		return scrollPane;
	}
	
	public JButton createButton(String buttonLabel, int x, int y, int width, int height, ActionListener al) {
		JButton button = new JButton(buttonLabel);
		button.setBounds(x, y, width, height);
		button.addActionListener(al);
		this.panel.add(button);
		return button;
	}
}
