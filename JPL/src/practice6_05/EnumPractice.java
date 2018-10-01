package practice6_05;

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
		RED{
			Color getColor() {
				return Color.RED;
			}
		},
		YELLOW{
			Color getColor() {
				return Color.YELLOW;
			}
		},
		BLUE{
			Color getColor() {
				return Color.BLUE;
			}
		};
		
		abstract Color getColor();
	}
}