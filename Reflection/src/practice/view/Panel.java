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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import practice.model.FieldSearcher;

public class Panel extends JPanel{
	private JLabel label;
	private JLabel label2;
	private JLabel label3;
	private JLabel label5;
	private JLabel label6;
	private JLabel label7;
	private JLabel console;
	private JTextField textField;
	private JTextField textField2;
	private JTextField textField3;
	private JList<String> constructorList;
	private JList<String> instanceList;
	private JTextArea consoleText;
	private String[] constructorDataList;
	private String[] instanceDataList;
	private FieldSearcher fieldSeacher = new FieldSearcher();

	Panel() {
		this.fieldSeacher = new FieldSearcher();

		setLayout(null);
		setBackground(Color.DARK_GRAY);
		setPreferredSize(new Dimension(600, 800)); // JPanelのサイズ指定

		this.label = new JLabel();
		this.label.setForeground(Color.WHITE);
		this.label.setText("コンストラクタを検索したいObject名を入力してください");
		this.label.setBounds(0, 0, 600, 30);
		add(this.label);

		this.textField = new JTextField("java.lang.String" , 1);
		this.textField.setBounds(0, 30, 600, 30);
		this.textField.addActionListener(new TextFieldController());
		add(this.textField);

		this.label2 = new JLabel();
		this.label2.setForeground(Color.WHITE);
		this.label2.setText("## Constructor検索結果 ##");
		this.label2.setBounds(0, 70, 600, 30);
		add(this.label2);

		this.constructorList = new JList<String>();
		JScrollPane scrollPane = new JScrollPane(constructorList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(0, 100, 600, 300);
		this.add(scrollPane);

		this.label3 = new JLabel();
		this.label3.setForeground(Color.WHITE);
		this.label3.setText("コンストラクタ引数入力画面");
		this.label3.setBounds(0, 410, 250, 30);
		add(this.label3);

		this.textField2 = new JTextField(1);
		this.textField2.setBounds(0, 440, 250, 30);
		this.textField2.addActionListener(new TextFieldController());
		add(this.textField2);

		this.label6 = new JLabel();
		this.label6.setForeground(Color.WHITE);
		this.label6.setText("空配列生成");
		this.label6.setBounds(270, 410, 250, 30);
		add(this.label6);

		this.label7 = new JLabel();
		this.label7.setForeground(Color.WHITE);
		this.label7.setText("長さ：");
		this.label7.setBounds(270, 440, 40, 30);
		add(this.label7);

		this.textField3 = new JTextField(1);
		this.textField3.setBounds(310, 440, 290, 30);
		this.textField3.addActionListener(new TextFieldController());
		add(this.textField3);
		
		this.console = new JLabel("実行結果");
		this.console.setForeground(Color.WHITE);
		this.console.setBounds(0, 500, 600, 30);
		add(this.console);
		
		this.consoleText  = new JTextArea(10, 50);
		this.consoleText.setEditable(false);
		consoleText.setBounds(0, 530, 600, 200);
		this.add(this.consoleText);

		this.label5 = new JLabel();
		this.label5.setForeground(Color.WHITE);
		this.label5.setText("インスタンス");
		this.label5.setBounds(610, 0, 600, 30);
		add(this.label5);
		
		this.instanceList = new JList<String>();
		JScrollPane instanceListScrollPane = new JScrollPane(instanceList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		instanceListScrollPane.setBounds(610, 30, 250, 90);
		add(instanceListScrollPane);
	}

	private class TextFieldController implements ActionListener{
		List<Object> resultParameters = new ArrayList<Object>();

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == textField) {
				constructorDataList = getConstructors(textField.getText());
				constructorList.setListData(constructorDataList);
			}

			if(e.getSource() == textField2) {
				boolean result;
				String parameter = textField2.getText();
				String[] parameters = parameter.split("," , 0);

				// パラメータとして入力された値に""が含まれているかを判別して、含まれていれば文字列、そうでなければintとする
				for(String s: parameters) {
					s = s.trim();// 先頭と末尾の空白を削除する
					if (s.startsWith("\"") && s.endsWith("\"")) {
						resultParameters.add(s.substring(1,s.length() -1));
					} else if(Pattern.matches("[-\\+]?[0-9]+",s)) {
						resultParameters.add(Integer.parseInt(s));
					}
				}


				result =  fieldSeacher.toInstance(constructorList.getSelectedIndex(), resultParameters.toArray());
				if (result) {
					consoleText.setText("インスタンス生成成功\r\n\r\n");
					resultParameters.clear(); // 1つのインスタンス化が終わると、入力された引数の初期化をする
				} else {
					consoleText.setText("パラメータが不正です\r\n\r\n");
					resultParameters.clear();
				}

				// インスタンスのListをTextListに反映
				instanceDataList = getInstances();
				instanceList.setListData(instanceDataList);
			}

			if(e.getSource() == textField3) {
				String parameter = textField3.getText();

				// 数字の入力以外は不正なパラメータとして処理
				if(Pattern.matches("[0-9]+",parameter)) {
					fieldSeacher.toInstanceWithArray(textField.getText(), Integer.parseInt(textField3.getText()));
					// ここで、modelが保持しているインスタンスのリストが更新されたから、Observerが検知して、Instance表示画面を更新するべき
					instanceDataList = getInstances();
					instanceList.setListData(instanceDataList);
					consoleText.setText("インスタンス生成成功\r\n\r\n");
					consoleText.append("長さ：" + textField3.getText() + "\r\n");
				} else {
					consoleText.setText("パラメータが不正です\r\n\r\n");
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
		Constructor<?>[] constructors= fieldSeacher.searchConstructors(searchClassName);
		String[] results = new String[100];
		for(int i = 0; i < constructors.length; i++) {
			results[i] = "#" + i + " : " + constructors[i].toGenericString();
		}
		return results;
	}

	/**
	 * modelからインスタンス一覧を取得し、インスタンス名をString配列で取得する
	 * @return results
	 */
	private String[] getInstances() {
		String[] results = new String[100];
		List<Object> instances =  this.fieldSeacher.getInstanceList();
		for(int i = 0 ; i < instances.size(); i ++) {
			results[i] = "#" + i + " : " + instances.get(i).getClass().getSimpleName();
		}
		return results;
	}
}
