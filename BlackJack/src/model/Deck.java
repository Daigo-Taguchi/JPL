package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	private List<Card> cardList = new ArrayList<Card>(); 
	
	public Deck(){
		for(int i = 1; i <= 13; i++) {
			Card clubCard = new Card(Suit.club , i);
			Card diamondCard = new Card(Suit.diamond , i);
			Card heartCard = new Card(Suit.heart , i);
			Card spadeCard = new Card(Suit.spade , i);	
			
			this.cardList.add(clubCard);
			this.cardList.add(diamondCard);
			this.cardList.add(heartCard);
			this.cardList.add(spadeCard);
		}
	}
	
	/***
	 * 生成したデッキ(cardList)をシャッフルする関数
	 */
	public void shuffle() {
		Collections.shuffle(this.cardList);
	}
	
	/***
	 * デッキ(cardList)の一番上のカードを引く
	 * @return Card型インスタンス
	 */
	public Card drawCard() {
		Card returnCard = this.cardList.get(0);
		this.cardList.remove(0);
		return returnCard;
	}
	
	/***
	 * デッキ(cardList)の末尾にカードを追加する
	 * @param card
	 */
	public void addCard(Card card) {
		this.cardList.add(card);
	}
	
	public void printDeck() {
		System.out.println("START PRINT DECK");
		for(int i = 0; i < this.cardList.size(); i  ++) {
			this.cardList.get(i).printCard();
		}
	}
}
