package clock;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FontColorDialog extends  Dialog implements ActionListener{
	private static final int WINDOW_SIZE_X = 300;
	private static final int WINDOW_SIZE_Y = 200;
	private List fontColorList;
	private Button decideButton;

	FontColorDialog(Frame parent){
		super(parent , true);
		setSize(WINDOW_SIZE_X , WINDOW_SIZE_Y); //Windowのサイズ(x,y)
		setLayout(new FlowLayout());//objectのレイアウトの指定。左下から右下に流し込むようにレイアウトする。
		setTitle("フォントカラー設定");
		setBackground(Color.BLACK);
		addWindowListener(new DialogWindowAdapter());

		//フォントカラーのリスト
		fontColorList = new List();
		fontColorList.add("RED");
		fontColorList.add("BLUE");
		fontColorList.add("PINK");
		add(fontColorList);
		fontColorList.addActionListener(this);

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

		//フォントカラーが設定された時の処理
		if(fontColorList.getSelectedItem() == null) {
			dispose();
		}
		else {
			clockFrame.setColor(fontColorList.getSelectedItem());			
		}
	}

	class DialogWindowAdapter extends WindowAdapter{
		public void windowClosing(WindowEvent e) {
			dispose();
		}
	}
}
