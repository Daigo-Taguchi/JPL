package practice.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import practice.model.FieldSearcher;

public class Panel extends JPanel{
	private JLabel label;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JTextField textField;
	private JTextField textField2;
	private JList<String> list;
	private String[] constructorList;
	private FieldSearcher fi = new FieldSearcher();

	Panel() {
		setLayout(null);
		setBackground(Color.DARK_GRAY);
		setPreferredSize(new Dimension(600, 600)); // JPanelのサイズ指定

		this.label = new JLabel();
		this.label.setForeground(Color.WHITE);
		this.label.setText("コンストラクタを検索したいObject名を入力してください");
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
		this.textField2.addActionListener(new TextFieldController());
		add(this.textField2);

		this.label4 = new JLabel();
		this.label4.setForeground(Color.RED);
		this.label4.setBounds(0, 480, 600, 30);
		add(this.label4);
	}

	private class TextFieldController implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == textField) {
				constructorList = getConstructors(textField.getText());
				list.setListData(constructorList);
			}

			if(e.getSource() == textField2) {
				boolean result;
				String parameter = textField2.getText();
				String[] parameters = parameter.split("," , 0);
//				for (int i =0; i < parameters.length; i ++) {
//					System.out.println(parameters[i]);
//				}
				List<Object> resultParameters = new ArrayList<Object>();
				// パラメータとして入力された値に""が含まれているかを判別して、含まれていれば文字列、そうでなければintとする
				// ListじゃなくてOnject型の配列で持つ必要がある？
				// 配列で持つと、宣言時に確保した長さに入れないとnullが入っちゃう

				for(String s: parameters) {
					s = s.trim();// 先頭と末尾の空白を削除する
					if (s.startsWith("\"") && s.endsWith("\"")) {
						resultParameters.add(s.substring(1,s.length() -1));
					} else if(Pattern.matches("[-\\+]?[0-9]+",s)) {
						resultParameters.add(Integer.parseInt(s));
					}
				}

				result =  fi.toInstance(list.getSelectedIndex(), resultParameters.toArray());
				if (result) {
					label4.setText("インスタンス生成成功");
				} else {
					label4.setText("パラメータが不正です");
				}
			}
		}
	}

	/***
	 * 指定したクラス名のコンストラクタ一覧のString配列を取得する
	 * @param searchClassName
	 * @return results
	 */
	private String[] getConstructors(String searchClassName) {
		this.fi = new FieldSearcher();
		Constructor<?>[] constructors= fi.searchConstructors(searchClassName);
		String[] results = new String[100];
		for(int i = 0; i < constructors.length; i++) {
			results[i] = "#" + i + " : " + constructors[i].toGenericString();
		}
		return results;
	}

//	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		g.drawString("TEST", 100, 100);
//		System.out.println("Check");
//	}
}
