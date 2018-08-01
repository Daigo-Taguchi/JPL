package practice1_15;

public class Main {

	public static void main(String[] args) {
		SimpleLookup sl = new SimpleLookup();
		boolean bool1 =  sl.add("taguchi");
		boolean bool2 = sl.add("taguchi2");
		
		var name =  sl.find("taguchi2");
		System.out.println(name);
		
		boolean bool3 = sl.remove("taguchi2");
		var name2 = sl.find("taguchi2");
		System.out.println(name2);

	}

}
