package clock;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class ClockFrame extends Frame{
	private static final int WINDOW_SIZE_X = 550;
	private static final int WINDOW_SIZE_Y = 300;
	private static final int REPAINT_SPAN = 300;
	private static final int CLOCK_POSITION_X = 100;
	private static final int CLOCK_POSITION_Y = 200;
	private static final int CLOCK_FONT_INITSIZE = 50;

	private Button button1;
	private Menu menuFile;
	private MenuItem fontMenu;
	private MenuItem fontSizeMenu;
	private MenuItem fontColorMenu;
	private MenuItem backgroundMenu;
	private static String textFont;
	private static Color fontColor = Color.RED;
	private static int fontSize = CLOCK_FONT_INITSIZE;
	private static Color backgroundColor = Color.BLACK;
	private int fontWidth;

	ClockFrame() {
		setSize(WINDOW_SIZE_X , WINDOW_SIZE_Y); //Windowのサイズ(x,y)
		//setSize(fontWidth , WINDOW_SIZE_Y);
		setTitle("TaguchiClock");
		setBackground(backgroundColor);
		setLayout(new FlowLayout());//objectのレイアウトの指定。左下から右下に流し込むようにレイアウトする。
		setResizable(false);//Windowのリサイズ設定
		addWindowListener(new MyWindowAdapter());

		/*メニューバーの実装*/
		//設定メニューの表示
		MenuBar menuBar = new MenuBar();
		setMenuBar(menuBar);
		this.menuFile = new Menu("Menu");
		menuBar.add(menuFile);

		//FontSetting
		this.fontMenu = new MenuItem("FontSetting");
		menuFile.add(fontMenu);
		fontMenu.addActionListener(new FontMenuContoroler());

		//FontSizeSetting
		this.fontSizeMenu = new MenuItem("FontSizeSetting");
		menuFile.add(fontSizeMenu);
		fontSizeMenu.addActionListener(new FontSizeContoroler());

		//FontColorSetting
		this.fontColorMenu = new MenuItem("FontColorSetting");
		menuFile.add(fontColorMenu);
		fontColorMenu.addActionListener(new FontColorContoroler());

		//BackgroundSetting
		this.backgroundMenu = new MenuItem("BackgroundColorSetting");
		menuFile.add(backgroundMenu);
		backgroundMenu.addActionListener(new BackgroundColorContoroler());

		//		/*TextFieldの実装*/
		//		TextField t1 = new TextField("Textを入力してください", 30);
		//		add(t1);
		//

		/*ボタンの実装*/
		this.button1 = new Button("閉じる");
		button1.addActionListener(new ButtonContoroler());
		add(button1);			
	}

	/**
	 *再描画のタイマー処理を行うメソッド
	 *REPAINT_SPANで設定した時間間隔で再描画が行われる
	 *repaint()メソッドが呼ばれることで下のpaintメソッドが再度呼ばれる
	 * */
	public void timeRepaint() {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {//再描画の時間を設定する。第二引数で実行間隔の調整
			public void run() {
				repaint();
			}
		},0, REPAINT_SPAN);
	}

	/**
	 * Frame内の描画を行うメソッド
	 * */
	public void paint(Graphics g) {
		Dimension size = getSize();
		Image back = createImage(size.width , size.height);//ダブルバッファリングに使用する画面イメージを描画処理用のイメージ領域に作成

		Graphics buffer = back.getGraphics();

		DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm:ss");
		var date = LocalTime.now().format(f);
		String dateString = date.toString();
		Font font = new Font(textFont, Font.BOLD, fontSize);
		setBackground(backgroundColor);

		//		FontMetrics fontMetrics = g.getFontMetrics(font);
		//fontWidth = fontMetrics.stringWidth(dateString);
		//setSize(fontWidth,300);

		buffer.setFont(font);
		buffer.setColor(fontColor);
		buffer.drawString(dateString, CLOCK_POSITION_X , CLOCK_POSITION_Y);
		//drawStringCenter(g, buffer , dateString ,CLOCK_POSITION_X , CLOCK_POSITION_Y);
		g.drawImage(back, 0 ,0, this);//作成した画面イメージを反映させる
	}


	public static void drawStringCenter(Graphics g, Graphics buffer, String text, int x, int y) {
		FontMetrics fm = g.getFontMetrics();
		Rectangle rectText = fm.getStringBounds(text, g).getBounds();
		x = x - rectText.width / 2;
		y = y -rectText.height / 2 + fm.getMaxAscent();
		buffer.drawString(text, x, y);
	}

	/**
	 * Fontの種類をセットするメソッド
	 * @param font
	 */
	public void setFont(String font) {
		textFont = font;
		//System.out.println(this.textFont);
	}

	/**
	 * FontSizeをセットするメソッド
	 * @param size
	 */
	public void setFontSize(String size) {
		fontSize = Integer.parseInt(size);
	}

	/**
	 * FontColorをセットするメソッド
	 * @param color
	 */
	public void setColor(String color) {
		fontColor = stringToColor(color);
	}

	/**
	 * BackgroundColorをセットするメソッド
	 * @param String color
	 */
	public void setBackgroundColor(String color) {
		backgroundColor = stringToColor(color);
		System.out.println(backgroundColor);
	}

	/**
	 * String型の色を表す値をColor型に変換するメソッド
	 * @param String型 color
	 * @return Color型変数
	 */
	private Color stringToColor(String color) {
		if(color == "RED") {
			return Color.RED;
		}

		if(color == "BLUE") {
			return Color.BLUE;
		}

		if(color == "PINK") {
			return Color.PINK;
		}

		if(color == "BLACK") {
			return Color.BLACK;
		}

		if(color == "WHITE") {
			return Color.WHITE;
		}

		if(color == "ORANGE") {
			return Color.ORANGE;
		}
		return Color.RED;
	}

	/**button1が押された時の処理を行うクラス**/
	class ButtonContoroler implements ActionListener{	
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == button1) {
				System.exit(0);	
			}
		} 
	}
	/**Menuの"FontSetting"が押された時の処理を行うクラス**/
	class FontMenuContoroler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == fontMenu) {
				FontDialog fontDialog = new FontDialog(ClockFrame.this);
				fontDialog.setVisible(true);	
			}
		}
	}
	/**Menuの"FontColorSetting"が押された時の処理を行うクラス**/
	class FontSizeContoroler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == fontSizeMenu) {
				FontSizeDialog fontSizeDialog = new FontSizeDialog(ClockFrame.this);
				fontSizeDialog.setVisible(true);
			}
		}
	}
	/**Menuの"FontSizeSetting"が押された時の処理を行うクラス**/
	class FontColorContoroler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == fontColorMenu) {
				FontColorDialog fontColorDialog = new FontColorDialog(ClockFrame.this);
				fontColorDialog.setVisible(true);
			}
		}
	}
	/**Menuの"BackgroundColorSetting"が押された時の処理を行うクラス**/
	class BackgroundColorContoroler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == backgroundMenu) {
				BackgroundDialog backgroundDialog = new BackgroundDialog(ClockFrame.this);
				backgroundDialog.setVisible(true);
			}
		}
	}
}

//抽象クラスMyWindowAdapterの実装。Windowの×ボタンで閉じる処理
class MyWindowAdapter extends WindowAdapter{
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
}