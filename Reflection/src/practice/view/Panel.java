package practice.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import practice.model.ConstructorModel;
import practice.model.FieldSearcher;
import practice.model.InstanceListModel;

public class Panel extends JPanel{
	/*頭にDataと命名されているものは、表示用のデータリスト
	 * 頭に何も記載されてない●●Listは、JListの名前*/
	private JLabel label;
	private JLabel label2;
	private JLabel label3;
	private JLabel label5;
	private JLabel label6;
	private JLabel label7;
	private JLabel element;
	private JLabel console;
	private JTextField constructorInputTextField;
	private JTextField constructorParamInputTextField;
	private JTextField textField3;
	private JList<String> constructorList;
	private JList<String> instanceList;
	private JList<String> elementList;
	private JTextArea consoleText;
	private String[] constructorDataList;
	private String[] instanceDataList;
	private List<String> elementDataList;
	private JButton getArrayElementButton;
	private JButton getElementButton;
	private FieldSearcher fieldSeacher = new FieldSearcher();
	
	private InstanceListModel instanceListModel = new InstanceListModel();
	private ConstructorModel constructorModel = new ConstructorModel(instanceListModel);

	Panel() {
		this.fieldSeacher = new FieldSearcher();
		this.elementDataList = new ArrayList<String>();
		setLayout(null);//レイアウトマネージャを無効にする
		setBackground(Color.DARK_GRAY);
		setPreferredSize(new Dimension(600, 800)); // JPanelのサイズ指定

		this.label = new JLabel();
		this.label.setForeground(Color.WHITE);
		this.label.setText("コンストラクタを検索したいオブジェクト名を入力してください");
		this.label.setBounds(0, 0, 600, 30);
		add(this.label);

		// コンストラクター入力エリア
		this.constructorInputTextField = new JTextField("java.lang.String" , 1);
		this.constructorInputTextField.setBounds(0, 30, 600, 30);
		this.constructorInputTextField.addActionListener(new TextFieldController());
		add(this.constructorInputTextField);// JPanel上に表示

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

		this.constructorParamInputTextField = new JTextField(1);
		this.constructorParamInputTextField.setBounds(0, 440, 250, 30);
		this.constructorParamInputTextField.addActionListener(new TextFieldController());
		add(this.constructorParamInputTextField);

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
		this.label5.setBounds(610, 0, 250, 30);
		add(this.label5);

		this.instanceList = new JList<String>();
		JScrollPane instanceListScrollPane = new JScrollPane(instanceList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		instanceListScrollPane.setBounds(610, 30, 250, 90);
		add(instanceListScrollPane);

		this.element = new JLabel();
		this.element.setForeground(Color.WHITE);
		this.element.setText("配列データ");
		this.element.setBounds(880, 0, 250, 30);
		add(this.element);

		this.elementList = new JList<String>();
		JScrollPane elementListScrollPane = new JScrollPane(elementList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		elementListScrollPane.setBounds(880, 30, 250, 90);
		this.add(elementListScrollPane);

		this.getElementButton = new JButton("インスタンスデータ取得");
		this.getElementButton.setBounds(610, 130, 250, 30);
		this.getElementButton.addActionListener(new ButtonController());
		this.add(this.getElementButton);

		this.getArrayElementButton = new JButton("配列データ取得");
		this.getArrayElementButton.setBounds(880, 130, 250, 30);
		this.getArrayElementButton.addActionListener(new ButtonController());
		this.add(this.getArrayElementButton);

	}

	private class TextFieldController implements ActionListener{
		List<Object> resultParameters = new ArrayList<Object>();

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == constructorInputTextField) {
				constructorDataList = getConstructors(constructorInputTextField.getText());
				constructorList.setListData(constructorDataList);
			}

			if(e.getSource() == constructorParamInputTextField) {
				boolean result;
				String parameter = constructorParamInputTextField.getText();
				String[] parameters = parameter.split("," , 0);

				// TODO:ここは別クラスのパーサーとして切り分けたい
				// パラメータとして入力された値に""が含まれているかを判別して、含まれていれば文字列、そうでなければintとする
				for(String s: parameters) {
					s = s.trim();// 先頭と末尾の空白を削除する
					if (s.startsWith("\"") && s.endsWith("\"")) {
						resultParameters.add(s.substring(1,s.length() -1));
					} else if(Pattern.matches("[-\\+]?[0-9]+",s)) {
						resultParameters.add(Integer.parseInt(s));
					}
				}

				// result =  fieldSeacher.toInstance(constructorList.getSelectedIndex(), resultParameters.toArray());
				result = constructorModel.createInstance(constructorList.getSelectedIndex(), resultParameters.toArray());
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
					Object arrayInstance;
					arrayInstance =  fieldSeacher.toArrayInstance(constructorInputTextField.getText(), Integer.parseInt(textField3.getText()));
					// ここで、modelが保持しているインスタンスのリストが更新されたから、Observerが検知して、Instance表示画面を更新するべき
					instanceDataList = getInstances();
					instanceList.setListData(instanceDataList);
					consoleText.setText("インスタンス生成成功\r\n\r\n");
					consoleText.append("長さ：" + textField3.getText() + "\r\n");
					for (int i = 0; i < Integer.parseInt(textField3.getText()); i ++ ) {
						Object arrayElement = Array.get(arrayInstance, i);
						if(arrayElement == null) {
							consoleText.append("[" + i + "] = null" + "\r\n");
						} else {
							consoleText.append("[" + i + "] = " + arrayElement.toString());
						}
					}
				} else {
					consoleText.setText("パラメータが不正です\r\n\r\n");
				}
			}
		}
	}

	private class ButtonController implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent ebutton) {
			// TODO 自動生成されたメソッド・スタブ
			if(ebutton.getSource() == getArrayElementButton) {
				/* Constructor.getInstanceでとれる型が何かわからないので、Object型にしてる。
				 * でも、Objectだと、isArrayとかのメソッドが使えないので、選択されてるインスタンスがArrayかどうかの判定ができない。
				 * 加えて、選択されてるインスタンスがどのクラスのインスタンスかさかのぼって調べる手段がないと、getFiledとかでフィールド情報の取得ができない
				 * そのあたりどうしたらいいのかわからない。教えてもらう*/
				
				Object selectedInstance = fieldSeacher.getInstanceList().get(instanceList.getSelectedIndex());
				for (int i = 0; i < Array.getLength(selectedInstance); i ++) {
					if (Array.get(selectedInstance, i) == null) {
						elementDataList.add("#" + instanceList.getSelectedIndex() + "#" + i + "：null");
					} else {
						elementDataList.add("#" + instanceList.getSelectedIndex() + "#" + i + "：" + Array.get(selectedInstance, i).toString());						
					}
				}
				String[] elements = elementDataList.toArray(new String[elementDataList.size()]);
				for(String s: elements) {
					System.out.println(s);
				}
				elementList.setListData(elements);
			}

			if(ebutton.getSource() == getElementButton) {
				
			}
		}
	}

	/***
	 * 指定したクラス名のコンストラクタ一覧をのString配列として取得する
	 * @param searchClassName
	 * @return results
	 */
//	private String[] getConstructors(String searchClassName) {
//		Constructor<?>[] constructors= fieldSeacher.searchConstructors(searchClassName);
//		String[] results = new String[100];
//		for(int i = 0; i < constructors.length; i++) {
//			results[i] = "#" + i + " : " + constructors[i].toGenericString();
//		}
//		return results;
//	}
	
	private String[] getConstructors(String searchClassName) {
		Class<?> clazz;
		try {
			clazz = Class.forName(searchClassName);
			constructorModel.loadConstructor(clazz);
			Constructor<?>[] constructors = constructorModel.getList();
			String[] results = new String[100];
			for(int i = 0; i < constructors.length; i++) {
				results[i] = "#" + i + " : " + constructors[i].toGenericString();
			}
			return results;
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * modelが保持しているインスタンス一覧を取得し、インスタンス名をString配列で取得する
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
