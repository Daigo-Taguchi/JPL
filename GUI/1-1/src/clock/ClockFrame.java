package clock;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class ClockFrame extends Frame implements ActionListener{
	Timer t = new Timer();
	
	ClockFrame() {
		setSize(500,300);
		setTitle("TaguchiClock");
		setBackground(Color.BLACK);
		
		setLayout(new FlowLayout());
		TextField t1 = new TextField("フリースペース", 30);
		add(t1);
		
		Button button1 = new Button("押すなよ...絶対に押すなよ");
		button1.addActionListener(this);
		add(button1);
		
		setResizable(false);
		addWindowListener(new MyWindowAdapter());
	}
	
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
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
		Font font = new Font("Monospaced", Font.BOLD, 100);
		
		g.setFont(font);
		g.setColor(Color.RED);
		g.drawString(dateString, 30 , 200);
	}
}