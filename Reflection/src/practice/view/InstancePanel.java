package practice.view;

import java.awt.Color;
import java.util.List;
import javax.swing.JList;
import javax.swing.JPanel;
import practice.model.ConstructorModel;
import practice.model.Obserbable;
import practice.model.Observer;

@SuppressWarnings("serial")
public class InstancePanel extends JPanel implements Observer{
	private final int PANEL_WIDTH = 600;
	private final int PANEL_HEIGHT = 290;
	
	private JPanelOperator operator;
	private ConstructorModel constructorModel;
	private Obserbable obserbable;
	
	private JList<String> instanceList = new JList<String>();

	public InstancePanel(ConstructorModel constructorModel, Obserbable generator) {
		super();
		setLayout(null);
		setBackground(Color.DARK_GRAY);
		setSize(PANEL_WIDTH, PANEL_HEIGHT);
		
		this.constructorModel = constructorModel;
		this.obserbable = generator;
		this.obserbable.addObserver(this);
		
		this.operator = new JPanelOperator(this);
		
		this.operator.createLabel("インスタンス一覧", 0, 0, 600, 30);
		this.operator.createScrollPane(this.instanceList, 0, 30, 300, 200);
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
}
