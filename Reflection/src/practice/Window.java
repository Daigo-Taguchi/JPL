package practice;

import java.awt.Container;
import javax.swing.JFrame;


public class Window {
	private JFrame frame;
	private Container contentPane;
	private Panel p1;
	
	Window(String title){
		this.frame = new JFrame(title);
		this.frame.setSize(1000, 1000);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLocationRelativeTo(null);// 画面中央にFrameを表示
		this.frame.setLayout(null);
		this.contentPane = this.frame.getContentPane();
		
		this.p1 = new Panel();
		this.p1.setBounds(0 ,0, 500, 500);
		this.contentPane.add(this.p1);
		
		// 最後に書かないと未完成の状態で表示されちゃう
		frame.setVisible(true);
	}
}