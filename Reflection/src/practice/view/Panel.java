package practice.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import practice.model.FieldSearcher;

public class Panel extends JPanel{
	private JLabel label;
	private JLabel label2;
	private JTextField textField;
	private JList<String> list;
	private FieldSearcher fs;
	private String[] fieldList;

	Panel() {
		setLayout(null);
		setBackground(Color.DARK_GRAY);
		setPreferredSize(new Dimension(500, 250)); // JPanelのサイズ指定

		this.fs = new FieldSearcher();

		this.label = new JLabel();
		this.label.setForeground(Color.WHITE);
		this.label.setText("Fieldを検索したいObjectを入力してください");
		this.label.setBounds(0, 0, 500, 30);
		add(this.label);

		this.textField = new JTextField(1);
		this.textField.setBounds(0, 30, 500, 30);

		this.textField.addActionListener(new TextFieldController());
		add(this.textField);

		this.label2 = new JLabel();
		this.label2.setForeground(Color.WHITE);
		this.label2.setText("## Field検索結果 ##");
		this.label2.setBounds(0, 70, 500, 30);
		add(this.label2);

//		this.fieldList = fs.searchClassFiled(this.textField.getText());
		this.list = new JList<String>();
		this.list.setBounds(0, 100, 500, 150);
		add(this.list);
	}

	private class TextFieldController implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == textField) {
				fieldList = fs.searchClassFiled(textField.getText());
				System.out.println("##Check1");
				fieldList = fs.searchClassFiled(textField.getText());
				list.setListData(fieldList);
			}
		}
	}

//	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		g.drawString("TEST", 100, 100);
//		System.out.println("Check");
//	}
}
