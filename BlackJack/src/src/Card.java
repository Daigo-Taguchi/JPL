package src;

public class Card {
	private int number; // トランプの数字
	private Suit suit; // トランプの柄
	
	Card(Suit suit ,int number){
		this.number = number;
		this.suit = suit;
	}
	public int getNum() {
		return this.number;
	}
	
	public void printCardNum() {
		System.out.print(this.suit + " , ");
		System.out.print(this.number);
		System.out.println();
	}
}