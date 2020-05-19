package practice.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextArea;

import practice.model.ConstructorModel;

public class InstanceInfoPanel extends JPanelOperator{
	private final int PANEL_WIDTH = 600;
	private final int PANEL_HEIGHT = 600;

	private final ConstructorModel constructorModel;

	private Object selectedInstance;

	private final JTextArea selectedInstanceArea;
	private final JButton fieldPickupButton;
	private final JButton fieldChangeButton;
	private final JList<String> fieldInfoList = new JList<String>();

	public InstanceInfoPanel(final ConstructorModel constructorModel) {
		super();
		setLayout(null);
		setBackground(Color.DARK_GRAY);
		setSize(PANEL_WIDTH, PANEL_HEIGHT);

		this.constructorModel = constructorModel;

		createLabel("インスタンス情報", 0, 0, 600, 30);
		createLabel("対象インスタンス：", 0, 30, 120, 30);
		this.selectedInstanceArea = createTextArea(125, 30, 200, 30, 1, 30, new Color(173, 216, 230));
		this.fieldPickupButton = createButton("取得", 330, 30, 100, 30, new ButtonController());

		createLabel("フィールド一覧", 0, 60, 120, 30);
		createScrollPane(this.fieldInfoList, 0, 90, 570, 150);

		createLabel("値:", 0, 250, 50, 30);
		createTextArea(40, 250, 300, 30, 1, 100, Color.WHITE);
		this.fieldChangeButton = createButton("変更", 350, 250, 100, 30, new ButtonController());
	}

	/**
	 * インスタンス情報に選択済みのインスタンス情報をsetする
	 * @param selectedInstanceName
	 */
	public void setInstanceInfo (final int selectedIndex, final String selectedInstanceName) {
		this.selectedInstanceArea.setText(selectedInstanceName);
		this.selectedInstance = this.constructorModel.getInstanceList().get(selectedIndex);
	}

	private class ButtonController implements ActionListener {

		@Override
		public void actionPerformed(final ActionEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			if(e.getSource() == fieldPickupButton) {
				// ここで取得したインスタンスが持つフィールド一覧を取得する
				try {
					fieldInfoList.setListData(constructorModel.getFields(selectedInstance));
				} catch (final IllegalAccessException e1) {
					// TODO 自動生成された catch ブロック
					e1.printStackTrace();
				}
			}

			if(e.getSource() == fieldChangeButton) {
				// 変更ボタンを押すとFieldを書き換えられるようにする
			}
		}
	}
}
