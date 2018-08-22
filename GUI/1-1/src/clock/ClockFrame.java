package clock;

import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class ClockFrame extends Frame{
	Timer t = new Timer();
	
	public ClockFrame() {
		setSize(300,200);
		setTitle("Application");
		addWindowListener(new MyWindowAdapter());
	}
	
	public void RunTimer() {
		t.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				repaint();
			}
		},0, 100);
	}
	
	public void paint(Graphics g) {
		DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm:ss");
		var date = LocalTime.now().format(f);
		
		String dateString = date.toString();
		g.drawString(dateString, 30 , 50);
	}
}