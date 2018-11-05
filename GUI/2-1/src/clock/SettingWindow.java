package clock;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingWindow extends JFrame{
	
	private int WindowWidth = 500;
	private int WindowHeight = 200;
	private Container contentPane;
	TextWriter tw;
	
	private JMenuItem size1;
	private JMenuItem size2;
	private JMenuItem size3;
	private JMenuItem size4;
	
	private JMenuItem color1;
	private JMenuItem color2;
	private JMenuItem color3;
	private JMenuItem color4;

	SettingWindow(TextWriter tw){
		/*window初期設定*/
		super("Clock");
		this.tw = tw;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//×ボタンで閉じる処理
		this.setSize(this.WindowWidth, this.WindowHeight);//windowサイズの設定
		this.contentPane = getContentPane();
		//this.contentPane.setBackground(Color.BLACK);//背景色の設定
		this.setLocationRelativeTo(null);//windowを画面中央に配置
		this.contentPane.add(tw,BorderLayout.CENTER);
		
		/*MenuBar*/
		JMenuBar menubar = new JMenuBar();
		
		JMenu fontMenu = new JMenu("Font");
		JMenu backgroundMenu = new JMenu("background");
		
		menubar.add(fontMenu);
		menubar.add(backgroundMenu);
		
		JMenu fontSize = new JMenu("fontSize");
		
		this.size1 = new JMenuItem("30");
		this.size2 = new JMenuItem("50");
		this.size3 = new JMenuItem("70");
		this.size4 = new JMenuItem("100");
		
		size1.addActionListener(new FontSizeContoroler());
		size2.addActionListener(new FontSizeContoroler());
		size3.addActionListener(new FontSizeContoroler());
		size4.addActionListener(new FontSizeContoroler());
		
		fontSize.add(size1);
		fontSize.add(size2);
		fontSize.add(size3);
		fontSize.add(size4);
		
		JMenu fontColor = new JMenu("FontColor");
		
		this.color1 = new JMenuItem("RED");
		this.color2 = new JMenuItem("BLUE");
		this.color3 = new JMenuItem("PINK");
		this.color4 = new JMenuItem("GREEN");
		
		color1.addActionListener(new FontColorContoroler());
		color2.addActionListener(new FontColorContoroler());
		color3.addActionListener(new FontColorContoroler());
		color4.addActionListener(new FontColorContoroler());
		
		fontColor.add(color1);
		fontColor.add(color2);
		fontColor.add(color3);
		fontColor.add(color4);
		
		fontMenu.add(fontSize);
		fontMenu.add(fontColor);
		
		JMenu backgroundColor = new JMenu("backgroundColor");		
		
		JMenuItem bcolor1 = new JMenuItem("BLACK");
		JMenuItem bcolor2 = new JMenuItem("GRAY");
		JMenuItem bcolor3 = new JMenuItem("WHITE");
		
		backgroundColor.add(bcolor1);
		backgroundColor.add(bcolor2);
		backgroundColor.add(bcolor3);
		
		backgroundMenu.add(backgroundColor);
		
		this.setJMenuBar(menubar);
		
		this.setVisible(true);//windowを表示
	}
	
	/**
	 * フォントサイズメニューをクリック時のイベント処理を行うクラス
	 * 
	 *
	 */
	class FontSizeContoroler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {

			if(e.getSource() == size1) {
				tw.setClockFontSize(30);
			}
			else if(e.getSource() == size2) {
				tw.setClockFontSize(50);
			}
			else if(e.getSource() == size3) {
				tw.setClockFontSize(70);
			}
			else if(e.getSource() == size4) {
				tw.setClockFontSize(100);
			}
		}
	}
	
	class FontColorContoroler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {

			if(e.getSource() == color1) {
				tw.setClockFontColor(Color.RED);
			}
			else if(e.getSource() == color2) {
				tw.setClockFontColor(Color.BLUE);
			}
			else if(e.getSource() == color3) {
				tw.setClockFontColor(Color.PINK);
			}
			else if(e.getSource() == color4) {
				tw.setClockFontColor(Color.GREEN);
			}
		}
	}
	
}
