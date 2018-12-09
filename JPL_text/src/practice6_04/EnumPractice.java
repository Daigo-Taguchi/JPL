package practice6_04;

import java.awt.Color;

public class EnumPractice {
	enum Week {
		MONDAY,
		TUESDAY,
		WEDNESDAY,
		THURSDAY,
		FRIDAY,
		SATURDAY,
		SUNDAY
	}
	
	enum Signal{
		RED(Color.RED) ,
		YELLOW(Color.YELLOW),
		BLUE(Color.BLUE);
		
		private Color color;
		
		Signal(Color color) {
			this.color = color;
		}
		
		public Color getColor(Signal signal) {
			return this.color;
		}
	}
}
