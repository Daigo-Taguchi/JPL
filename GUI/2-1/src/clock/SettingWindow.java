package clock;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

public class SettingWindow extends JFrame{
	
	private int WindowWidth = 300;
	private int WindowHeight = 200;
	private Container contentPane;

	SettingWindow(){
		super("Clock");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//×ボタンで閉じる処理
		this.setSize(this.WindowWidth, this.WindowHeight);//windowサイズの設定
		this.contentPane = getContentPane();
		this.contentPane.setBackground(Color.BLACK);//背景色の設定
		this.setLocationRelativeTo(null);//windowを画面中央に配置
		this.contentPane.add(new TextWriter(),BorderLayout.CENTER);
		
		/*MenuBar*/
		JMenuBar menubar = new JMenuBar();
		
		JMenu fontMenu = new JMenu("Font");
		JMenu backgroundMenu = new JMenu("background");
		
		menubar.add(fontMenu);
		menubar.add(backgroundMenu);
		
		JMenu fontSize = new JMenu("fontSize");
		
		JMenuItem size1 = new JMenuItem("10");
		JMenuItem size2 = new JMenuItem("15");
		JMenuItem size3 = new JMenuItem("20");
		JMenuItem size4 = new JMenuItem("40");
		
		fontSize.add(size1);
		fontSize.add(size2);
		fontSize.add(size3);
		fontSize.add(size4);
		
		JMenu fontColor = new JMenu("FontColor");
		
		JMenuItem color1 = new JMenuItem("RED");
		JMenuItem color2 = new JMenuItem("BLUE");
		JMenuItem color3 = new JMenuItem("PINK");
		JMenuItem color4 = new JMenuItem("GREEN");
		
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
	
}
