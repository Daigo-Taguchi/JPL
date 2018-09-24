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

public class ClockFrame extends Frame implements ActionListener{
	private static final int WINDOW_SIZE_X = 550;
	private static final int WINDOW_SIZE_Y = 300;
	private static final int REPAINT_SPAN = 300;
	private static final int CLOCK_POSITION_X = 30;
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
		setTitle("TaguchiClock");
		setBackground(backgroundColor);
		setLayout(new FlowLayout());//objectのレイアウトの指定。左下から右下に流し込むようにレイアウトする。
		setResizable(true);//Windowのリサイズ設定
		addWindowListener(new MyWindowAdapter());
		
		/*メニューバーの実装*/
		//設定メニューの表示
		MenuBar menuBar = new MenuBar();
		setMenuBar(menuBar);
		this.menuFile = new Menu("設定");
		menuFile.addActionListener(this);
		menuBar.add(menuFile);
		
		this.fontMenu = new MenuItem("フォント");
		menuFile.add(fontMenu);
		fontMenu.addActionListener(this);
		
		this.fontSizeMenu = new MenuItem("フォントサイズ");
		menuFile.add(fontSizeMenu);
		fontSizeMenu.addActionListener(this);
		
		this.fontColorMenu = new MenuItem("フォントカラー");
		menuFile.add(fontColorMenu);
		fontColorMenu.addActionListener(this);
		
		this.backgroundMenu = new MenuItem("背景色");
		menuFile.add(backgroundMenu);
		backgroundMenu.addActionListener(this);
			
//		/*TextFieldの実装*/
//		TextField t1 = new TextField("Textを入力してください", 30);
//		add(t1);
//		
//		/*ボタンの実装*/
//		this.button1 = new Button("閉じる");
//		button1.addActionListener(this);
//		add(button1);
//		
//		
	}
	
	//Window内でボタンクリック時の処理を書く
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button1) {
			System.exit(0);	
		}
		
		if(e.getSource() == fontMenu) {
			FontDialog fontDialog = new FontDialog(this);
			fontDialog.setVisible(true);
		}
		
		if(e.getSource() == fontSizeMenu) {
			FontSizeDialog fontSizeDialog = new FontSizeDialog(this);
			fontSizeDialog.setVisible(true);
		}
		
		if(e.getSource() == fontColorMenu) {
			FontColorDialog fontColorDialog = new FontColorDialog(this);
			fontColorDialog.setVisible(true);
		}
		
		if(e.getSource() == backgroundMenu) {
			BackgroundDialog backgroundDialog = new BackgroundDialog(this);
			backgroundDialog.setVisible(true);
		}
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
//		Dimension size = getSize();
//		Image back = createImage(size.width , size.height);
//		
//		Graphics buffer = back.getGraphics();
//		
		DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm:ss");
		var date = LocalTime.now().format(f);
		String dateString = date.toString();
		Font font = new Font(textFont, Font.BOLD, fontSize);
		
//		FontMetrics fontmetrics = g.getFontMetrics();
//		fontWidth = fontmetrics.stringWidth(dateString);
		
		
		//System.out.println(textFont);
		g.setFont(font);
		g.setColor(fontColor);
//		buffer.setFont(font);
//		buffer.setColor(fontColor);
		g.drawString(dateString, CLOCK_POSITION_X , CLOCK_POSITION_Y);
//		g.drawImage(back, CLOCK_POSITION_X , CLOCK_POSITION_Y , this);
	}
	
	public void setFont(String font) {
		textFont = font;
		//System.out.println(this.textFont);
	}
	
	public void setFontSize(String size) {
		fontSize = Integer.parseInt(size);
	}
	
	public void setColor(String color) {
		fontColor = stringToColor(color);
	}
	
	public void setBackgroundColor(String color) {
		backgroundColor = stringToColor(color);
	}
	
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
}

//抽象クラスMyWindowAdapterの実装。Windowの×ボタンで閉じる処理
class MyWindowAdapter extends WindowAdapter{
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
}