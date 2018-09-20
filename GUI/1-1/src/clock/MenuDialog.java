package clock;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MenuDialog extends Dialog implements ActionListener{
	private static final int WINDOW_SIZE_X = 600;
	private static final int WINDOW_SIZE_Y = 300;

	private Button decideButton;
	private List fontList;
	private List fontSizeList;
	private List fontColorList;
	private List backgroundColorList;

	MenuDialog(Frame parent){
		super(parent , true);
		setSize(WINDOW_SIZE_X , WINDOW_SIZE_Y); //Windowのサイズ(x,y)
		setLayout(new FlowLayout());//objectのレイアウトの指定。左下から右下に流し込むようにレイアウトする。
		setTitle("プロパティ");
		setBackground(Color.BLACK);
		addWindowListener(new DialogWindowAdapter());

		/*Listの実装*/
		//フォントの種類のリスト
		fontList = new List();
		fontList.add("Serif");
		fontList.add("SansSerief");
		fontList.add("Monospaced");
		add(fontList);
		fontList.addActionListener(this);

		//フォントサイズのリスト
		fontSizeList = new List();
		fontSizeList.add("10");
		fontSizeList.add("11");
		fontSizeList.add("13");
		fontSizeList.add("15");
		fontSizeList.add("20");
		fontSizeList.add("50");
		fontSizeList.add("100");
		add(fontSizeList);
		fontSizeList.addActionListener(this);

		//フォントカラーのリスト
		fontColorList = new List();
		fontColorList.add("RED");
		fontColorList.add("BLUE");
		fontColorList.add("PINK");
		add(fontColorList);
		fontColorList.addActionListener(this);
		
		//背景色のリスト
		backgroundColorList = new List();
		backgroundColorList.add("BLACK");
		backgroundColorList.add("WHITE");
		backgroundColorList.add("ORANGE");
		add(backgroundColorList);
		backgroundColorList.addActionListener(this);

		/*決定ボタンの実装*/
		decideButton = new Button("OK");
		add(decideButton);
		decideButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		ClockFrame clockFrame = new ClockFrame();

		//OKボタンが押された時の処理
		if(e.getSource() == decideButton) {
			dispose();
		}

		//フォントが選択された時の処理
		if(fontList.getSelectedItem() == null) {
			clockFrame.setFont("SansSerief");
		}
		else {
			clockFrame.setFont(fontList.getSelectedItem());			
		}

		//フォントサイズが選択されたときの処理
		if(fontSizeList.getSelectedItem() == null) {
			clockFrame.setFontSize("50");
		}
		else {
			clockFrame.setFontSize(fontSizeList.getSelectedItem());			
		}

		//フォントカラーが設定された時の処理
		if(fontColorList.getSelectedItem() == null) {
			clockFrame.setColor("RED");
		}
		else {
			clockFrame.setColor(fontColorList.getSelectedItem());			
		}
		
		//背景色が設定された時の処理
		if(backgroundColorList.getSelectedItem() == null) {
			clockFrame.setBackgroundColor("BLACK");
		}
		else {
			clockFrame.setBackgroundColor(backgroundColorList.getSelectedItem());
		}
	}

	//抽象クラスMyWindowAdapterの実装。Windowの×ボタンで閉じる処理
	class DialogWindowAdapter extends WindowAdapter{
		public void windowClosing(WindowEvent e) {
			dispose();
		}
	}
}