package practice1_15;

public class SimpleLookup implements Lookup {
	private String[] names;
	private Object[] values;
	
	public Object find(String name) {
		for(int i = 0; i < names.length; i++) {
			if(names[i].equals(name)) {
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