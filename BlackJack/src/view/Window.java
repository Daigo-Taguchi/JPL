package view;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Window extends JFrame{
	public Window(String title) {
		// windowのタイトル設定
		setTitle(title);
		// windowの表示位置とサイズの指定
		setBounds(100, 100, 300, 250);
		// ×ボタンで閉じる設定
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel =new JPanel();
		
		ImageIcon trump1 = new ImageIcon("img/card_club_01.png");
		ImageIcon trump2 = new ImageIcon("img/card_club_02.png");
		
		JLabel label1 = new JLabel(trump1);
		JLabel label2 = new JLabel(trump2);
		
		panel.add(label1);
		panel.add(label2);
		
		Container contentPane = getContentPane();
		contentPane.add(panel, BorderLayout.CENTER);
	}
}
