package practice.view;

import javax.swing.JFrame;

import practice.model.Obserbable;
import practice.model.ConstructorModel;
import practice.model.InstanceListModel;

public class Window {
	private JFrame frame;
	private ConstructorPanel p1;
	private InstancePanel p2;
	private ConstructorModel constructorModel;
	private InstanceListModel instanceListModel;
	private Obserbable generator;
	
	public Window(String title){
		this.instanceListModel = new InstanceListModel();
		this.constructorModel = new ConstructorModel(this.instanceListModel);
		this.generator = this.constructorModel;
		
		this.frame = new JFrame(title);
		this.frame.setSize(1200, 800);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLocationRelativeTo(null);// 画面中央にFrameを表示
		this.frame.setLayout(null);
		
		this.p1 = new ConstructorPanel(this.constructorModel, this.generator);
		this.p1.setBounds(0 ,0, 600, 510);
		this.frame.add(this.p1);

		this.p2 = new InstancePanel(this.constructorModel, this.generator);
		this.p2.setBounds(0, 510, 600, 290);
		this.frame.add(this.p2);
		
		// 最後に書かないと未完成の状態で表示されちゃう
		this.frame.setVisible(true);
	}
}