package clock;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeSetting {
	
	public String getTimeSetting() {
		DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm:ss");
		var date = LocalTime.now().format(f);
		String dateString = date.toString();
		return dateString;
	}

}
