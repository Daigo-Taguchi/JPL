package clock;

import java.awt.*;

public class ClockFrame extends Frame{
	Timer t = new Timer();
	
	public ClockFrame() {
		setSize(300,200);
		setTitle("Application");
		addWindowListener(new MyWindowAdapter());
	}
	
	public void paint(Graphics g) {
		g.drawString(t.getHour() + ":" + t.getMinute() + ":" + t.getSecond(), 50, 50);
	}
}
