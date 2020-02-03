package practice.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import practice.model.ConstructorModel;
import practice.model.Obserbable;

public class InstanceInfoPanel extends JPanel{
	private final int PANEL_WIDTH = 600;
	private final int PANEL_HEIGHT = 600;
	
	private ConstructorModel constructorModel;
	private JPanelOperator operator;
	
	private JTextArea selectedInstance;
	
	public InstanceInfoPanel(ConstructorModel constructorModel) {
		super();
		setLayout(null);
		setBackground(Color.DARK_GRAY);
		setSize(PANEL_WIDTH, PANEL_HEIGHT);
		
		this.constructorModel = constructorModel;
		this.operator = new JPanelOperator(this);
		
		this.operator.createLabel("インスタンス情報", 0, 0, 600, 30);
		this.operator.createLabel("対象インスタンス：", 0, 30, 120, 30);
		this.selectedInstance = this.operator.createTextArea(125, 30, 200, 30, 1, 30, new Color(173, 216, 230));
		this.operator.createButton("取得", 330, 30, 100, 30, new ButtonController());
	}
	
	/**
	 * インスタンス情報に選択済みのインスタンス情報をsetする
	 * @param selectedInstanceName
	 */
	public void setInstanceInfo (String selectedInstanceName) {
		this.selectedInstance.setText(selectedInstanceName);
	}
	
	private class ButtonController implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			
		}
		
	}
}
