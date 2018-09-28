package clock;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BackgroundDialog extends  Dialog{
	private static final int WINDOW_SIZE_X = 300;
	private static final int WINDOW_SIZE_Y = 200;
	private List backgroundColorList;
	private Button decideButton;

	BackgroundDialog(Frame parent){
		super(parent , true);
		setSize(WINDOW_SIZE_X , WINDOW_SIZE_Y); //Windowのサイズ(x,y)
		setLayout(new FlowLayout());//objectのレイアウトの指定。左下から右下に流し込むようにレイアウトする。
		setTitle("背景設定");
		setBackground(Color.BLACK);
		addWindowListener(new DialogWindowAdapter());

		//背景色のリスト
		backgroundColorList = new List();
		backgroundColorList.add("BLACK");
		backgroundColorList.add("WHITE");
		backgroundColorList.add("ORANGE");
		add(backgroundColorList);
		backgroundColorList.addActionListener(new BackgroundDialogContoroler());

		/*決定ボタンの実装*/
		decideButton = new Button("OK");
		add(decideButton);
		decideButton.addActionListener(new BackgroundDialogContoroler());
	}
	
	class BackgroundDialogContoroler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			ClockFrame clockFrame = new ClockFrame();

			if(e.getSource() == decideButton) {
				dispose();
			}

			//背景色が設定された時の処理
			if(backgroundColorList.getSelectedItem() == null) {
				dispose();
			}
			else {
				clockFrame.setBackgroundColor(backgroundColorList.getSelectedItem());
			}
		}
	}

	class DialogWindowAdapter extends WindowAdapter{
		public void windowClosing(WindowEvent e) {
			dispose();
		}
	}
}
