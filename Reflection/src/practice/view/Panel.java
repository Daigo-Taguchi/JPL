package practice.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import practice.controller.Converter;
import practice.model.FieldSearcher;

public class Panel extends JPanel{
	private JLabel label;
	private JLabel label2;
	private JLabel label3;
	private JTextField textField;
	private JTextField textField2;
	private JList<String> list;
	private String[] constructorList;
	private Converter converter;

	Panel() {
		setLayout(null);
		setBackground(Color.DARK_GRAY);
		setPreferredSize(new Dimension(600, 600)); // JPanelのサイズ指定
		
		this.converter = new Converter();

		this.label = new JLabel();
		this.label.setForeground(Color.WHITE);
		this.label.setText("検索したいObjectのクラス名を入力してください");
		this.label.setBounds(0, 0, 600, 30);
		add(this.label);

		this.textField = new JTextField(1);
		this.textField.setBounds(0, 30, 600, 30);

		this.textField.addActionListener(new TextFieldController());
		add(this.textField);

		this.label2 = new JLabel();
		this.label2.setForeground(Color.WHITE);
		this.label2.setText("## Constructor検索結果 ##");
		this.label2.setBounds(0, 70, 600, 30);
		add(this.label2);

		this.list = new JList<String>();
		this.list.setBounds(0, 100, 600, 300);
		add(this.list);
		
		this.label3 = new JLabel();
		this.label3.setForeground(Color.WHITE);
		this.label3.setText("引数入力画面");
		this.label3.setBounds(0, 410, 600, 30);
		add(this.label3);
		
		this.textField2 = new JTextField(1);
		this.textField2.setBounds(0, 440, 600, 30);
		this.textField.addActionListener(new TextFieldController());
		add(this.textField2);
	}

	private class TextFieldController implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == textField) {
				constructorList = converter.getConstructors(textField.getText());
				list.setListData(constructorList);
			}
			
			if(e.getSource() == textField2) {
				// TextFiled2から受けとった内容について書く
			}
		}
	}

//	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		g.drawString("TEST", 100, 100);
//		System.out.println("Check");
//	}
}
