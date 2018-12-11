package src;

public class Card {
	private int number; // トランプの数字
	private Suit suit; // トランプの柄
	
	Card(Suit suit ,int number){
		this.number = number;
		this.suit = suit;
	}
}