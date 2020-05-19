package practice.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

import practice.model.ConstructorModel;
import practice.model.Obserbable;
import practice.model.Observer;

public class ConstructorPanel extends JPanelOperator implements Observer{
	private final int PANEL_WIDTH = 600;
	private final int PANEL_HEIGHT = 510;

	private final JTextField textField;
	private final JTextField parameterTextField;
	private final JTextField arrayLengthTextField;
	private final JList<String> constructorList = new JList<String>();
	private final JButton generateButton;
	private final JButton generateArrayInstanceButton;
	private final JLabel searchConstructorErrorMessage;
	private final JLabel parameterErrorMessage;

	private String[] constructorDataList;

	private final ConstructorModel constructorModel;
	private final Obserbable obserbable;

	public ConstructorPanel(final ConstructorModel constructorModel, final Obserbable generator) {
		setLayout(null);
		setBackground(Color.DARK_GRAY);
		setSize(PANEL_WIDTH, PANEL_HEIGHT);

		this.constructorModel = constructorModel;
		this.obserbable = generator;
		this.obserbable.addObserver(this);

		final String searchMessageText = "コンストラクタを検索したいオブジェクト名を入力して、Enterキーを押してください";
		createLabel(searchMessageText, 5, 0, 550, 30);

		this.textField = createTextField("java.lang.String", 1, 5, 30, 550, 30, new TextFieldContoroller());
		this.searchConstructorErrorMessage = createLabel("", 5, 60, 550, 30);

		final String searchResultMessageText = "Constructor検索結果";
		createLabel(searchResultMessageText, 5, 90, 550, 30);
		createScrollPane(this.constructorList, 5, 120, 550, 300);

		final String parameterMessage = "引数";
		createLabel(parameterMessage, 5, 420, 250, 30);
		this.parameterTextField =  createTextField(5, 450, 150, 30, new TextFieldContoroller());
		this.generateButton = createButton("インスタンス生成", 160, 450, 140, 30, new ButtonController());
		this.parameterErrorMessage = createLabel("", 5, 480, 600, 30);

		final String arrayLengthMessage = "配列の長さ";
		createLabel(arrayLengthMessage, 330, 420, 250, 30);
		this.arrayLengthTextField = createTextField(330, 450, 50, 30);
		this.generateArrayInstanceButton = createButton("空配列生成", 385, 450, 170, 30, new ButtonController());

	}

	@Override
	public void updateConstructor() {
		final Constructor<?>[] constructors = constructorModel.getList();
		final String[] results = new String[100];

		for(int i = 0; i < constructors.length; i++) {
			results[i] = constructors[i].toGenericString();
		}
		constructorList.setListData(results);
	}

	@Override
	public void updateInstance() {
		// TODO 自動生成されたメソッド・スタブ
	}

	private class TextFieldContoroller implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent e) {
			if (e.getSource() == textField) {
				searchConstructorErrorMessage.setText("");
				constructorDataList = getConstructors(textField.getText());
				if (constructorDataList == null) {
					searchConstructorErrorMessage.setForeground(Color.RED);
					searchConstructorErrorMessage.setText("Object名が不正です");
				}
			}
		}
	}

	private class ButtonController implements ActionListener {
		List<Object> resultParameters = new ArrayList<Object>();
		@Override
		public void actionPerformed(final ActionEvent e) {
			if (e.getSource() == generateButton) {
				parameterErrorMessage.setText("");
				final String parameter = parameterTextField.getText();
				final String[] parameters = parameter.split(",", 0);

				for (String s: parameters) {
					s = s.trim(); //先頭と末尾の空白を削除する
					if (s.startsWith("\"") && s.endsWith("\"")) {
						resultParameters.add(s.substring(1,s.length() -1));
					} else if(Pattern.matches("[-\\+]?[0-9]+",s)) {
						resultParameters.add(Integer.parseInt(s));
					}
				}
				final boolean result = constructorModel.createInstance(constructorList.getSelectedIndex(), resultParameters.toArray());
				if (result) {
					resultParameters.clear();
				} else {
					parameterErrorMessage.setForeground(Color.RED);
					parameterErrorMessage.setText("パラメータが不正です");
					resultParameters.clear();
				}
			}
			if (e.getSource() == generateArrayInstanceButton) {
				parameterErrorMessage.setText("");
				final String length = arrayLengthTextField.getText();

				if (Pattern.matches("[0-9]+", length)) {
					final boolean result = constructorModel.createArrayInstance(Integer.parseInt(length));
					if (!result) {
						parameterErrorMessage.setText("パラメータが不正です");
					}
				}
			}
		}
	}

	private String[] getConstructors(final String searchClassName) {
		final boolean result =  constructorModel.loadConstructor(searchClassName);
		if (result) {
			final Constructor<?>[] constructors = constructorModel.getList();
			final String[] results = new String[100];
			for(int i = 0; i < constructors.length; i++) {
				results[i] = "#" + i + " : " + constructors[i].toGenericString();
			}
			return results;
		} else {
			return null;
		}
	}
}
