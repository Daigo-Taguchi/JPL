package practice1_15;

public class SimpleLookup implements Lookup {
	private static final int INDEX_NUMBER = 10;
	private String[] names = new String[INDEX_NUMBER]; //このへんも教科書に載ってない
	private Object[] values = new Object[INDEX_NUMBER];
	
	public Object find(String name) {
		for(int i = 0; i < names.length; i++) {
			if(names[i].equals(name)) {
				values[i] = names[i];//教科書にはこの記述載ってないけど省略されてるだけ？
				return values[i];
			}
		}
		return null;
	}
	
	public boolean add(String name) {
		for(int i = 0; i < names.length; i++) {
			if(names[i] == null) {
				names[i] = name;
				return true;
			}
		}
		return false;
	}
	
	public boolean remove(String name) {
		for(int i = 0; i < names.length; i++) {
			if(names[i].equals(name)) {
				names[i] = null;
				return true;
			}
		}
		return false;
	}
}