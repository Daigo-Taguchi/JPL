package test;
import java.awt.*;
import java.awt.event.*;

	
class MyWindowAdapter extends WindowAdapter{
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
}

public class FrameTest extends Frame{
	public FrameTest() {
		setSize(300,200);
		setTitle("Application");
		addWindowListener(new MyWindowAdapter());
	}

	public void paint(Graphics g) {
		g.drawString("Test", 30, 120);
	}

	public static void main (String [] args) {
		FrameTest f = new FrameTest();
		f.setVisible(true);
	}	
}
