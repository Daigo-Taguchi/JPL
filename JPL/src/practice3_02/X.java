package practice3_02;

public class X {
	protected int xMask = 0x00ff;
	protected int fullMask;
	
	public X() {
		fullMask = xMask;
		//System.out.printf("%x%n",fullMask);
	}
	
	public int mask(int orig) {
		return (orig & fullMask);
	}

}
