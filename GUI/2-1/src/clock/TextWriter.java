package clock;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class TextWriter extends JPanel{
	
	private int clockFontSize = 20;
	private int clockPositionX = 200;
	private int clockPositionY = 100;
	
	TextWriter(){
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		System.out.println("CheckPoint");
		ClockSetting cs = new ClockSetting();
		Font font = new Font("Serif", Font.BOLD, this.clockFontSize);
		g.setFont(font);
		g.setColor(Color.RED);
		g.drawString(cs.getTimeSetting(), this.clockPositionX, this.clockPositionY);
	}
	
	/**
	 * 時計のフォントサイズを変更するSetter
	 * @param fontSize
	 */
	public void setClockFontSize(int fontSize) {
		this.clockFontSize = fontSize;
	}
	
}
