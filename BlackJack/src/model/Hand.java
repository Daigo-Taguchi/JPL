package model;

import java.util.ArrayList;
import java.util.List;

public class Hand {
	protected List<Card> cards = new ArrayList<Card>();
	private Deck deck;

	Hand(Deck deck){
		this.deck = deck;
	}

	/***
	 * 手札に引数で指定したカードを一枚加える
	 * @param card
	 */
	public void setCard(Card card) {
		this.cards.add(card);
	}

	/***
	 * 手札を表すリストを返す
	 * @return
	 */
	public List<Card> getCards(){
		return this.cards;
	}

	public void printHand() {
		for(int i = 0; i < this.cards.size(); i ++) {
			this.cards.get(i).printCard();
		}
	}

	public void printDealerHand() {
		this.cards.get(0).printCard();
		System.out.println("??");
	}
}
