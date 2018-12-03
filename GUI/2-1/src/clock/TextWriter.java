package clock;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class TextWriter extends JPanel{
	
	private int clockFontSize = 20;
	private Color fontColor = Color.RED;
	private Color backgroundColor = Color.BLACK;
	private String font = "Monospaced";
	private int clockPositionX = 10;
	private int clockPositionY = 100;
	private static final int REPAINT_SPAN = 300;
	
	TextWriter(){
		setOpaque(true);//自分の背景色を透過させることで、Frameの背景色を表面に出す
	}
	
	/**
	 * 描画を行うメソッド
	 * Swingの場合はダブルバッファリングを自動で行う
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		TimeSetting cs = new TimeSetting();
		Font font = new Font(this.font, Font.BOLD, this.clockFontSize);
		g.setFont(font);
		g.setColor(this.fontColor);
		g.drawString(cs.getTimeSetting(), this.clockPositionX, this.clockPositionY);
		this.setBackground(this.backgroundColor);
		System.out.println("Check");
	}
	
	/**
	 *再描画のタイマー処理を行うメソッド
	 *REPAINT_SPANで設定した時間間隔で再描画が行われる
	 *repaint()メソッドが呼ばれることで下のpaintメソッドが再度呼ばれる
	 * */
	public void doRepaint() {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {//再描画の時間を設定する。第二引数で実行間隔の調整
			public void run() {
				repaint();
			}
		},0, REPAINT_SPAN);
	}
	
	/**
	 * 時計のフォントサイズを変更するSetter
	 * @param fontSize
	 */
	public void setClockFontSize(int fontSize) {
		this.clockFontSize = fontSize;
	}
	
	/**
	 * 時計のフォントカラーを変更するSetter
	 * @param fontColor
	 */
	public void setClockFontColor(Color fontColor) {
		this.fontColor = fontColor;
	}
	
	/**
	 * 時計のフォントの種類を変更するSetter
	 * @param font
	 */
	public void setFont(String font) {
		this.font = font;
	}
	
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	
}
