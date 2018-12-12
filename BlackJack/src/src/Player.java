package src;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private List<Card> hand = new ArrayList<Card>();
	
	Player(int cardNum, Deck deck){
		for(int i = 0; i < cardNum; i++) {
			deck.drawCard();
		}
	}
	
	/***
	 * 手札に引数で指定したカードを一枚加える
	 * @param card
	 */
	public void setHand(Card card) {
		hand.add(card);
	}
	
	/***
	 * 手札を表すリストを返す
	 * @return
	 */
	public List<Card> getHand() {
		return this.hand;
	}
	
	public void printHand() {
		System.out.println("==PlayerCardNumber==");
		for(int i = 0; i < this.hand.size(); i ++) {
			this.hand.get(i).printCardNum();
		}
	}
}