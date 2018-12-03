package clock;

public class Main  {
	
	public static void main(String[] args) {
		TextWriter textWriter = new TextWriter();
		SettingWindow sw = new SettingWindow(textWriter);
		textWriter.doRepaint();
	}

}
