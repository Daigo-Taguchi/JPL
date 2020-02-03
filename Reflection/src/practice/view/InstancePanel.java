package practice.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import practice.model.ConstructorModel;
// import practice.model.InstanceListModel;
import practice.model.Obserbable;
import practice.model.Observer;

@SuppressWarnings("serial")
public class InstancePanel extends JPanel implements Observer{
	private final int PANEL_WIDTH = 600;
	private final int PANEL_HEIGHT = 290;
	
	private JPanelOperator operator;
	private ConstructorModel constructorModel;
	private Obserbable obserbable;
	private InstanceInfoPanel instanceInfoPanel;
	
	private JList<String> instanceList = new JList<String>();
	private JList<String> arrayList = new JList<String>();
	private JButton pickupButton;
	private JTextArea selectedInstance;

	public InstancePanel(ConstructorModel constructorModel, Obserbable generator, InstanceInfoPanel instanceInfoPanel) {
		super();
		setLayout(null);
		setBackground(Color.DARK_GRAY);
		setSize(PANEL_WIDTH, PANEL_HEIGHT);
		
		this.constructorModel = constructorModel;
		this.instanceInfoPanel = instanceInfoPanel;
		this.obserbable = generator;
		this.obserbable.addObserver(this);
		
		this.operator = new JPanelOperator(this);
		
		this.operator.createLabel("インスタンス一覧", 5, 0, 200, 30);
		this.operator.createScrollPane(this.instanceList, 5, 30, 200, 200, new ListController());
		
		this.operator.createLabel("配列要素一覧", 215, 0, 340, 30);
		this.operator.createLabel("対象：", 215, 30, 40, 30);
		this.selectedInstance =  this.operator.createTextArea(255, 30, 200, 30, 1, 10, new Color(173, 216, 230));
		this.pickupButton =  this.operator.createButton("取得", 460, 30, 95, 30, new ButtonController());
		this.operator.createScrollPane(arrayList, 255, 70, 300, 120);
		this.operator.createLabel("値：", 225, 200, 30, 30);
		this.operator.createTextField(255, 200, 200, 30, new TextFieldContoroller());
		this.operator.createButton("変更", 460, 200, 95, 30, new ButtonController());
	}

	@Override
	public void updateConstructor() {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void updateInstance() {
		// TODO 自動生成されたメソッド・スタブ
		String[] results = new String[100];
		List<Object> instances =  this.constructorModel.getInstanceList();
		for(int i = 0 ; i < instances.size(); i ++) {
			results[i] = "#" + i + " : " + instances.get(i).getClass().getSimpleName();
		}
		this.instanceList.setListData(results);
	}
	
	private class ButtonController implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == pickupButton) {
				List<Object> instances = constructorModel.getInstanceList();
				Object searchObject = instances.get(instanceList.getSelectedIndex());
				List<String> elementDataList = new ArrayList<String>();
				
				// モデルが保持するインスタンスのリストで、選択されているインスタンスが配列かどうかを判定する
				if (searchObject.getClass().isArray()) {
					for (int i = 0; i < Array.getLength(searchObject); i ++) {
						if (Array.get(searchObject, i) == null) {
							elementDataList.add("#" + instanceList.getSelectedIndex() + "#" + i + "：null");
						} else {
							System.out.println(Array.get(searchObject, i).toString());
							elementDataList.add("#" + instanceList.getSelectedIndex() + "#" + i + "：" + Array.get(selectedInstance, i).toString());
						}
					}
					String[] elements = elementDataList.toArray(new String[elementDataList.size()]);
					arrayList.setListData(elements);
				}
			}
		}
	}
	
	private class ListController implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			if (e.getValueIsAdjusting()) {
				Object searchObject = constructorModel.getInstanceList().get(instanceList.getSelectedIndex());
				
				if (searchObject.getClass().isArray()) {
					pickupButton.setEnabled(true);
					selectedInstance.setText(instanceList.getSelectedValue());
				}else {
					pickupButton.setEnabled(false);
					selectedInstance.setText(null);
				}
				
				String selectedInstanceName = searchObject.getClass().getSimpleName();
				// ここからinstanceInfoPanelのインスタンスを生成して、インスタンス情報のテキストボックス内に取得したインスタンス名を渡す
				instanceInfoPanel.setInstanceInfo(instanceList.getSelectedValue());
			}
		}
		
	}
	
	private class TextFieldContoroller implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
		}
	}
}
