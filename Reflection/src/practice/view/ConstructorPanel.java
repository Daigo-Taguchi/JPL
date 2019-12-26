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
import javax.swing.JPanel;
import javax.swing.JTextField;

import practice.model.Obserbable;
import practice.model.ConstructorModel;
import practice.model.Observer;

@SuppressWarnings("serial")
public class ConstructorPanel extends JPanel implements Observer{
	private final int PANEL_WIDTH = 600;
	private final int PANEL_HEIGHT = 510;
	
	private JTextField textField;
	private JTextField parameterTextField;
	private JList<String> constructorList = new JList<String>();
	private JButton generateButton;
	private JLabel searchConstructorErrorMessage;
	private JLabel parameterErrorMessage;
	
	private String[] constructorDataList;
	
	private ConstructorModel constructorModel;
	private JPanelOperator operator = new JPanelOperator(this);
	private Obserbable obserbable;
	
	public ConstructorPanel(ConstructorModel constructorModel, Obserbable generator) {
		setLayout(null);
		setBackground(Color.DARK_GRAY);
		setSize(PANEL_WIDTH, PANEL_HEIGHT);
		
		this.constructorModel = constructorModel;
		this.obserbable = generator;
		this.obserbable.addObserver(this);
		
		String searchMessageText = "コンストラクタを検索したいオブジェクト名を入力して、Enterキーを押してください";
		this.operator.createLabel(searchMessageText, 0, 0, 600, 30);
		
		this.textField = this.operator.createTextField("java.lang.String", 1, 0, 30, 600, 30, new TextFieldContoroller());
		this.searchConstructorErrorMessage = this.operator.createLabel("", 0, 60, 600, 30);
		
		String searchResultMessageText = "## Constructor検索結果 ##";
		operator.createLabel(searchResultMessageText, 0, 90, 600, 30);
		this.operator.createScrollPane(this.constructorList, 0, 120, 600, 300);
		
		String parameterMessage = "引数";
		this.operator.createLabel(parameterMessage, 0, 420, 250, 30);
		this.parameterTextField =  this.operator.createTextField(0, 450, 200, 30, new TextFieldContoroller());
		this.generateButton = this.operator.createButton("インスタンス生成", 210, 450, 150, 30, new ButtonController());
		this.parameterErrorMessage =  this.operator.createLabel("", 0, 480, 600, 30);
		
	}
	
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
	
	private class TextFieldContoroller implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
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
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == generateButton) {
				parameterErrorMessage.setText("");
				String parameter = parameterTextField.getText();
				String[] parameters = parameter.split(",", 0);
				
				for (String s: parameters) {
					s = s.trim(); //先頭と末尾の空白を削除する
					if (s.startsWith("\"") && s.endsWith("\"")) {
						resultParameters.add(s.substring(1,s.length() -1));
					} else if(Pattern.matches("[-\\+]?[0-9]+",s)) {
						resultParameters.add(Integer.parseInt(s));
					}
				}
				boolean result = constructorModel.createObject(constructorList.getSelectedIndex(), resultParameters.toArray());
				if (result) {
					resultParameters.clear();
				} else {
					parameterErrorMessage.setForeground(Color.RED);
					parameterErrorMessage.setText("パラメータが不正です");
					resultParameters.clear();
				}
			}
		}
	}

	@Override
	public void updateConstructor() {
		Constructor<?>[] constructors = constructorModel.getList();
		String[] results = new String[100];
		
		for(int i = 0; i < constructors.length; i++) {
			results[i] = "#" + i + " : " + constructors[i].toGenericString();
		}
		constructorList.setListData(results);					
	}

	@Override
	public void updateInstance() {
		// TODO 自動生成されたメソッド・スタブ
		
		
	}
}
