package practice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	 public static void main(String args[]){
		 Pattern pattern = Pattern.compile("[-\\+]?[0-9]+");
		 Matcher matcher =  pattern.matcher("-+20");
		 System.out.println(matcher.matches());

		 Window window = new Window("Reflection");
	 }
}
