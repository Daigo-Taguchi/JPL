package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import src.GUIGameFlow;

public class Window extends JFrame{
	private final int WINDOW_WIDTH = 1000;
	private final int WINDOW_HEIGHT = 1000;
	
	public Window(String title) {
		// windowのタイトル設定
		setTitle(title);
		// windowの表示位置とサイズの指定
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		// ×ボタンで閉じる設定
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Frameを画面中央に表示する
		setLocationRelativeTo(null);
		// フレームのサイズを変更可能かを設定する
		setResizable(true);
		
		CardImage ci = new CardImage();		
		add(ci, BorderLayout.CENTER); // FrameにJpanelを追加する
		
		GUIGameFlow gf = new GUIGameFlow();
		
		BJbutton button = new BJbutton(gf);
		add(button,BorderLayout.SOUTH);
		
		gf.addObserver(ci);// Observerを登録する
		gf.addObserver(button);
		gf.initGame(); // Gameを初期化する
	}
}
