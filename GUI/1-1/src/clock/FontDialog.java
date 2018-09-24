package clock;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FontDialog extends Dialog implements ActionListener{
	private static final int WINDOW_SIZE_X = 600;
	private static final int WINDOW_SIZE_Y = 300;
	private Button decideButton;
	private List fontList;

	FontDialog(Frame parent){
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
			dispose();
		}
		else {
			clockFrame.setFont(fontList.getSelectedItem());			
		}
	}

	//抽象クラスMyWindowAdapterの実装。Windowの×ボタンで閉じる処理
	class DialogWindowAdapter extends WindowAdapter{
		public void windowClosing(WindowEvent e) {
			dispose();
		}
	}
}