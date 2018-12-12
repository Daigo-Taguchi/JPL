package src;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private List<Card> hand = new ArrayList<Card>();
	private int handSum;
	
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
	
	public void setHundSum(int handSum) {
		this.handSum = handSum;
	}
	
	/***
	 * プレイヤーの手札の合計値を計算する
	 * @param player
	 * @return
	 */
	public int calcHandPoint() {
		int sum = 0;
		
		for(int i = 0; i < this.hand.size(); i ++) {
			sum += this.hand.get(i).getNum();
		}
		this.handSum = sum;
		// System.out.println("手札の合計値：" + sum);
		return sum;
	}
	
	public void printHand() {
		System.out.println("==PlayerCardNumber==");
		for(int i = 0; i < this.hand.size(); i ++) {
			this.hand.get(i).printCardNum();
		}
	}
}