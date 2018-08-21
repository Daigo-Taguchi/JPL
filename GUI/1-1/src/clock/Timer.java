package clock;

import java.util.Calendar;

public class Timer {
	private static int hour;
	private static int minute;
	private static int second;
	
	public int getHour() {
		Calendar cTime = Calendar.getInstance();
		hour = cTime.get(Calendar.HOUR);
		return hour;
	}
	
	public int getMinute() {
		Calendar cTime = Calendar.getInstance();
		minute = cTime.get(Calendar.MINUTE);
		return minute;
	}
	
	public int getSecond() {
		Calendar cTime = Calendar.getInstance();
		second = cTime.get(Calendar.SECOND);
		return second;
	}
}
