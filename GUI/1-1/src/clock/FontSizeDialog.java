package clock;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FontSizeDialog extends  Dialog implements ActionListener{
	private static final int WINDOW_SIZE_X = 300;
	private static final int WINDOW_SIZE_Y = 200;
	private List fontSizeList;
	private Button decideButton;

	FontSizeDialog(Frame parent){
		super(parent , true);
		setSize(WINDOW_SIZE_X , WINDOW_SIZE_Y); //Windowのサイズ(x,y)
		setLayout(new FlowLayout());//objectのレイアウトの指定。左下から右下に流し込むようにレイアウトする。
		setTitle("フォントサイズ設定");
		setBackground(Color.BLACK);
		addWindowListener(new DialogWindowAdapter());

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
		
		/*決定ボタンの実装*/
		decideButton = new Button("OK");
		add(decideButton);
		decideButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		ClockFrame clockFrame = new ClockFrame();
		
		if(e.getSource() == decideButton) {
			dispose();
		}
		
		//フォントサイズが選択されたときの処理
		if(fontSizeList.getSelectedItem() == null) {
			dispose();
		}
		else {
			clockFrame.setFontSize(fontSizeList.getSelectedItem());			
		}
	}

	class DialogWindowAdapter extends WindowAdapter{
		public void windowClosing(WindowEvent e) {
			dispose();
		}
	}
}
