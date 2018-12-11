package src;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
	private List<Card> hand = new ArrayList<Card>();
	
	Dealer(int cardNum, Deck deck) {
		for(int i = 0; i < cardNum; i++) {
			deck.drawCard();
		}
	}
	
	public void setHand(Card card) {
		hand.add(card);
	}
}
